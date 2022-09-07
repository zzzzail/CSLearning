package com.java.base;

public class BinaryUtils {
    
    /**
     * 0xffffffff 的二进制值是全1的
     * n & (0xffffffff >>> i) 意思是n保留从第i位到最低位中的1后的数字
     */
    public static String getBinaryValue(int n) {
        StringBuilder result = new StringBuilder();
        int b = 32;
        for (int i = 0; i < b; i++) {
            int t = ( n & 0xffffffff >>> i ) >>> ( 31 - i );
            result.append(t);
            // 每隔4位加一个空格
            if (i != 0 && i != (b - 1) && (i + 1) % 4 == 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }
    
}
