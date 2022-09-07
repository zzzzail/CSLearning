package com.java.dsa.zuo;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zail
 * @date 2022/6/30
 */
public class MaxWindow {
    
    public static void main(String[] args) {
        MaxWindow solution = new MaxWindow();
        int[] arr1 = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res1 = solution.getMaxWindow(arr1, 3);
        System.out.println(Arrays.toString(res1));
    }
    
    /**
     * 通过维护一个最大值下表的双端队列，来辅助求出最大值数组
     */
    public int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w < 1) return null;
        
        int n = arr.length;
        // 维护一个最大值下表数组
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[n - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= cur) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            // 弹出前面过期的下表
            if (qmax.peekFirst() == i - w) qmax.pollFirst();
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
