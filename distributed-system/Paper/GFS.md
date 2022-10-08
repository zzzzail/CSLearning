# 1 Introduction:

GFS是一种scalable的分布式文件，其主要用处是管理数据。

同时它在廉价的商用硬件的情况下可以有很好的fault tolerance，并且她可以给大量用户提供服务。

GFS和传统的一些数据文件管理系统有什么区别:

- component failure是常态，由于GFS用的底层是多台廉价的machines。

   所以GFS拥有时时刻刻的监视，错误检查，fault tolerance，自动恢复等等功能

- 传统文件都是非常巨大的，multi-GB是非常常见的

- 大多数传统文件都是通过append新data来而不是重写原文件。random write几乎不存在

- 协同设计和file system API通过增加灵活性来优化了整个系统

## 2 Desigen Overview

### 2.1 假设

- system主要靠一些廉价的文件组成

- system储存了大量的文件，百万的文件，我们要承认每一个文件通常比100MB还要大，同时Multi-GB也是非常

常见的

- workload主要包含两种：large streaming read 和 small random read

- 系统可以非常高效的运行，当很多client在append一样的文件的时候，要能让多个顾客同时读写，保持原子性

- 稳定和持续性的宽带比低时延更加重要，但是对读写反应时间却没有很高的要求。

### 2.2 Interface
- GFS提供了一种很好的文件交互系统，即使它没有提供标准的API比如POSIX等等。

GFS系统提供Create, delete, open, close, read, write文件。

同时GFS还提供snapshot和record append等等操作（snapshot和record append会在后面详细讲）

