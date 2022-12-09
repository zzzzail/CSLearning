package alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @link https://leetcode.cn/problems/merge-intervals/
 * 合并区间
 */
public class P56MergeIntervals1 {
    
    /**
     * 归并排序方法
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>(intervals.length);
        if (intervals.length == 0) return res.toArray(new int[0][]);
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        res.add(intervals[0]);
        int idx = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] > res.get(idx)[1]) { // 不合并
                res.add(new int[]{interval[0], interval[1]});
                ++idx;
            }
            else { // 合并
                res.get(idx)[1] = Math.max(res.get(idx)[1], interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    
}
