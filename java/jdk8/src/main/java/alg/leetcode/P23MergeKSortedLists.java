package alg.leetcode;

/**
 * 合并 K 个升序链表
 *
 * @author zhangxq
 * @link https://leetcode.cn/problems/merge-k-sorted-lists/
 * @since 2022/12/12
 */
public class P23MergeKSortedLists {
    
    public ListNode mergeKLists(ListNode[] lists) {
        
        return null;
    }
    
    public class ListNode {
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
