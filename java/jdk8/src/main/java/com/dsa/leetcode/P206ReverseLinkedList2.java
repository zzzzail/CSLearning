package com.dsa.leetcode;

public class P206ReverseLinkedList2 {
    
    public static void main(String[] args) {
        ListNode list = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4, null))));
        ListNode res1 = reverseList(list);
        System.out.println(res1);
    }
    
    /**
     * 反转链表
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode res = new ListNode(head.val, null);
        ListNode cur = head.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = res;
            res = cur;
            cur = next;
        }
        return res;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {
        }
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
