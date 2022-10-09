package com.java.dsa.shijiwenti;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxq
 * @since 2022/10/9
 */
public class PolygonUtilsTest {
    
    public static void main(String[] args) {
        // 简单测试
        long[][] contour1 = new long[][] {
                {0, 0},
                {0, 10},
                {10, 10},
                {10, 0}
        };
        long[] p1 = {0, 0};
        System.out.println(PolygonUtils2.insidePolygon(p1, contour1));
        long[] p2 = {2, 2};
        System.out.println(PolygonUtils2.insidePolygon(p2, contour1));
        long[] p3 = {10, 11};
        System.out.println(PolygonUtils2.insidePolygon(p3, contour1));
        
        // 围栏 1
        long[][] fence1 = new long[][]{
                {11848287002363406L, 38956445319342585L},
                {11849287002363405L, 38956445319342585L},
                {11848287002363406L, 3896644531934258L}
        };
        long[] insidePoint1 =  {11848587002363406L, 3895944531934258L}; // 内部点
        long[] outsidePoint1 = {11848587002363406L, 3890944531934258L}; // 外部点
        boolean insideRes1 = PolygonUtils2.insidePolygon(insidePoint1, fence1);
        System.out.println(insideRes1 ? "【正确】点在内部" : "点在外部");
        boolean outsideRes1 = PolygonUtils2.insidePolygon(outsidePoint1, fence1);
        System.out.println(outsideRes1 ? "点在内部" : "【正确】点在外部");
        
        // 围栏2
        long[][] fence2 = new long[][]{
                {11848287002363406L, 38956445319342585L},
                {11849287002363405L, 38956445319342585L},
                {11849287002363405L, 3896644531934258L},
                {11848287002363406L, 3896644531934258L}
        };
        long[] insidePoint2 =  {11848587002363406L, 3895944531934258L}; // 内部点
        long[] outsidePoint2 = {11848587002363406L, 3890944531934258L}; // 外部点
        boolean insideRes2 = PolygonUtils2.insidePolygon(insidePoint2, fence2);
        System.out.println(insideRes2 ? "【正确】点在内部" : "点在外部");
        boolean outsideRes2 = PolygonUtils2.insidePolygon(outsidePoint2, fence2);
        System.out.println(outsideRes2 ? "点在内部" : "【正确】点在外部");
    }
}
