package demo.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * 内置函数式接口
 *
 * @auther zhangyu(dpzain)
 * @date 2019/8/22 21:24
 */
public class DemoFunctionInteface {

    @Test
    public void testConsumer() {
        consumo(500, (x) -> System.out.println(x + 500));
    }

    private void consumo(double money, Consumer<Double> c) {
        c.accept(money);
    }

    /**
     * 供给型接口，Supplier<T>
     */
    @Test
    public void testSupplier() {
        Random ran = new Random();
        List<Integer> list = supplier(10, () -> ran.nextInt(10));

        for (Integer i : list) {
            System.out.println(i);
        }
    }

    /**
     * 随机产生sum个数量得集合
     *
     * @param sum 集合内元素个数
     * @param sup
     * @return
     */
    public List<Integer> supplier(int sum, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            list.add(sup.get());
        }
        return list;
    }

    /**
     * 函数型接口：Function<R, T>
     */
    @Test
    public void test3() {
        String s = strOperar(" asdf ", x -> x.substring(0, 2));
        System.out.println(s);
        String s1 = strOperar(" asdf ", String::trim);
        System.out.println(s1);
    }

    /**
     * 字符串操作
     *
     * @param str 需要处理得字符串
     * @param fun Function接口
     * @return 处理之后得字符传
     */
    public String strOperar(String str, Function<String, String> fun) {
        return fun.apply(str);
    }


    /**
     * 断言型接口：Predicate<T>
     */
    @Test
    public void predicateTest() {
        List<Integer> l = new ArrayList<>();
        l.add(102);
        l.add(172);
        l.add(13);
        l.add(82);
        l.add(109);
        List<Integer> list = filterInt(l, x -> (x > 100));
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * 过滤集合
     *
     * @param list
     * @param pre
     * @return
     */
    public List<Integer> filterInt(List<Integer> list, Predicate<Integer> pre) {
        List<Integer> l = new ArrayList<>();
        for (Integer integer : list) {
            if (pre.test(integer))
                l.add(integer);
        }
        return l;
    }

//    public static void main(String[] args) {
//        Function<Integer, Integer> name = e -> e * 2;
//        Function<Integer, Integer> square = e -> e * e;
//        int value = name.andThen(square).apply(3);
//        System.out.println("andThen value=" + value);
//        int value2 = name.compose(square).apply(3);
//        System.out.println("compose value2=" + value2);
//        Object identity = Function.identity().apply("huohuo");
//        System.out.println(identity);
//
//        System.out.println(name.compose(square).andThen(name).compose(name).apply(3));
//
//
//    }
//


    public static void main(String[] args) {
        Student s1 = new Student("Shyam", 22, "A");
        Student s2 = new Student("Ram", 23, "A");
        Student s3 = new Student("Mohan", 22, "B");
        Student s4 = new Student("Ramesh", 21, "B");
        List<Student> list = Arrays.asList(s1, s2, s3, s4);
        Comparator<Student> ageComparator = Comparator.comparing(Student::getAge);
        //Using BinaryOperator.maxBy
        System.out.println("---BinaryOperator.maxBy---");
        Map<String, Optional<Student>> eldestByClass = list.stream().collect(Collectors.groupingBy(Student::getClassName,
                Collectors.reducing(BinaryOperator.maxBy(ageComparator))));
        eldestByClass.forEach((k, v) -> System.out.printf("Class:%s Age:%d Name:%s%n", k, v.get().getAge(), v.get().getName()));

        //Using BinaryOperator.minBy
        System.out.println("---BinaryOperator.minBy---");
        Map<String, Optional<Student>> youngestByClass = list.stream().collect(Collectors.groupingBy(Student::getClassName,
                Collectors.reducing(BinaryOperator.minBy(ageComparator))));
        youngestByClass.forEach((k, v) -> System.out.printf("Class:%s Age:%d Name:%s%n", k, v.get().getAge(), v.get().getName()));
    }

    static class Student {
        private String name;
        private Integer age;
        private String className;

        public Student(String name, Integer age, String className) {
            this.name = name;
            this.age = age;
            this.className = className;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }


}
