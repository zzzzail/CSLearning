package alg.leetcode;

/**
 * 两数相加
 *
 * @author zail
 * @link <a href="https://leetcode.cn/problems/add-two-numbers/">两数相加</a>
 * @date 2022/5/27
 */
public class P2AddTwoNumbers01 {
    
    public static void main(String[] args) {
        P2AddTwoNumbers01 solution = new P2AddTwoNumbers01();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res1 = solution.addTwoNumbers(l1, l2);
        System.out.println(res1);
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode cur = res;
        boolean carry = false;   // 标记是否进位
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            if (carry) ++sum;    // 若进位则和要加一
            carry = sum >= 10;   // 判断是否进位
            sum %= 10;           // 和
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry) cur.next = new ListNode(1);
        return res.next;
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
