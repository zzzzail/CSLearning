package alg.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 多边形工具类
 *
 * @author zhangxq
 * @link 参考 https://www.flipcode.com/archives/Efficient_Polygon_Triangulation.shtml
 * @since 2022/10/9
 */
public class PolygonUtils {
    
    // ε
    static final float EPSILON = 0.0000000001f;
    
    public static boolean insidePolygon(long[] p, long[][] contour) {
        return insidePolygon(p, contour, true);
    }
    
    public static boolean insidePolygon(long[] p, long[][] contour, boolean precisely) {
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
        
        return insidePolygon(_p, _contour, true);
    }
    
    
    public static boolean insidePolygon(Point2D p, List<Point2D> contour, boolean precisely) {
        if (precisely) {
            return insidePolygonPrecisely(p, contour);
        }
        else {
            // 利用叉乘法求点 p 是否在一个凸多边形内
            return false;
        }
    }
    
    /**
     * 判断 p 点是否在 contour 轮廓点集形成的区域内
     *
     * @param p       点
     * @param contour 轮廓点集
     * @return true 是在区域内，否则返回 false
     */
    private static boolean insidePolygonPrecisely(Point2D p, List<Point2D> contour) {
        // 1. 将有 n 个点的区域 area 划分为 n - 2 个三角形
        // 将 area 区域的点按照连接边的顺序排序
        // 任取一个点，需要保证该点的前后两条边形成的角是锐角
        List<Point2D> res = new ArrayList<>();
        boolean triangulate = triangulate(contour, res);
        if (!triangulate) return false;
        
        // 2. 依次判断每个三角形中是否包含点 p，若出现某个三角形包含点 p，则直接返回 true（贪心）
        // 遍历完所有三角形都不包含点 p 则返回 false。
        int tcount = res.size() / 3;
        for (int i = 0; i < tcount; i++) {
            Point2D p1 = res.get(i * 3 + 0);
            Point2D p2 = res.get(i * 3 + 1);
            Point2D p3 = res.get(i * 3 + 2);
            if (insideTriangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p.x, p.y)) {
                return true;
            }
        }
        return false;
    }
    
    // 根据轮廓 contour 的点集求面积
    public static double area(List<Point2D> contour) {
        int n = contour.size();
        double A = 0.0;
        for (int p = n - 1, q = 0; q < n; p = q++) {
            A += contour.get(p).x * contour.get(q).y - contour.get(q).x * contour.get(p).y;
        }
        return A * 0.5;
    }
    
    /*
      判断点 P 是否在 由 A B C 三个点组成的三角形中。这里使用的是 叉乘法
      InsideTriangle decides if a point P is Inside of the triangle
      defined by A, B, C.
    */
    public static boolean insideTriangle(long Ax, long Ay,
                                         long Bx, long By,
                                         long Cx, long Cy,
                                         long Px, long Py) {
        long ax, ay, bx, by, cx, cy, apx, apy, bpx, bpy, cpx, cpy;
        long cCROSSap, bCROSScp, aCROSSbp; // 计算叉乘的值
        
        ax = Cx - Bx;
        ay = Cy - By;
        bx = Ax - Cx;
        by = Ay - Cy;
        cx = Bx - Ax;
        cy = By - Ay;
        apx = Px - Ax;
        apy = Py - Ay;
        bpx = Px - Bx;
        bpy = Py - By;
        cpx = Px - Cx;
        cpy = Py - Cy;
        
        aCROSSbp = ax * bpy - ay * bpx;
        cCROSSap = cx * apy - cy * apx;
        bCROSScp = bx * cpy - by * cpx;
        
        return ((aCROSSbp >= 0) && (bCROSScp >= 0) && (cCROSSap >= 0));
    }
    
    /**
     * 截取
     *
     * @param contour 轮廓点集
     * @param u       三角形的第一条边 下标
     * @param v       三角形的第二条边 下标
     * @param w       三角形的第三条边 下标
     * @param n       共 n 个边
     * @param V       多边形的点集下表数组
     * @return 截取成功与否
     */
    private static boolean snip(List<Point2D> contour, int u, int v, int w, int n, int[] V) {
        int p;
        long Ax, Ay, Bx, By, Cx, Cy, Px, Py;
        
        Ax = contour.get(V[u]).x;
        Ay = contour.get(V[u]).y;
        Bx = contour.get(V[v]).x;
        By = contour.get(V[v]).y;
        Cx = contour.get(V[w]).x;
        Cy = contour.get(V[w]).y;
        
        if (EPSILON > (((Bx - Ax) * (Cy - Ay)) - ((By - Ay) * (Cx - Ax)))) return false;
        
        for (p = 0; p < n; p++) {
            if ((p == u) || (p == v) || (p == w)) continue;
            Px = contour.get(V[p]).x;
            Py = contour.get(V[p]).y;
            if (insideTriangle(Ax, Ay, Bx, By, Cx, Cy, Px, Py)) return false;
        }
        
        return true;
    }
    
    /**
     * 将一个由轮廓点集组成的多边形分解为多个三角形
     * result 保存生成所有三角的点
     *
     * @param contour 轮廓点集
     * @param result  结果集
     * @return 成功返回 true，否则返回 false
     */
    private static boolean triangulate(List<Point2D> contour, List<Point2D> result) {
        /* allocate and initialize list of Vertices in polygon */
        int n = contour.size();
        if (n < 3) return false;
        
        // 带有顺序的点集下标
        int[] V = new int[n];
        
        /* we want a counter-clockwise polygon in V */
        /* 逆时针轮廓点集 */
        if (0 < area(contour))
            for (int v = 0; v < n; v++) V[v] = v;
        else
            for (int v = 0; v < n; v++) V[v] = (n - 1) - v;
        
        int nv = n;
        
        /*  remove nv-2 Vertices, creating 1 triangle every time */
        int count = 2 * nv;   /* 错误检查 error detection */
        
        for (int m = 0, v = nv - 1; nv > 2; ) {
            /* if we loop, it is probably a non-simple polygon */
            if (0 >= (count--)) {
                //** Triangulate: ERROR - probable bad polygon!
                return false;
            }
            
            // 当前多边形中的三个连续顶点 <u,v,w>
            /* three consecutive vertices in current polygon, <u,v,w> */
            int u = v;
            if (nv <= u) u = 0;     /* previous */
            v = u + 1;
            if (nv <= v) v = 0;     /* new v    */
            int w = v + 1;
            if (nv <= w) w = 0;     /* next     */
            
            if (snip(contour, u, v, w, nv, V)) {
                int a, b, c, s, t;
                
                /* true names of the vertices */
                a = V[u];
                b = V[v];
                c = V[w];
                
                /* output Triangle 输出三角形的三条边 */
                result.add(contour.get(a));
                result.add(contour.get(b));
                result.add(contour.get(c));
                
                m++;
                
                /* remove v from remaining polygon */
                for (s = v, t = v + 1; t < nv; s++, t++) V[s] = V[t];
                nv--;
                
                /* resest error detection counter */
                count = 2 * nv;
            }
        }
        
        return true;
    }
    
    static class Point2D {
        public long x;
        public long y;
        
        public Point2D(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
