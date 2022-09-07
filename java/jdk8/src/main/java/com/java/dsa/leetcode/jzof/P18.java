package com.java.dsa.leetcode.jzof;

/**
 * 删除链表的节点
 *
 * @author zail
 * @link https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 * @date 2022/7/18
 */
public class P18 {
    
    public static void main(String[] args) {
        P18 solution = new P18();
        
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(5);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(9);
        System.out.println(solution.deleteNode(head1, 5));
    }
    
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        // 删除 head 节点
        if (head.val == val) return head.next;
        
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next; // 删除节点
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
    }
}
