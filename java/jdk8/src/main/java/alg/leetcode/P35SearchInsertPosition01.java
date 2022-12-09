package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/search-insert-position/?envType=study-plan&id=suan-fa-ru-men&plan=algorithms&plan_progress=407ao9i
 * @author zhangxq
 * @since 2022/10/22
 */
public class P35SearchInsertPosition01 {
    
    public static void main(String[] args) {
    
    }
    
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length, mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (target > nums[mid]) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }
}
