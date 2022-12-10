package alg.leetcode;

import java.util.logging.Level;

/**
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 * 使容器容纳最大水量
 * 容量 = 数组长度 * 数值高度
 * <p>
 * 假设 x 为起始位置，y 为最终位置。则
 * 容量 = ( y - x ) * min{height[x], height[y]}
 */
public class P11ContainerWithMostWater {
    
    public static void main(String[] args) {
        P11ContainerWithMostWater solution = new P11ContainerWithMostWater();
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int max = solution.maxArea2(height);
        System.out.println(max);
    }
    
    /**
     * 暴力解法
     * 时间复杂度 O(n ^ 2)
     * 空间复杂度 O(1)
     * 暴力解法时间复杂度太高了，以至于执行超出时间限制。
     */
    public int maxArea(int[] height) {
        int max = 0;
        int c = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (max < (c = (j - i) * Math.min(height[i], height[j]))) {
                    max = c;
                }
            }
        }
        return max;
    }
    
    /**
     * 左右指针法
     * 左右指针分别指向数组的左右两端，循环
     * 每轮将左右指针两者的短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；
     * 即可获得最大面积。
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int maxArea2(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] > height[j]) j--;
            else i++;
        }
        return max;
    }
    
}
