package alg.leetcodejzof;

/**
 * 替换空格
 *
 * @author zail
 * @link https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
 * @date 2022/7/4
 */
public class P05 {
    
    public static void main(String[] args) {
        P05 solution = new P05();
        String res1 = solution.replaceSpace("We are happy.");
        System.out.println(res1);
    }
    
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                res.append("%20");
            }
            else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
