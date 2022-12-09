package alg.problems;

/**
 * @author zhangxq
 * @since 2022/10/11
 */
public class CircleUtils {
    
    /**
     * 判断点 p 是否在以 o 为圆心以 r 为半径组成的圆中
     *
     * @param p 判断点
     * @param o 圆心
     * @param r 半径
     * @return 点 p 是否在圆中，若在返回 true，若不在则返回 false
     */
    public static boolean insideCircle(Point2D p, Point2D o, long r) {
        // 点 p 到 o 的距离
        long l = (long) (Math.pow(o.x - p.x, 2) + Math.pow(o.y - p.y, 2));
        // 若在圆内则点 p 到 o 的距离不超过 r
        return l <= r;
    }
    
}