## 2.3 Architecture
![IMG_0139(20200913-052151)](https://user-images.githubusercontent.com/52951960/93005206-7aea8e80-f581-11ea-9fd0-66b6c83f739f.PNG)

## 2.31 Single Master
单独的master对简化设计还是很重要的，master的主要作用有管理元数据信息（namespaces，访问控制信息，

文件到chunk的映射信息，和chunk的地址信息），我们要不断减少master对读写操作的参与，防止它成为

整个系统的瓶颈

举一个简单的流程
如图：

- 1  client会利用file name和chunk index(通过file name和byte offset这两个信息获得chunk index）---> master。

- 2  master 会有chunk handle+replicas的地址 ---> client

- 3  client 会选择replicas中离它最近的那一个（通过chunk handle+byte range) 来进行读写

- 4  步骤3的好处这样做client以后读取同一个chunk（client会储存chunk handle+byte range的信息）这再也不同经历一系列和master互动的流程(除非这个chunk已经挂了或者reopened）

这样可以减少与master的接触，减少了cost。

## 2.32 Chunk Size

对于GFS来说，chunk的大小都是64MB的（这是google公司经验值得出来的）

选择64MB有以下优点：

- 1 减少了GFS client和GFS master的interaction，因为chunk size较大的时候client就可以反复读取同一个chunk，而不需要

反反复复的跟master不断地interaction来拉去信息

- 2 反复读取一个chunk（由于一个chunk64MB可以储存很多内容），client和chunkserver之间持续性的TCP链接

可以减少network overhead(网络过载）。

- 3 可以减少元数据（metadata）在master储存的大小。（这个好处我们在后面会讨论）

选择64MB的一些缺点

1：一些小文件可能只存在于某一个chunk，这样这个chunk很容易变成hot spots如果很多client想用这个file的时候

（但实际情况这种事情很少发生，since we read large multi-chunk files sequentially）

不过在Google运用 batch-queue system的时候，这个hot spots的问题还是有的（就是因为一些executable被

写进一个chunk file的时候，这样同时开始的时候，几百个机器可能会产生request（对这么一个chunk）

Google的解决方案是延缓某一些batch-queue system，让他们不能同时启动，另外一个方法是

可以利用client从其他client中读取数据。

## 2.33 metadata

- master储存三种类型的元数据 1：file+chunk的namespace 2:file到chunk的映射 3: chunk副本的位置信息

- 所有元数据都储存在master中

- 前两种元数据是通过记录操作日志的方式进行persistent的储蓄，操作日志同步到包括GFS master在内的各个机器上（在各个机器上建立副本）

- master不会persistent的储存chunk的信息，但master会在master自身startup或者chunksever加入的时候，要求chunksever把其管理的

chunk位置信息发给master。

## In-Memory Data Structures

- 利用内存储存元数据，这样可以让master的操作也非常的快，同时这样也可以让周期性的全局扫描非常简单和高效。这个周期性扫描用作于

chunk garbage回收，re-replication in the persence of chunksever failure,和chunk的迁移来做到负载均衡和disk space usage

across chunkservers（后面会讨论）

- 另外一个问题是master的所在的内存储存量会不会成为这个系统的瓶颈，master可以利用少于64字节的元素据来储存64MB的chunk

但实际上这个不会成为一个大问题，因为大部分chunk都是full的只有最后一个chunk可能没有full，其次即使储存的chunk导致了master内存

储存满了，也可以通过添加内存的方式解决问题。

## Chunk Location

- master不会持续性的记录chunk的位置信息。而是在重启的时候拉去到所需chunk的信息，并周期的获取它的更新信息，

通过master控制着chunk位置，同时也通过监视HeartBeat来获取信息。

- 为什么不持续性的获取信息呢？因为会出现在保持master和chunkservers保持协程同步的时候，会出现chunkserver出现

加入这个cluster，离开这个cluster，改变名字，fail，或者需要重启等等问题

同时一个cluster里面有太多的servers，这样的event太多，从而造成cost太高

## Operation Log

-Operation Log的定义: 一系重要的metadata改变的记录

它的作用不仅仅是对改变的记录，更是记录了并行操作顺序，这点对GFS真的非常的重要

- 1 储存方式是将副本放入多个远程machine中+ 2 当我们把对应的log存到当地和远程的时候，我们再发送给client

为了减少flushing和交互对整个系统的影响，我们一般等到有几个log的时候，再进行储存。

- Check-point

- Check-point是在operation达到一定size的时候，master就会开始做check-point，就是

把内存的B- Tree格式的信息dump到磁盘当中。当master准备重启的时候，他会读lastest的checkpoint

之后再replay在这个之后的checkpoint，这样就可以缩短恢复的时间

## 3 Consistency Model

![IMG_0140(20200913-093905)](https://user-images.githubusercontent.com/52951960/93008234-0b869600-f5a5-11ea-8a68-5c6f6736b550.PNG)

- 首先我们定义一下图中的consistent,  defined的意思

- consistent: 所有的client都能看到一样的数据，不管从哪个副本中读取。

- defined: 一个文件的region发生write mutation操作后，client可以看到所有操作的数据

图中的几种情况：

1：Write(Serial Success)单个write操作（success)，则所有的副本都会写入这次操作的数据，所以所有客户都能看到这次写

的数据，属于数据defined

2：Write(Concurrent Successes) 多个写的操作(Successes), 是多个客户端写请求发给Primary后，Primary会决定写的操作顺序，但是多个

写的操作可能存在区域重叠，这样最后的结果可能是多个写操作叠加在一起的结果，这样的情况就是consistent但是不是defined。

3: Write(Failure) 写操作失败，则可能有一些副本进行了write操作，但是有一些没有，所以他是inconsistent的

4: Record Append(Serial Success and Concurrent Success) 由于Record Append可能包含重复数据，所以这不是consistent的，但是是defined的

5: Record Append(Failure) 部分副本可能append成功，但是部分副本可能会append失败，所以是inconsistent的。

- 为了保持“已经操作”的文件的consistent且包含最后一个写操作，GFS通过以下的操作来保证：

1： 保持左右操作的一致性，保证所有chunk的操作是有一样的order的

2： 当有一个chunk副本不一样的时候（stale）可能是因为它的chunkservers挂掉的时候，这个chunk就没有进行操作。

但是GFS会增加一个version，version是在chunkservers挂掉的时候对每一次client进行write或者append操作的时候，version会增加

（:((）

- GFS应用层

GFS为了保持一个consistency model，应用层采取了一些必要的措施:

1: 保持append而不是overwrite

2：checkpoint

3：writing self-validation recording

4：self - identifying recording

具体操作具体是： append一个file的时候，写完以后要进行重命名

对文件进行checkpoint，且在最近一次的checkpoint文件区域和最新文件区域的数据是否具有一致性，如果不一致，则可以进行重新操作

对于并行的append的操作，对于出现重复的数据，client提供去重的功能。

## 4: System Interaction

System Interaction会看到如何把client，master，和chunkservers相互互动去应用数据操作，例如原子性数据append，snapshot等等。

### 4.1 Lease and Mutation Order

- 我们用lease来保持一致性操作顺序，GFS master会对一个chunk选择一个chunkserver，发放lease（叫做primary），此时就用primary来控制整个

一致性操作

- Lease机制为了减少master的参与，他会有一个timout的时间（60s），但是lease不会马上expire。

如果lease正常工作，lease可以持续性的存在（通过HeartBeat来延续时间，且正常工作，时间会被持续性延续下去）

当primary和master断了以后，GFS也方便把其他chunk副本所在的chunkservers作为新的primary，

### 4.2 Control Flow
![3C74D0B1CC535C3D4773AA97EB9C52D1](https://user-images.githubusercontent.com/52951960/93028727-48579900-f648-11ea-972e-6fe0471d86d9.png)

- 1 client会向master要当前chunk的，拥有lease的chunkserver和其他chunkserver副本的位置，如果没有

lease，则master会选择一个副本并发放lease（not shown）

- 2 ：master会把primary chunkserver和 chunkserver的副本信息都发送给client

client会把信息cache储存起来，当未来primary chunksever联系不上或者这个chunkserver不在拥有lease，它会再次

和master联系。

- 3 client会把这些数据发送给所有的拥有该chunk的副本（这里的push是没有任何顺序的）

每个chunkserver会储存这些数据在内部的LRU 缓存（会在这些数据用了以后或者过期的时候删除）

By decoupling the data flow from the control flow, we can improve performance by scheduling

the expensive data flow based on the network topology regradless of which chunkserver is the

primary(接下来的章节会讨论这些数据是如何被发送的）

- 4 当所有的replicas都接受到了数据，client会向primary发送一个请求。 这个请求会确认好所有已经发送了的数据

而且primary会向所有的操作都发送一个序列号（这些操作可能来自于多个client），这样可以保证多个client并发写请求

可以让多个副本写入的数据是consistency的

- 5 primary会向所有的副本发送secondary replicas发送请求，每个副本都会根据这个请求进行一致性的操作顺序

- 6 所有的副本会告诉primary当他们完成所有操作的时候

- 7 primary会回复client，任何在replicas的errors都会报告给client。

错误是在primary chunkserver里面是正确的操作的，但是可能在某一个副本当中发生了错误（因为如果在primary发生错误，它就不会被发送出来）

client会处理被遗留的那一块出现一致性问题的区域，我们经常尝试好几次（3）和（7）防止错误发生，而进行自动恢复程序启动，在开始write的时候。

## 4.3 Data Flow

- 数据发送是linearly型的，是一种picked chain of chunkservers, 在一种pipeline的方式。GFS的目标是充分利用机器的宽带，防止网络瓶颈

和高延迟性，减小push数据的时候的延迟性

- 这里对linearly进行解释，比如这里有chunkserver S1-S4。首先client会选取最近的chunkserver S1， 之后 S1会选取S2-S4之中离它最近的

chunkserver S2，S2同理发给S3, S3传给S4。（这里的距离是通过网络拓扑来确定的(network topology),而且这个距离是可以通过IP address来

精确的估计。

- 按照这个方式传播B个字节到R个副本，这时候网络吞吐量为T，时间之间的时延为L --- 整个数据的传输时间为 B/T+RL

## 4.4 Atomic Record Appends

- 传统的write中，client会明确数据操作的偏移量，而且并发的write在同一个区域不是序列化的，它可能是很多的数据碎片来自于很多的client里的。

但是在append里面，client只specify data。

- 如何操作呢？

1：首先client会把数据push到所有副本的最后一个chunk，接着它会向primary发起请求。同时primary会检查最后一个chunk是不是还有足够的空间写当前

的请求，可以就开始执行写流程。如果不行，primary会把最后一个chunk的剩余空间pad满，之后告诉其他的副本都这么干，最后告诉client，最后的chunk已经

满了，需要开下一个chunk。

2：如果整个append失败了的话，client会重新尝试这个操作，这样可能有一些原来append成功的chunk就有两次append，但是原来没有成功的chunk就只有一分

append。

所以GFS是不能保证bytewise identical的，他只能保证一次的written是原子性的。

## 4.5 SnapShot 

- snapshot操作目的是进行copy一个file或者a directory tree。它主要用作于快速的create 分支copy大量数据集，或者是checkpoint当前状态在某些改变发生

之前（这些change可能可以被committed 或者被驳回）

1： client先会向GFS master发送Snapshot请求

2：GFS master收到请求以后，会回收所有这次Snapchat涉及到的chunk的lease。（保证了接下来的写操作可以让master找到lease的holder，同时也给master机会创造

一个新的copy chunk。

3：当这个lease到期以后，master会log操作，并把这个log放到disk中，之后他会在内存中复制一份snapshot涉及到进行复制或者diectory tree的metadata。

4：snapshot完成以后，client会写一个chunk C在sp操作以后，client会要求master找到现在的lease holder。

5：这时候master会发现chunk C的引用次数已经超过了1，即snapshot和master自己的request。这样拥有chunk C的chunkserver会发送一个创建chunk C的拷贝请求

记作chunk C'，把最新的数据导入到C'中，就完成了copy on write。

### 5 Master Operation 

- GFS master有很多的功能，其中包括namespace Management, Replica Placement, Chunk Creation, Re-replication and Rebalancing and Garbage Collection等。

## 5.1 Namespace Management and Locking

不想传统的file System， GFS 没有一个pre-directory data（可以列出来所有的file在dictory里面），它也不支持别名这种处理（对于同一种file或者directory而言）

每一个master的操作都会获得一系列的锁。比如当一个操作设计/d1/d2/.../dn/leaf（leaf可能是一种路径，也可能是一种file，

那么需要获得/d1/,/d1/d2/,...,/d1/d2/.../dn的read lock，然后根据操作，获得

/d1/,/d1/d2/,...,/d1/d2/.../dn的read lock或者是write lock。

- Example

当/home/user被快照到/save/user上时，我们做不到/home/user/foo的创建

因为对于snapshot而言:

- 1: 获得/home 和/save的read lock，获取/home/user和/save/user的read lock

- 2:Snapshot 对 /home和/save的read lock，/home/user和/save/user的write lock。而创建的时候，我们要/home,/home/user的read lock，然后/home/user/foo的write lock。这个时候/home/user的锁产生conflict，/home/user/foo创建是不可行的。

- 3:这种锁的机制好处是可以在同一个目录下并发的进行一系列操作，比如可以在同一个目录下创建文件，每一个操作豆申请了read lock和write lock。read lock有效

的防止了被删除，重命名，快照等操作

## 5.2 Replica Placement

- GFS的cluster是高度分布在各个不同的地方的，经常有成百上千个chunkserver分布在多个machine rack上。这样不仅可以防止网络switch带来的cost，同时分布在不同rack上

可以提高数据的scalability， reliability，and avaliability.

## 5.3 Creation, Re-replication, Rebalancing

-  Creation 我们在creation新的chunk的时候，我们会考虑以下几个因素

1：磁盘空间的average使用率要低于平均值的chunkserver。

2：控制好每一个chunkserver的创建chunk的次数，因为每一个创建代表着后面有有很多数据操作，虽然创造一个chunk很cheap，但是如果一个chunkserver

有创建太多的chunk，会导致这个chunkserver的write traffic。

3： chunk的副本应该放在不同的chunkserver机架上。

- Re-replication
 
 1：如果一个chunk的副本没有达到所需要的数量的时候，就需要做复制操作（这里chunkserver unavailable，副本的数据错误，磁盘损坏，或者预定的副本数量增加）
 
 chunk的复制的优先级根据下列的因素来确定：
 
 （1）：丢失副本多的比丢失副本少的优先级高
 
 （2): 如果该文件正在使用，那么比已经删除文件的chunk优先级高。
 
 （3）：阻塞client进程的chunk优先级比较高。
 
 chunk准备复制的时候考虑五个因素：磁盘使用率，该chunkserver的复制个数限制，多个副本应该放在多个机架，集群的复制个数限制，限制每个chunkserver的复制
 
 网络宽带（通过限制流量的速率来限制）。
 
 - Rebalancing
 
 GFS会周期性检查副本的分布情况，这样能更好的调整磁盘的使用翁恺和负载均衡。 GFS master每次对新加入的chunkserver，会逐渐的迁移到副本上面，这样可以防止
 
 chunkserver的带宽打满。
 
 - Garbage Collection
 
 当一个文件被删除，GFS不会立即进行物理性的删除，而是在后面的定期清理过程当中才会做到真正的删除。
 
 对于一个删除操作，GFS只是写一条log+文件命名成对外不可见的名称（名称包括删除时间的times-tamp）。
 
 GFS master会定期扫描，这些文件存在超过三天以后，这些文件会从namespace中删掉（改变它的名字，当然可以不删除它，如果把名字改回去的话）+内存中的metadata删除掉。
 
 ---> 对chunk namespace的定期扫描当中，master会发现这些要删除掉的oropened chunks，同时在metadata中删除掉。
 
 同时在chunkserver和heartbeat的交互过程中，GFS master会把这些不在metadata的chunk告诉chunkserver，这样chunkserver就可以删除这些chunk了。
 
 为什么用心跳交互的方式，因为心跳交互可以再失败以后重复操作+删除操作和其他的全局scan metadata是可以一起做的。
 
 缺点：这样可能需要时常的创建和删除文件，这种延期删除方式可能会让磁盘的使用率过高。GFS一般是采用一个文件调用删除操作两次，且GFS会马上做物理删除
 
 操作，释放空间。
 
 - 5.5 Stale Replication Detection

当一台chunkserver挂掉的时候，有新的写入操作到chunk副本，会导致chunkserve的数据不是最新的。这里master就要维持chunk version number来区别

up-to-data 和 stale replica。

当master分配lease到一个chunk时，它会更新chunk version number，然后其他的副本都会更新该值。这个操作是在返回给客户端之前完成的，如果有一个chunkserver当前是down的，那么它的version number就不会增加。当chunkserver重启后，会汇报它的chunk以及version number，对于version number落后的chunk，master就认为这个chunk的数据是落后的。

GFS master会把落后的chunk当垃圾来清理掉，并且不会把落后的chunkserver的位置信息传给client。（GFS master把落后的chunk当作垃圾清理，那么，是否是走re-replication的逻辑来生成新的副本呢？没有，是走立即复制的逻辑。）

## High Avaliability

- overall system highly available通过两次fast recovery+replication

对于fast recovery而言chunkserver和master都是秒级重启的

- Chunk Replication

每一个chunk分布在多个rack上，副本的数量是由client指定好的。

chunkserver出现问题的时候，GFS master会自动复制副本（保证了副本数量和用户指定的一致）

- Master Replication

Master包含的operation log+checkpoint都会复制到很多机器上面，当只有在这些机器成功了以后，才能正常运行（复制的shadow master

只有在primary master down掉以后，才能发挥作用。

只有一台master在garabge collection的后台操作的收。当master down掉，master能在很多时间内重启。

master的机器挂掉的话，监控会在其他拥有operation log的机器上进行重启master。

但是新启动的只提供read，因为挂掉的一瞬间，可能有一些log会记录到primary里面，但是没有记录到secondary master。

- Data Integrity

每个chunkserver可以用checksum来验证数据是否损坏的。每个chunk被分成多个64KB的block，每个block有32位的checksum，checksum在内存中和磁盘的log中都有记录。

对于读请求，chunkserver会检查读操作所涉及block的所有checksum值是否正确，如果有一个block的checksum不对，那么会报错给client和master。client这时会从其他副本读数据，而master会clone一个新副本，当新副本clone好后，master会删除掉这个checksum出错的副本。

- Diagnose Tools 主要是通过log，包括重要事件的log(chunkserver上下线)，RPC请求，RPC响应等。
（未完待续）
 



