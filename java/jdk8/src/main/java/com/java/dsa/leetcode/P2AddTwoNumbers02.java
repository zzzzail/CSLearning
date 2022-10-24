package com.java.dsa.leetcode;

/**
 * @author zhangxq
 * @since 2022/10/24
 */
public class P2AddTwoNumbers02 {
    
    public static void main(String[] args) {
    
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int t = 0;
            if (l1 != null) {
                t += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                t += l2.val;
                l2 = l2.next;
            }
            t += carry;
            carry = t / 10;
            t = t % 10;
            cur.next = new ListNode(t);
            cur = cur.next;
        }
        return dummy.next;
    }
    
    static class ListNode {
        public int val;
        public ListNode next;
        
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
