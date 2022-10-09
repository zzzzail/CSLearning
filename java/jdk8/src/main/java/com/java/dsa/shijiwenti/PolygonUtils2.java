package com.java.dsa.shijiwenti;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/10/9
 */
public class PolygonUtils2 {
    
    public static boolean insidePolygon(long[] p, long[][] contour) {
        if (p.length != 2) return false;
        if (contour.length < 3) return false;
        for (int i = 0; i < contour.length; i++) {
            if (contour[i].length != 2) {
                return false;
            }
        }
        
        // 构造 p 点 和 轮廓点
        Point2D _p = new Point2D(p[0], p[1]);
        List<Point2D> _contour = new ArrayList<>();
        for (int i = 0; i < contour.length; i++) {
            long[] conp = contour[i];
            _contour.add(new Point2D(conp[0], conp[1]));
        }
        
        return insidePolygon(_p, _contour);
    }
    
    /**
     * 利用射线法判断点 p 是否在由 contour 轮廓点集组成的多边形内。
     * 点 p 沿着 x 轴向右画一条无限长的射线 A，判断每一条边与射线 A 是否相交，并记录一次，
     * 相交的次数为奇数时，说明点 p 在多边形内，若相交点次数为偶数时，说明点 p 不在多边形内。
     *
     * https://blog.csdn.net/WilliamSun0122/article/details/77994526?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2~default~CTRLIST~default-1-77994526-blog-102737081.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2~default~CTRLIST~default-1-77994526-blog-102737081.pc_relevant_default&utm_relevant_index=1
     *
     * @param p       点 p
     * @param contour 轮廓点集
     * @return 返回 true 则点 p 在多边形内，否则返回 false
     */
    private static boolean insidePolygon(Point2D p, List<Point2D> contour) {
        int n = contour.size();
        boolean flag = false; // 结果
        Point2D p1, p2; // 多边形一条边的两个顶点
        for (int i = 0, j = n - 1; i < n; j = i++) {
            // 给出多边形的两个顶点
            p1 = contour.get(i);
            p2 = contour.get(j);
            // 点在多变形的一条边上
            if (onSegment(p1, p2, p)) {
                return true;
            }
            // 前一个判断 min(p1.y, p2.y) < p.y <= max(p1.y, p2.y)
            // 后一个判断被测点在射线与边交点的左边
            if (((p1.y - p.y > 0) != (p2.y - p.y > 0)) &&
                    (p.x - (p.y - p1.y) * (p1.x - p2.x) / (p1.y - p2.y) - p1.x) < 0) {
                flag = !flag;
            }
        }
        return flag;
    }
    
    private static boolean onSegment(Point2D A, Point2D B, Point2D p) {
        // 前一个判断点 p 在 A -> B 直线上
        // 后一个判断在 A B 范围上
        return A.vectorSubtract(p).vectorX(B.vectorSubtract(p)) == 0
                && A.vectorSubtract(p).vectorMultiply(B.vectorSubtract(p)) <= 0;
    }
    
    static class Point2D {
        public long x;
        public long y;
        
        public Point2D(long x, long y) {
            this.x = x;
            this.y = y;
        }
        
        // 向量 -
        public Point2D vectorSubtract(Point2D p) {
            return new Point2D(x - p.x, y - p.y);
        }
        
        // 向量 +
        public Point2D vectorPlus(Point2D p) {
            return new Point2D(x + p.x, y + p.y);
        }
        
        // 向量 *
        public long vectorMultiply(Point2D p) {
            return x * p.x + y * p.y;
        }
        
        // 向量叉乘
        public long vectorX(Point2D p) {
            return x * p.y - p.x * y;
        }
    }
}
