package alg.problems;

/**
 * @author zhangxq
 * @since 2022/10/9
 */
public class PolygonUtilsTest {
    
    public static void main(String[] args) {
        // 简单测试
        long[][] contour1 = new long[][]{
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

        // 地图上的三角形
        long[][] fence1 = new long[][]{
                {1182446787, 391226895},
                {1185338612, 391240331},
                {1183746096, 392734509}
        };
        long[] insidePoint1 = {1182659505, 391336609}; // 内部点
        long[] outsidePoint1 = {1181728142, 393493788}; // 外部点
        boolean insideRes1 = PolygonUtils2.insidePolygon(insidePoint1, fence1);
        System.out.println(insideRes1 ? "【正确】点在内部" : "点在外部");
        boolean outsideRes1 = PolygonUtils2.insidePolygon(outsidePoint1, fence1);
        System.out.println(outsideRes1 ? "点在内部" : "【正确】点在外部");

        // 地图上的四边形
        long[][] fence2 = new long[][]{
                {1183026014, 391488837},
                {1182951275, 392598197},
                {1185023845, 392665239},
                {1185156076, 391605223}
        };
        long[] insidePoint2 = {1183373838, 391802142}; // 内部点
        long[] outsidePoint2 = {1182778800, 391197785}; // 外部点
        boolean insideRes2 = PolygonUtils2.insidePolygon(insidePoint2, fence2);
        System.out.println(insideRes2 ? "【正确】点在内部" : "点在外部");
        boolean outsideRes2 = PolygonUtils2.insidePolygon(outsidePoint2, fence2);
        System.out.println(outsideRes2 ? "点在内部" : "【正确】点在外部");

        // 围栏 1
        long[][] fence11 = new long[][]{
                {11848287002363406L, 38956445319342585L},
                {11849287002363405L, 38956445319342585L},
                {11848287002363406L, 38966445319342580L},
        };
        // 内部点
        long[] insideP4 =  {11848587002363406L, 38959445319342580L};
        //外部点
        long[] outsideP4 = {11848587002363406L, 38909445319342580L};
        System.out.println(PolygonUtils2.insidePolygon(insideP4, fence11));
        System.out.println(PolygonUtils2.insidePolygon(outsideP4, fence11));

        // 围栏2
        long[][] fence22 = new long[][]{
                {11848287002363406L, 38956445319342585L},
                {11849287002363405L, 38956445319342585L},
                {11849287002363405L, 38966445319342580L},
                {11848287002363406L, 38966445319342580L},
        };
        // 内部点
        long[] insideP5 =  {11848832130432130L, 38957206297861840L};
        // 外部点
        long[] outsideP5 = {11848587002363406L, 38909445319342580L};
        System.out.println(PolygonUtils2.insidePolygon(insideP5, fence22));
        System.out.println(PolygonUtils2.insidePolygon(outsideP5, fence22));
        
        // 高精度的地图
        long[][] polygon6 = new long[][]{
                {11849398612976076L, 38994972934024110L},
                {11850445747375490L, 38995239764368556L},
                {11850248336791994L, 39002510503993660L},
                {11849372863769533L, 39002043598210280L},
        };
        long[] insideP6 =  {11849836349487305L, 38997507781674905L};
        long[] outsideP6 = {11853415489196779L, 39001376584603115L};
        System.out.println(PolygonUtils2.insidePolygon(insideP6, polygon6));
        System.out.println(PolygonUtils2.insidePolygon(outsideP6, polygon6));
    }
}
