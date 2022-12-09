package demo;

public class OperationsDemo {
    
    public static void main(String[] args) {
        // 二进制左移动（每移动一位相当于一次/2）
        // 左移动是带符号的，正数右移高位补0；负数右移高位补1
        int a = 10 >> 1;
        System.out.println(a);
        
        // 二进制右移动（每移动一位相当于一次*2）
        // 带符号的右移，正数右移高位补0；负数右移高位补1
        a = 2 << 1;
        System.out.println(a);
    
        /*
            无符号右移。无论是正数还是负数，高位通通补0。
            对于正数而言 >> 和 >>> 没有任何区别。
            对付负数而言 >> 是符号不变，数字除以2
                       >>> 整个二进制向右移动，符号也会跟着移动。
         */
        System.out.println("-1 >>> 1 = " + (-1 >>> 1));
        System.out.println("-2 >>> 1 = " + (-2 >>> 1));
    
        System.out.println(BinaryUtils.getBinaryValue(0xffffffff));
        System.out.println(BinaryUtils.getBinaryValue(Integer.MAX_VALUE));
        System.out.println(BinaryUtils.getBinaryValue(Integer.MIN_VALUE));
        
        System.out.println(BinaryUtils.getBinaryValue(10));
        
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        
    }
}
