package alg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @link https://leetcode-cn.com/problems/linked-list-cycle/
 * 环形链表
 */
public class P141LinkedListCycle1 {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 快慢指针法
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) { return false; }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
        }
        return false;
    }
    
    // 解题思路1：利用HashMap的key值唯一特性，判断链表中是否有换
    public boolean hasCycle2(ListNode head) {
        // 边界
        if (head == null || head.next == null) {
            return false;
        }
        Map<ListNode, Object> map = new HashMap<>();
        ListNode p = head;
        while (p != null) {
            if (map.containsKey(p)) {
                return true;
            }
            map.put(p, null);
            p = p.next;
        }
        return false;
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
