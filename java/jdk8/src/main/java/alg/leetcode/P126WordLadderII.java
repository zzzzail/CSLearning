package alg.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/word-ladder-ii/
 * 单词接龙 2
 */
public class P126WordLadderII {
    
    public static void main(String[] args) {
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        List<List<String>> res1 = findLadders(beginWord1, endWord1, wordList1);
        System.out.println(res1);
        
        String beginWord2 = "a";
        String endWord2 = "c";
        List<String> wordList2 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<List<String>> res2 = findLadders(beginWord2, endWord2, wordList2);
        System.out.println(res2);
        
        String beginWord3 = "red";
        String endWord3 = "tax";
        List<String> wordList3 = new ArrayList<>(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));
        List<List<String>> res3 = findLadders(beginWord3, endWord3, wordList3);
        // 预期结果 [["red","ted","tad","tax"],["red","ted","tex","tax"],["red","rex","tex","tax"]]
        System.out.println(res3);
    }
    
    /**
     * ["hit","hot","dot","dog","cog"]
     * ["hit","hot","lot","log","cog"]
     * 使用 尾 Map 这个想法非常失败！
     * Set 里的值是不能删除的！
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        List<List<String>> res = new LinkedList<>();
        if (!set.contains(endWord)) return res;
        set.remove(beginWord);
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // 已尾单词作为 key 的 map
        Map<String, List<String>> tailMap = new HashMap<>();
        tailMap.put(beginWord, new ArrayList<>(Collections.singletonList(beginWord)));
        while (!queue.isEmpty()) {
            for (int count = queue.size(); count > 0; --count) {
                String currentWord = queue.poll();
                List<String> diffs = getALetterDifferences(set, currentWord);
                if (diffs.size() <= 0) continue;
                for (String diff : diffs) {
                    // if (endWord.equals(diff)) break; // 到达 endWord
                    set.remove(diff);
                    queue.add(diff);
                    updateTailMap(tailMap, currentWord, diff);
                }
                tailMap.remove(currentWord);
            }
        }
        handleResult(tailMap, res, endWord);
        return res;
    }
    
    private static void handleResult(Map<String, List<String>> tailMap, List<List<String>> res, String endWord) {
        int min = Integer.MAX_VALUE; // 最短路径是多少步
        for (String key : tailMap.keySet()) {
            List<String> list = tailMap.get(key);
            if (countDiffLetter(endWord, key) == 0) min = Math.min(min, list.size());
            else if (countDiffLetter(endWord, key) == 1) min = Math.min(min, list.size() + 1);
        }
        for (String key : tailMap.keySet()) {
            List<String> list = tailMap.get(key);
            int c = countDiffLetter(endWord, key);
            if (c == 0) { // 相等的情况
                min = list.size();
                res.add(new ArrayList<>(list));
            }
            else if (c == 1 && list.size() < min) { // 只差一个字母的情况
                List<String> l = tailMap.get(key);
                l.add(endWord);
                res.add(new ArrayList<>(l));
            }
        }
    }
    
    private static List<String> getALetterDifferences(Set<String> set, String str) {
        List<String> res = new ArrayList<>();
        for (String s : set)
            if (countDiffLetter(str, s) == 1) res.add(s);
        return res;
    }
    
    private static int countDiffLetter(String str1, String str2) {
        if (str1 == null || str2 == null) return -1;
        char[] ca1 = str1.toCharArray();
        char[] ca2 = str2.toCharArray();
        int min = Math.min(ca1.length, ca2.length);
        int diff = Math.abs(ca1.length < ca2.length ? ca2.length - ca1.length : ca1.length - ca2.length);
        int res = 0;
        for (int i = 0; i < min; ++i) {
            if (ca1[i] != ca2[i]) ++res;
        }
        return res + diff;
    }
    
    private static void updateTailMap(Map<String, List<String>> tailMap, String oldTail, String newTail) {
        List<String> oldList = tailMap.get(oldTail);
        if (oldList == null) return;
        List<String> newList = new ArrayList<>(oldList);
        newList.add(newTail);
        tailMap.put(newTail, newList);
    }
    
    /**
     * @link https://leetcode-cn.com/problems/word-ladder-ii/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you--2/
     * BFS 时需要记录：
     * 从当前的单词 currWord 只变化了一个字符以后，且又在单词字典中的单词 nextWord 之间的
     * 单向关系（虽然实际上无向图，但是广度优先遍历是有方向的，我们解决这个问题可以只看成有向
     * 图），记为 from，它是一个映射关系：键是单词，值是广度优先遍历的时候从哪些单词可以遍历
     * 到「键」所表示的单词，使用哈希表来保存。
     *
     * todo: 搞懂这一道复杂的题目
     */
    public static List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表，这里命名为「字典」
        Set<String> dict = new HashSet<>(wordList);
        // 特殊用例判断
        if (!dict.contains(endWord)) return res;
        // 因为从 beginWord 开始扩展，因此 dict 里一定不可以有 beginWord
        dict.remove(beginWord);
        
        // 第 1 步：广度优先遍历构建图
        // 为了避免记录不需要的边，我们需要记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
        // steps 记录了已经访问过的 word 集合，同时记录了在第几层访问到
        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系，dfs 的时候会用到
        Map<String, Set<String>> from = new HashMap<>();
        boolean found = bfs(beginWord, endWord, dict, steps, from);
        
        // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(from, path, beginWord, endWord, res);
        }
        return res;
    }
    
    private static boolean bfs(String beginWord, String endWord, Set<String> dict, Map<String, Integer> steps, Map<String, Set<String>> from) {
        int wordLen = beginWord.length();
        int step = 0;
        boolean found = false;
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                for (int j = 0; j < wordLen; j++) {
                    char origin = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        // 将每一位替换成 26 个小写英文字母
                        charArray[j] = c;
                        String nextWord = String.valueOf(charArray);
                        // 注意：这几行代码的逻辑先后顺序
                        if (steps.containsKey(nextWord) && steps.get(nextWord) == step) {
                            from.get(nextWord).add(currWord);
                        }
                        
                        if (!dict.contains(nextWord)) {
                            continue;
                        }
                        dict.remove(nextWord);
                        
                        // dict 和 steps 承担了已经访问的功能
                        queue.offer(nextWord);
                        
                        // 维护 from、steps、found 的定义
                        from.putIfAbsent(nextWord, new HashSet<>());
                        from.get(nextWord).add(currWord);
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord)) {
                            // 注意：由于有多条路径到达 endWord 找到以后不能立即退出，只需要设置 found = true 即可
                            found = true;
                        }
                    }
                    charArray[j] = origin;
                }
            }
            if (found) {
                break;
            }
        }
        return found;
    }
    
    private static void dfs(Map<String, Set<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(cur)) {
            path.addFirst(precursor);
            dfs(from, path, beginWord, precursor, res);
            path.removeFirst();
        }
    }
}
