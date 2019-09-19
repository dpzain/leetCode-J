package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/8/13 23:42
 */
public class TestClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String o : list) {
            if(o.equals("2"))
                list.remove(o);
        }
        System.out.println(Arrays.toString(list.toArray()));
    }
}
