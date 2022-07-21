package com.dsa.leetcode;

public class P24SwapNodesInPairs1 {
    
    public static void main(String[] args) {
        ListNode list = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4, null))));
        ListNode res1 = swapPairs(list);
        System.out.println(res1);
    }
    
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) { return head; }
        // 一次走两步呗
        ListNode oneStep = head;
        ListNode twoStep = head.next;
        while (oneStep != null && twoStep != null) {
            // 交换两个的值就完事了
            int t = oneStep.val;
            oneStep.val = twoStep.val;
            twoStep.val = t;
            oneStep = twoStep.next;
            if (oneStep == null || oneStep.next == null) break;
            else twoStep = oneStep.next;
        }
        return head;
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
