package raft

//
// this is an outline of the API that raft must expose to
// the service (or tester). see comments below for
// each of these functions for more details.
//
// rf = Make(...)
//   create a new Raft server.
// rf.Start(command interface{}) (index, term, isleader)
//   start agreement on a new log entry
// rf.GetState() (term, isLeader)
//   ask a Raft for its current term, and whether it thinks it is leader
// ApplyMsg
//   each time a new entry is committed to the log, each Raft peer
//   should send an ApplyMsg to the service (or tester)
//   in the same server.
//

import (
	// "bytes"
	"sync"
	"sync/atomic"
	"time"

	// "6.824/labgob"
	"6.824/labrpc"
)

// 设置状态的常量
const (
	Follower  = NodeState(1)
	Candidate = NodeState(2)
	Leader    = NodeState(3)
)

// as each Raft peer becomes aware that successive log entries are
// committed, the peer should send an ApplyMsg to the service (or
// tester) on the same server, via the applyCh passed to Make(). set
// CommandValid to true to indicate that the ApplyMsg contains a newly
// committed log entry.
//
// in part 2D you'll want to send other kinds of messages (e.g.,
// snapshots) on the applyCh, but set CommandValid to false for these
// other uses.
type ApplyMsg struct {
	CommandValid bool
	Command      interface{}
	CommandIndex int

	// For 2D:
	SnapshotValid bool
	Snapshot      []byte
	SnapshotTerm  int
	SnapshotIndex int
}

// 节点状态描述
type NodeState uint8

func (s NodeState) String() string {
	switch s {
	case Follower:
		return "Follower"
	case Candidate:
		return "Candidate"
	case Leader:
		return "Leader"
	}
	return "Unknow"
}

type LogEntry struct {
	Command interface{}
	Term    int
}

// example RequestVote RPC arguments structure.
// field names must start with capital letters!
type RequestVoteArgs struct {
	// Your data here (2A, 2B).

	// 2A
	Term        int
	CandidateId int

	// 2B
	LastLogIndex int
	LastLogTerm  int
}

// example RequestVote RPC reply structure.
// field names must start with capital letters!
type RequestVoteReply struct {
	// Your data here (2A).
	Term        int
	VoteGranted bool // 投票授权
}

type AppendEntriesArgs struct {
	// 2A
	Term     int
	LeaderId int

	// 2B
	PrevLogIndex int
	PrevLogTerm  int
	LogEntries   []LogEntry
	LeaderCommit int
}

type AppendEntriesReply struct {
	// 2A
	Term    int
	Success bool

	// 2C
	// OPTIMIZE: see thesis section 5.3
	ConflictTerm  int
	ConflictIndex int
}

// A Go object implementing a single Raft peer.
type Raft struct {
	mu        sync.Mutex          // Lock to protect shared access to this peer's state
	peers     []*labrpc.ClientEnd // RPC end points of all peers
	persister *Persister          // Object to hold this peer's persisted state
	me        int                 // this peer's index into peers[]
	dead      int32               // set by Kill()

	// Your data here (2A, 2B, 2C).
	// Look at the paper's Figure 2 for a description of what
	// state a Raft server must maintain.

	// 2A
	currentTerm    int         // 当前任期数
	votedFor       int         // 为谁投票
	electionTimer  *time.Timer // 选举时间
	heartbeatTimer *time.Timer // 心跳时间
	state          NodeState   // 节点状态

	// 2B
	logs        []LogEntry
	commitIndex int
	lastApplied int
	nextIndex   []int
	matchIndex  []int
	applyCh     chan ApplyMsg

	// 3B
	snapshottedIndex int
}

// return currentTerm and whether this server
// believes it is the leader.
func (rf *Raft) GetState() (int, bool) {
	var term int
	var isleader bool
	// Your code here (2A).
	term = rf.currentTerm
	isleader = (rf.state == Leader)
	return term, isleader
}

// save Raft's persistent state to stable storage,
// where it can later be retrieved after a crash and restart.
// see paper's Figure 2 for a description of what should be persistent.
func (rf *Raft) persist() {
	// Your code here (2C).
	// Example:
	// w := new(bytes.Buffer)
	// e := labgob.NewEncoder(w)
	// e.Encode(rf.xxx)
	// e.Encode(rf.yyy)
	// data := w.Bytes()
	// rf.persister.SaveRaftState(data)
}

