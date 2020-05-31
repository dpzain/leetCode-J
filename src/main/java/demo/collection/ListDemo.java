package demo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @auther zhangyu(dpzain)
 * @date 2020/4/23 22:31
 */
public class ListDemo {


    /**
     * forEach中调用集合的remove方法删除倒数第二个元素 不报错;
     * 底层就是迭代器 查看AbstractList中的迭代器next 和 hasNext源码就知道 删除倒数第二个 size变为size-1 hasNext直接返回false 结束迭代了，之后也就判断不了modCount和expect....
     * 最后一个元素给忽略了
     */
    @Test
    public void removeList(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        for (String s : list) {
            if("2".equals(s)){
                list.remove(s);
            }
            System.out.println(s);
        }

//        for (String s : list) {
//            if("1".equals(s)){
//                list.remove(s);
//            }
//        }

    }

    {
        Executors
    }
}
