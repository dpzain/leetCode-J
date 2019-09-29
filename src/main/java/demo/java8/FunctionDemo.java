package demo.java8;

import java.util.function.Function;

/**
 * @Author dpzain
 * @Description
 * @Date Created in 10:56 2019/8/26
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> doubles= e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        System.out.println(doubles.compose(square).andThen(doubles).compose(doubles).apply(3));
        System.out.println(square.compose(square).andThen(doubles).compose(doubles).andThen(doubles).compose(square).apply(3));
//        System.out.println(((3*3)*2)^2*2*2*2);
    }
}
