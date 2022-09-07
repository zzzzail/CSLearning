package com.java.dsa.leetcode.jzof;

/**
 * 合并两个排序的链表
 *
 * @author zail
 * @link https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 * @date 2022/7/18
 */
public class P25 {
    
    public static void main(String[] args) {
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        
        ListNode l1p = l1;
        ListNode l2p = l2;
        ListNode res = null;
        if (l1p.val <= l2p.val) {
            res = new ListNode(l1p.val);
            l1p = l1p.next;
        }
        else {
            res = new ListNode(l2p.val);
            l2p = l2p.next;
        }
        ListNode resp = res;
        while (l1p != null && l2p != null) {
            if (l1p.val <= l2p.val) {
                resp.next = new ListNode(l1p.val);
                l1p = l1p.next;
            }
            else {
                resp.next = new ListNode(l2p.val);
                l2p = l2p.next;
            }
            resp = resp.next;
        }
        if (l1p != null) resp.next = l1p;
        else if (l2p != null) resp.next = l2p;
        return res;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
    }
}