// raft server convert to the NodeState
func (rf *Raft) convertTo(s NodeState) {
	if s == rf.state {
		return
	}

	DPrintf("Term %d: server %d convert from %v to %v\n", rf.currentTerm, rf.me, rf.state, s)

	rf.state = s
	switch s {
	case Follower:
		rf.heartbeatTimer.Stop()
		ResetTimer(rf.electionTimer,
			RandTimeDuration(ElectionTimeoutLower, ElectionTimeoutUpper))
		rf.votedFor = -1
	case Candidate:
		// 开始选举
		rf.startElection()
	case Leader:
		for i := range rf.nextIndex {
			// initialized to leader last log index + 1
			rf.nextIndex[i] = rf.getAbsoluteLogIndex(len(rf.logs))
		}
		for i := range rf.matchIndex {
			rf.matchIndex[i] = rf.snapshottedIndex
		}

		rf.electionTimer.Stop()
		rf.broadcastHeartbeat()
		ResetTimer(rf.heartbeatTimer, HeartbeatInterval)
	}
}

// should be called with lock
func (rf *Raft) startElection() {
	defer rf.persist()

	rf.currentTerm += 1

	lastLogIndex := len(rf.logs) - 1
	args := RequestVoteArgs{
		Term:         rf.currentTerm,
		CandidateId:  rf.me,
		LastLogIndex: rf.getAbsoluteLogIndex(lastLogIndex),
		LastLogTerm:  rf.logs[lastLogIndex].Term,
	}

	var voteCount int32

	for i := range rf.peers {
		if i == rf.me {
			rf.votedFor = rf.me
			atomic.AddInt32(&voteCount, 1)
			continue
		}
		go func(server int) {
			var reply RequestVoteReply
			if rf.sendRequestVote(server, &args, &reply) {
				rf.mu.Lock()
				DPrintf("%v got RequestVote response from node %d, VoteGranted=%v, Term=%d",
					rf, server, reply.VoteGranted, reply.Term)
				if reply.VoteGranted && rf.state == Candidate {
					atomic.AddInt32(&voteCount, 1)
					if atomic.LoadInt32(&voteCount) > int32(len(rf.peers)/2) {
						rf.convertTo(Leader)
					}
				} else {
					if reply.Term > rf.currentTerm {
						rf.currentTerm = reply.Term
						rf.convertTo(Follower)
						rf.persist()
					}
				}
				rf.mu.Unlock()
			}
		}(i)
	}
}

func (rf *Raft) getAbsoluteLogIndex(index int) int {
	// index of log including snapshotted ones
	return index + rf.snapshottedIndex
}

// should be called with lock
func (rf *Raft) broadcastHeartbeat() {
	for i := range rf.peers {
		if i == rf.me {
			continue
		}

		go func(server int) {
			rf.mu.Lock()
			if rf.state != Leader {
				rf.mu.Unlock()
				return
			}

			prevLogIndex := rf.nextIndex[server]

			if prevLogIndex < rf.snapshottedIndex {
				// leader has discarded log entries the follower needs
				// send snapshot to follower and retry later
				rf.mu.Unlock()
				rf.syncSnapshotWith(server)
				return
			}

			// use deep copy to avoid race condition
			// when override log in AppendEntries()
			entries := make([]LogEntry, len(rf.logs[rf.getRelativeLogIndex(prevLogIndex+1):]))
			copy(entries, rf.logs[rf.getRelativeLogIndex(prevLogIndex+1):])

			args := AppendEntriesArgs{
				Term:         rf.currentTerm,
				LeaderId:     rf.me,
				PrevLogIndex: prevLogIndex,
				PrevLogTerm:  rf.logs[rf.getRelativeLogIndex(prevLogIndex)].Term,
				LogEntries:   entries,
				LeaderCommit: rf.commitIndex,
			}
			rf.mu.Unlock()

			var reply AppendEntriesReply
			if rf.sendAppendEntries(server, &args, &reply) {
				rf.mu.Lock()
				if rf.state != Leader {
					rf.mu.Unlock()
					return
				}

				if reply.Success {
					// successfully replicated args.LogEntries
					rf.matchIndex[server] = args.PrevLogIndex + len(args.LogEntries)
					rf.nextIndex[server] = rf.matchIndex[server] + 1

					// check if we need to update commitIndex
					// from the last log entry to committed one
					for i := rf.getAbsoluteLogIndex(len(rf.logs) - 1); i > rf.commitIndex; i-- {
						count := 0
						for _, matchIndex := range rf.matchIndex {
							if matchIndex >= 1 {
								count += 1
							}

							if count > len(rf.peers)/2 {
								// most of nodes agreed on rf.logs[i]
								rf.setCommitIndex(i)
								break
							}
						}
					}
				} else {
					if reply.Term > rf.currentTerm {
						rf.currentTerm = reply.Term
						rf.convertTo(Follower)
						rf.persist()
					} else {
						// log unmatch, update nextIndex[server] for the next trial
						rf.nextIndex[server] = reply.ConflictIndex

						// if term found, override it to
						// the first entry after entries in ConflictTerm
						if reply.ConflictTerm != -1 {
							DPrintf("%v conflict with server %d, prevLogIndex %d, log length = %d",
								rf, server, args.PrevLogIndex, len(rf.logs))
							for i := args.PrevLogIndex; i >= rf.snapshottedIndex+1; i-- {
								if rf.logs[rf.getRelativeLogIndex(i-1)].Term == reply.ConflictTerm {
									// in next trial, check if log entries in ConflictTerm matches
									rf.nextIndex[server] = i
									break
								}
							}
						}

						// TODO: retry now or in next RPC?
					}
				}
			}
		}(i)

	}
}

