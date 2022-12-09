package alg.leetcode;

/**
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 * 使容器容纳最大水量
 * 容量 = 数组长度 * 数值高度
 *
 * 假设 x 为起始位置，y 为最终位置。则
 * 容量 = ( y - x ) * min{height[x], height[y]}
 */
public class P11ContainerWithMostWater1 {
    
    public static void main(String[] args) {
        P11ContainerWithMostWater1 solution = new P11ContainerWithMostWater1();
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
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
                if ( max < ( c = ( j - i ) * Math.min(height[i], height[j]) ) ) {
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
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            int area = (j - i + 1) * minHeight; // 这里莫名其妙加 1 是因为上面的 i++ 和 j-- 造成的
            max = Math.max(max, area);
        }
        return max;
    }
    
}
