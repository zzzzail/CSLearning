package alg.others;

import java.util.LinkedList;

/**
 * 最大值减去最小值小于或等于 num 的子数组数量
 * <p>
 * 给定一个数组 arr 和一正整数 num，返回有多少个子数组满足如下情况：
 * max(arr[i...j]) - min(arr[i...j]) <= num
 * max(arr[i...j]) 是 arr 的子数组 arr[i] ~ arr[j] 的最大值
 * min(arr[i...j]) 是 arr 的子数组 arr[i] ~ arr[j] 的最小值
 *
 * @author zail
 * @date 2022/6/30
 */
public class P10 {
    
    public static void main(String[] args) {
        P10 solution = new P10();
        int res1 = solution.getNum(new int[]{1, 2, 100}, 1);
        System.out.println(res1);
    }
    
    // 维护一个最大值下标数组
    private LinkedList<Integer> qmax = new LinkedList<>();
    
    // 维护一个最小值下表数组
    private LinkedList<Integer> qmin = new LinkedList<>();
    
    public int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) return 0;
    
        int n = arr.length;
        int res = 0;
        int i = 0, j = 0;
        while (i < n) {
            while (j < n) {
                int cur = arr[j];
                if (qmin.isEmpty() || qmax.peekLast() != j) {
                    while (!qmin.isEmpty() && arr[qmin.peekLast()] >= cur) {
                        qmin.pollLast();
                    }
                    qmin.addLast(j);
                    while (!qmax.isEmpty() && arr[qmax.peekLast()] <= cur) {
                        qmax.pollLast();
                    }
                    qmax.addLast(j);
                }
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            
            res += j - i;
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            i++;
        }
        return res;
    }
}