func (rf *Raft) syncSnapshotWith(server int) {
	// todo 同步快照到 server
}

func (rf *Raft) getRelativeLogIndex(index int) int {
	// index of rf.logs
	return index - rf.snapshottedIndex
}

func (rf *Raft) sendAppendEntries(server int, args *AppendEntriesArgs, reply *AppendEntriesReply) bool {
	ok := rf.peers[server].Call("Raft.AppendEntries", args, reply)
	return ok
}

// restore previously persisted state.
func (rf *Raft) readPersist(data []byte) {
	if data == nil || len(data) < 1 { // bootstrap without any state?
		return
	}
	// Your code here (2C).
	// Example:
	// r := bytes.NewBuffer(data)
	// d := labgob.NewDecoder(r)
	// var xxx
	// var yyy
	// if d.Decode(&xxx) != nil ||
	//    d.Decode(&yyy) != nil {
	//   error...
	// } else {
	//   rf.xxx = xxx
	//   rf.yyy = yyy
	// }
}

func (rf *Raft) setCommitIndex(commitIndex int) {

}

// A service wants to switch to snapshot.  Only do so if Raft hasn't
// have more recent info since it communicate the snapshot on applyCh.
func (rf *Raft) CondInstallSnapshot(lastIncludedTerm int, lastIncludedIndex int, snapshot []byte) bool {

	// Your code here (2D).

	return true
}

// the service says it has created a snapshot that has
// all info up to and including index. this means the
// service no longer needs the log through (and including)
// that index. Raft should now trim its log as much as possible.
func (rf *Raft) Snapshot(index int, snapshot []byte) {
	// Your code here (2D).

}

// example RequestVote RPC handler.
func (rf *Raft) RequestVote(args *RequestVoteArgs, reply *RequestVoteReply) {
	// Your code here (2A, 2B).
	rf.mu.Lock()
	defer rf.mu.Unlock()
	// 持久化
	// execute before rf.mu.Unlock()
	defer rf.persist()

	// 若任期数 小于 若当前任期数
	if args.Term < rf.currentTerm ||
		(args.Term == rf.currentTerm && rf.votedFor != -1 && rf.votedFor != args.CandidateId) {
		reply.Term = rf.currentTerm
		reply.VoteGranted = false
		return
	}

	// 若任期数大于当前任期数
	if args.Term > rf.currentTerm {
		rf.currentTerm = args.Term
		rf.convertTo(Follower)
		// do not return here.
	}

	// 2B: candidate's vote should be at least up-to-date as receiver's log
	// "up-to-date" is defined in thesis 5.4.1
	lastLogIndex := len(rf.logs) - 1
	if args.LastLogTerm < rf.logs[lastLogIndex].Term ||
		(args.LastLogTerm == rf.logs[lastLogIndex].Term && args.LastLogIndex < rf.getAbsoluteLogIndex(lastLogIndex)) {
		// Receiver is more up-to-date, does not grant vote
		reply.Term = rf.currentTerm
		reply.VoteGranted = false
		return
	}

	rf.votedFor = args.CandidateId
	reply.Term = rf.currentTerm
	reply.VoteGranted = true
	// reset timer after grant vote
	ResetTimer(rf.electionTimer, RandTimeDuration(ElectionTimeoutLower, ElectionTimeoutUpper))
}

