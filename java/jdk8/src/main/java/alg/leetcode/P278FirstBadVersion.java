package alg.leetcode;

/**
 * @link https://leetcode.cn/problems/first-bad-version/description/?envType=study-plan&id=suan-fa-ru-men&plan=algorithms&plan_progress=407ao9i&orderBy=most_votes
 * @author zhangxq
 * @since 2022/10/22
 */
public class P278FirstBadVersion {
    
    public static void main(String[] args) {
    
    }
    
    // public int firstBadVersion(int n) {
    //     int l = 0, r = n, mid = 0;
    //     while (l < r) {
    //         mid = (r - l) / 2 + l;
    //         if (isBadVersion(mid))
    //             r = mid;
    //         else
    //             l = mid + 1;
    //     }
    //     return l;
    // }
    
    // public int firstBadVersion(int n) {
    //     if (isBadVersion(0)) return 0;
    //     return firstBadVersion(1, n);
    // }
    //
    // public int firstBadVersion(int i, int j) {
    //     if (i > j) return -1;
    //     int mid = (j - i) / 2 + i;
    //     boolean midV = isBadVersion(mid);
    //     boolean midm1V = isBadVersion(mid - 1);
    //     // 找到了第一个坏版本
    //     if (midV && !midm1V) return mid;
    //     if (midV) return firstBadVersion(i, mid - 1);
    //     else return firstBadVersion(mid + 1, j);
    // }
}
