package com.java.dsa.shijiwenti;

/**
 * @author zhangxq
 * @since 2022/10/11
 */
public class LonLatUtils {
    
    // 经度代表一米的距离
    public static double LON_CONVERT_M_UNIT = 0.00001141;
    // 纬度代表一米的距离
    public static double LAT_CONVERT_M_UNIT = 0.00000899;
    // 10 的 8 次方
    public static long TEN_POW_8 = (long) Math.pow(10, 8);
    
    /**
     * 经度转换成米
     *
     * @param lon 经度
     * @return 米
     */
    public static long lonConvert2m(double lon) {
        long unit = (long) (LON_CONVERT_M_UNIT * TEN_POW_8);
        long l = (long) (lon * TEN_POW_8);
        // 四舍五入
        return (l / unit) + (l % unit >= unit / 2 ? 1 : 0);
    }
    
    /**
     * 纬度转换成米
     *
     * @param lat 纬度
     * @return 米
     */
    public static long latConvert2m(double lat) {
        long unit = (long) (LAT_CONVERT_M_UNIT * TEN_POW_8);
        long l = (long) (lat * TEN_POW_8);
        return (l / unit) + (l % unit >= unit / 2 ? 1 : 0);
    }
}
