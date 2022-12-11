package alg.leetcode;

/**
 * @author zhangxq
 * @since 2022/12/11
 */
public class P18RemoveNthCodeFromEndOfList {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        // 边界条件
        if (head == null) return head;
        
        // 前面的一个指针
        ListNode pre = new ListNode(Integer.MIN_VALUE);
        pre.next = head;
        
        // start和end指针
        ListNode start = pre;
        ListNode end = pre;
        
        // end指针先移动n个位置
        while (n != 0) {
            end = end.next;
            n--;
        }
        
        // start和end指针一起移动，直到end指针指向最后一个节点
        while (end.next != null) {
            start = start.next;
            end = end.next;
        }
        
        // 删除倒数第n个节点
        start.next = start.next.next;
        
        return pre.next;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
    }
}
