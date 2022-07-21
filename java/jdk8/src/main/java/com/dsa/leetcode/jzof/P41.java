package com.dsa.leetcode.jzof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zail
 * @date 2022/7/21
 */
public class P41 {
    
    public static void main(String[] args) {
        MedianFinder solution = new MedianFinder();
        solution.addNum(1);
        solution.addNum(2);
        solution.addNum(10);
        solution.addNum(9);
        solution.addNum(8);
        solution.addNum(7);
        solution.addNum(6);
        solution.addNum(5);
        solution.addNum(4);
        solution.addNum(3);
        System.out.println(solution.list);
    }
    
    static
    class MedianFinder {
        private List<Integer> list;
        
        public MedianFinder() {
            this.list = new ArrayList<>();
        }
        
        public void addNum(int num) {
            list.add(num);
            int n = list.size();
            // 类似插入排序
            for (int i = n - 1; i > 0; i--) {
                if (list.get(i) < list.get(i - 1)) {
                    Integer t = list.get(i);
                    list.set(i, list.get(i - 1));
                    list.set(i - 1, t);
                }
                else break;
            }
        }
        
        public double findMedian() {
            int n = list.size();
            double median = 0;
            if (list.size() % 2 == 0) {
                median = (list.get(n / 2) + list.get(n / 2 - 1)) / 2.0D;
            }
            else {
                median = list.get(n / 2);
            }
            return median;
        }
    }
}
