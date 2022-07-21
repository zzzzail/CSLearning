package com.dsa.leetcode.jzof;

/**
 * 链表中倒数第 k 个节点
 *
 * @author zail
 * @link https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * @date 2022/7/18
 */
public class P22 {
    
    public static void main(String[] args) {
        P22 solution = new P22();
        
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
    
        System.out.println(solution.getKthFromEnd(head1, 2));
    }
    
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        
        int num = k;
        ListNode p = head;
        while (p != null) {
            num--;
            p = p.next;
        }
        if (num > 1) return null;
        else if (num == 1) return head;
        else {
            p = head;
            while (p != null) {
                num++;
                if (num == 1) return p;
                p = p.next;
            }
        }
        return null;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
    }
}
