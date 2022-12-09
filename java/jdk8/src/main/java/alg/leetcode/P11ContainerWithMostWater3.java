package alg.leetcode;

public class P11ContainerWithMostWater3 {
    
    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int max = maxArea2(height);
        System.out.println(max); // 49
    }
    
    /**
     * 最大容量
     * 前后双指针
     */
    public static int maxArea2(int[] height) {
        int max = 0, front = 0, rear = height.length - 1;
        while (front < rear) {
            int minHeight = height[front] < height[rear] ? height[front++] : height[rear--];
            int area = minHeight * (rear - front + 1);
            max = Math.max(max, area);
        }
        return max;
    }
}
