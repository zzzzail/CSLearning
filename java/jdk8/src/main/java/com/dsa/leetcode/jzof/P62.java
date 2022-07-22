package com.dsa.leetcode.jzof;

/**
 * 圆圈中最后剩下的数字
 *
 * @author zail
 * @link https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 * @date 2022/7/22
 */
public class P62 {
    
    public static void main(String[] args) {
    
    }
    
    public int lastRemaining(int n, int m) {
        int res = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }
}
