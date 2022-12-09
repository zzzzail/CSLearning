package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/reverse-linked-list/
 * 反转链表
 */
public class P206ReverseLinkedList1 {
    
    public static void main(String[] args) {
        ListNode list = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4, null))));
        ListNode res1 = reverseList(list);
        System.out.println(res1);
    }
    
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode tail = new ListNode(head.val, null);
        ListNode cur = head.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = tail;
            tail = cur;
            cur = next;
        }
        return tail;
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
