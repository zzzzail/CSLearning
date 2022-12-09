package alg.leetcode;

import java.util.*;

/**
 * @link https://leetcode-cn.com/problems/subsets/
 * 子集
 */
public class P78Subsets {
    
    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 2, 3};
        List<List<Integer>> res1 = subsets(nums1);
        System.out.println(res1);
    }
    
    /*
     暴力求解
     nums = [1, 2, 3]
     初始化结果集 res = [ [] ]
     每次循环在保留 res 结果集的同时，再依次将 res 中的每个结果拿出来拼接上当前关注值 nums[i] 形成一个新的结果，并追加到 res 中。
     第一次循环加入 1
         res = [ [], [1] ]
     第二次循环加入 2
         res = [ [], [1], [2], [1, 2] ]
     第三次循环
         res = [ [], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3] ]
     
     时间复杂度： O(2^n)
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());
        for (int i = 0; i < nums.length; i++) {
            int rn = res.size();
            for (int j = 0; j < rn; j++) {
                List<Integer> t = new LinkedList<>(res.get(j));
                t.add(nums[i]);
                res.add(t);
            }
        }
        return res;
    }
    
    /*
     二进制解法
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> t = new LinkedList<>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ( (mask & (1 << i)) != 0 ) {
                    t.add(nums[i]);
                }
            }
            res.add(new LinkedList<>(t));
        }
        return res;
    }
    
    /*
     分治 + dfs 解法
     */
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        dfs(0, nums, path, res);
        return res;
    }
    
    private static void dfs(int cur, int[] nums, List<Integer> path, List<List<Integer>> res) {
        // 递归结束条件，当所有的情况都考虑完
        if (cur == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        
        // 考虑选择当前位置
        path.add(nums[cur]);
        dfs(cur + 1, nums, path, res);
        
        // 不考虑选择当前位置
        path.remove(path.size() - 1);
        dfs(cur + 1, nums, path, res);
    }
    
}