// example code to send a RequestVote RPC to a server.
// server is the index of the target server in rf.peers[].
// expects RPC arguments in args.
// fills in *reply with RPC reply, so caller should
// pass &reply.
// the types of the args and reply passed to Call() must be
// the same as the types of the arguments declared in the
// handler function (including whether they are pointers).
//
// The labrpc package simulates a lossy network, in which servers
// may be unreachable, and in which requests and replies may be lost.
// Call() sends a request and waits for a reply. If a reply arrives
// within a timeout interval, Call() returns true; otherwise
// Call() returns false. Thus Call() may not return for a while.
// A false return can be caused by a dead server, a live server that
// can't be reached, a lost request, or a lost reply.
//
// Call() is guaranteed to return (perhaps after a delay) *except* if the
// handler function on the server side does not return.  Thus there
// is no need to implement your own timeouts around Call().
//
// look at the comments in ../labrpc/labrpc.go for more details.
//
// if you're having trouble getting RPC to work, check that you've
// capitalized all field names in structs passed over RPC, and
// that the caller passes the address of the reply struct with &, not
// the struct itself.
func (rf *Raft) sendRequestVote(server int, args *RequestVoteArgs, reply *RequestVoteReply) bool {
	ok := rf.peers[server].Call("Raft.RequestVote", args, reply)
	return ok
}

// the service using Raft (e.g. a k/v server) wants to start
// agreement on the next command to be appended to Raft's log. if this
// server isn't the leader, returns false. otherwise start the
// agreement and return immediately. there is no guarantee that this
// command will ever be committed to the Raft log, since the leader
// may fail or lose an election. even if the Raft instance has been killed,
// this function should return gracefully.
//
// the first return value is the index that the command will appear at
// if it's ever committed. the second return value is the current
// term. the third return value is true if this server believes it is
// the leader.
func (rf *Raft) Start(command interface{}) (int, int, bool) {
	index := -1
	term := -1
	isLeader := true

	// Your code here (2B).

	return index, term, isLeader
}

// the tester doesn't halt goroutines created by Raft after each test,
// but it does call the Kill() method. your code can use killed() to
// check whether Kill() has been called. the use of atomic avoids the
// need for a lock.
//
// the issue is that long-running goroutines use memory and may chew
// up CPU time, perhaps causing later tests to fail and generating
// confusing debug output. any goroutine with a long-running loop
// should call killed() to check whether it should stop.
func (rf *Raft) Kill() {
	atomic.StoreInt32(&rf.dead, 1)
	// Your code here, if desired.
}

func (rf *Raft) killed() bool {
	z := atomic.LoadInt32(&rf.dead)
	return z == 1
}

// The ticker go routine starts a new election if this peer hasn't received
// heartsbeats recently.
func (rf *Raft) ticker() {
	for !rf.killed() {

		// Your code here to check if a leader election should
		// be started and to randomize sleeping time using
		// time.Sleep().

		select {
		case <-rf.electionTimer.C:
			rf.mu.Lock()
			// we know the timer has stopped now
			// no need to call Stop()
			rf.electionTimer.Reset(RandTimeDuration(ElectionTimeoutLower, ElectionTimeoutUpper))
			if rf.state == Follower {
				// Raft::startElection() is called in conversion to Candidate
				rf.convertTo(Candidate)
			} else {
				rf.startElection()
			}
			rf.mu.Unlock()
		case <-rf.heartbeatTimer.C:
			rf.mu.Lock()
			if rf.state == Leader {
				rf.broadcastHeartbeat()
				// we know the timer has stopped now
				// no need to call Stop()
				rf.heartbeatTimer.Reset(HeartbeatInterval)
			}
			rf.mu.Unlock()
		}

	}
}

// the service or tester wants to create a Raft server. the ports
// of all the Raft servers (including this one) are in peers[]. this
// server's port is peers[me]. all the servers' peers[] arrays
// have the same order. persister is a place for this server to
// save its persistent state, and also initially holds the most
// recent saved state, if any. applyCh is a channel on which the
// tester or service expects Raft to send ApplyMsg messages.
// Make() must return quickly, so it should start goroutines
// for any long-running work.
func Make(peers []*labrpc.ClientEnd, me int, persister *Persister, applyCh chan ApplyMsg) *Raft {
	rf := &Raft{}
	rf.peers = peers
	rf.persister = persister
	rf.me = me

	// Your initialization code here (2A, 2B, 2C).
	rf.currentTerm = 0
	rf.votedFor = -1
	rf.heartbeatTimer = time.NewTimer(HeartbeatInterval)
	rf.electionTimer = time.NewTimer(RandTimeDuration(ElectionTimeoutLower, ElectionTimeoutUpper))
	rf.state = Follower
	rf.applyCh = applyCh
	rf.logs = make([]LogEntry, 1) // start from index 1

	// initialize from state persisted before a crash
	rf.mu.Lock()
	rf.readPersist(persister.ReadRaftState())
	rf.mu.Unlock()

	// start ticker goroutine to start elections
	go rf.ticker()

	return rf
}
