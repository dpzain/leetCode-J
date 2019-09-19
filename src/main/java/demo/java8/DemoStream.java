package demo.java8;

import org.junit.Test;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/9/5 23:13
 */
public class DemoStream {

    @Test
    public void testStream1(){
        Stream<Double> generateB = Stream.generate(Math::random);
        generateB.forEach(System.out::println);
        Stream.of(1,2,3,1,2,3)
                .distinct()
                .forEach(System.out::println); // 打印结果：1，2，3
    }
}
