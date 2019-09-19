package demo.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/9/5 22:17
 */
public class DemoComparator {

    @Test
    public void testComparator1(){
         List<User> users = Lists.newArrayList(
                new User("jack",17,10),
                new User("jack",18,10),
                new User("jack",19,11),
                new User("apple",25,15),
                new User("tommy",23,8),
                new User("jessica",15,13)
        );
        users.sort(Comparator.comparingInt(User::getAge));

        users.sort((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                if (o1.getAge().equals(o2.getAge())) {
                    return o1.getAge() - o2.getAge();
                } else {
                    return o1.getCredits() - o2.getCredits();
                }
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });
        users.sort(Comparator.comparing(User::getName)
                .thenComparing(User::getAge)
                .thenComparing(User::getCredits));

    }

}
 class User {
    private String name;
    private Integer age;
    private Integer credits;

     public User(String name, Integer age, Integer credits) {
         this.name = name;
         this.age = age;
         this.credits = credits;
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

     public Integer getCredits() {
         return credits;
     }

     public void setCredits(Integer credits) {
         this.credits = credits;
     }

     @Override
     public String toString() {
         return "User{" +
                 "name='" + name + '\'' +
                 ", age=" + age +
                 ", credits=" + credits +
                 '}';
     }
 }
