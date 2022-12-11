package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 合并两个有序链表
 */
public class P21MergeTwoSortedLists {
    
    public static void main(String[] args) {
    
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界条件
        if (l1 == null || l2 == null) return l1 != null ? l1 : l2;
        
        ListNode head = l1.val <= l2.val ? l1 : l2;
        
        ListNode cur1 = head == l1 ? l1 : l2;
        ListNode cur2 = head != l1 ? l1 : l2;
        
        ListNode pre = null;
        ListNode next = null;
        
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre = cur1;
                cur1 = cur1.next;
            }
            else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        
        pre.next = cur1 != null ? cur1 : cur2;
        
        return head;
    }
    
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        
        // 边界条件，如果任何一个链表为空，则直接返回另一个不为空的链表即可
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;
        
        // 选择一个头节点值小的节点作为新的链表头
        ListNode head = l1.val <= l2.val ? l1 : l2;
        
        // cur1指向头节点较小的链表值，cur2指向另一个链表
        ListNode cur1 = head == l1 ? l1 : l2;
        ListNode cur2 = head != l1 ? l1 : l2;
        
        // 新建cur1的pre节点，和cur2的next节点，用来处理数据
        ListNode pre = null;
        ListNode next = null;
        
        // 开始遍历，两个指针有一个为空，即终止遍历
        while (cur1 != null && cur2 != null) {
            // 我们始终认为cur1是小的节点
            if (cur1.val <= cur2.val) {
                pre = cur1;
                cur1 = cur1.next;
            }
            else {
                next = cur2.next;
                pre.next = cur2; // pre 的下一个节点是cur2
                cur2.next = cur1; // pre 的下一个节点的下一个节点是cur1
                pre = cur2;
                cur2 = next;
            }
        }
        
        // 追加上没有遍历的值
        pre.next = cur1 != null ? cur1 : cur2;
        
        return head;
    }
    
    // 使用虚拟头节点解决特判的问题（头节点会改变的情况下，可使用虚拟头节点）
    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        return null;
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
