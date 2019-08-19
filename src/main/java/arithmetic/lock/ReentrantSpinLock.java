package arithmetic.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/3/25 16:13
 * 可重入自旋锁  非公平
 */
public class ReentrantSpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<>();

    private  int count;//此变量 不需要线程安全 因为cas 已经 维护一个变量的线程安全(变量为Thread 可以当锁使用)  即只有一个线程可以获取到锁 不存在两个线程同时获取到锁同时操作count

    public void lock(){
        Thread current = Thread.currentThread();
        if(current == cas.get()){//当前线程已经获取到锁
            System.out.println(Thread.currentThread().getName()+"获取锁成功");
            count++;
            return;
        }

        //没有获取到锁  自旋
        while(!cas.compareAndSet(null,current)){
            //DO nothing
            System.out.println(Thread.currentThread().getName()+"正在自旋获取锁");
        }
    }

    public void unlock(){
        Thread cur = Thread.currentThread();
        if(cur == cas.get()){
            if(count>0){
                count--;
            }else{
                cas.compareAndSet(cur,null);
            }
        }
    }
}
