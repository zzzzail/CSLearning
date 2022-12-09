package alg.leetcodejzof;

/**
 * 反转链表
 *
 * @author zail
 * @link https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
 * @date 2022/6/30
 */
public class P24 {
    
    public static void main(String[] args) {
    
    }
    
    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        
        ListNode p = head;
        while (p != null) {
            ListNode previous = new ListNode(p.val);
            previous.next = res;
            res = previous;
            p = p.next;
        }
        
        return res;
    }
    
    public class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
    }
    
}
