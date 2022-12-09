package demo;

import sun.java2d.Disposer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo {
    
    public static void main(String[] args) {
        
        /*
        lambda 本质就是一个快速实现的类
        Runnable 是一个只有一个方法的接口，且使用 @FunctionalInterface 注释
         */
        new Thread(() -> System.out.println("1"));
        
        Runnable run = Disposer::new;
        
        List<Integer> list = new ArrayList<>();
        list.add(1);
        
        List<Boolean> list2 = list
                .stream()
                .map((a) -> a.equals(1))
                .collect(Collectors.toList());
        
    }
    
    public static void test() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        // 返回符合条件的stream
        Stream<String> stringStream = strings.stream().filter("abc"::equals);
        // 计算流符合条件的流的数量
        long count = stringStream.count();
        
        // forEach遍历->打印元素
        // strings.stream().forEach(System.out::println);
        strings.forEach(System.out::println);
        
        // limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1);
        // toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);
        System.out.println(array);
        
        // map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");
        
        // sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);
        
        // Collectors collect 把abc放入容器中
        List<String> collect = strings.stream().filter("abc"::equals).collect(Collectors.toList());
        // 把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        
        // 对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);
        
        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + statistics.getMax());
        System.out.println("列表中最小的数 : " + statistics.getMin());
        System.out.println("平均数 : " + statistics.getAverage());
        System.out.println("所有数之和 : " + statistics.getSum());
        
        // concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(), strings.stream()).count();
        
        // 注意 一个Stream只能操作一次，不能断开，否则会报错。
        Stream stream = strings.stream();
        // 第一次使用
        stream.limit(2);
        // 第二次使用
        stream.forEach(System.out::println);
        // 报错 java.lang.IllegalStateException: stream has already been operated upon or closed
        
        // 但是可以这样, 连续使用
        stream.limit(2).forEach(System.out::println);
    }
}
