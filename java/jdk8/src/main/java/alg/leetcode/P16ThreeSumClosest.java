package alg.leetcode;

import java.util.Arrays;

/**
 * 最接近的三数之和
 *
 * @author zhangxq
 * @link https://leetcode.cn/problems/3sum-closest/
 * @since 2022/12/9
 */
public class P16ThreeSumClosest {
    
    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 2, 1, -4};
        int res1 = threeSumClosest(nums1, 1);
        System.out.println(res1);
    }
    
    /**
     * 使用排序 + 双指针
     * @link https://leetcode.cn/problems/3sum-closest/solutions/6959/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum > target)
                    end--;
                else if (sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
}
