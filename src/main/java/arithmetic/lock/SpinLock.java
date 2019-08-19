package arithmetic.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/3/25 16:09
 * 简单自旋锁  不可重入  非公平
 */
public class SpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<>();
    public void lock(){
        Thread current = Thread.currentThread();
        //利用CAS
        while (!cas.compareAndSet(null,current)){
            //DO noting 循环等待获取锁
        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        cas.compareAndSet(current,null);
    }

}

