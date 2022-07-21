package com.dsa.leetcode.jzof;

import java.util.ArrayList;
import java.util.List;

/**
 * 从尾到头打印链表
 *
 * @author zail
 * @link https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * @date 2022/6/30
 */
public class P06 {
    
    public static void main(String[] args) {
    
    }
    
    List<Integer> list = new ArrayList<>();
    public int[] reversePrint(ListNode head) {
        recursion(head);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    
    // 递归打印，性能好差
    public void recursion(ListNode head) {
        if (head == null) return;
        reversePrint(head.next);
        list.add(head.val);
    }
    
    public class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
    }
}
