package demo.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Author dpzain
 * @Description Java 信号量Semaphore 实现对象池;对象池的'限流';
 * @Date Created in 11:30 2019/9/26
 */
public class ObjPool<T, R> {
    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;

    // 构造函数
    ObjPool(int size, T t) {
        pool = new Vector<T>() {
        };
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
        
    }

    // 利用对象池的对象，调用 func
    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

    public static void main(String[] args) {
        // 创建对象池
        ObjPool<Long, String> pool =
                new ObjPool<>(10, 2L);
        // 通过对象池获取 t，之后执行
        try {
            pool.exec(t -> {
                System.out.println(t);
                return t.toString();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
