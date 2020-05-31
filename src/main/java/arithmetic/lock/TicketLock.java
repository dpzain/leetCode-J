package arithmetic.lock;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/3/25 16:51
 * 自旋锁的变种之一   公平自旋锁
 *
 * 缺点:多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum ，
 * 每次读写操作都必须在多个处理器缓存之间进行缓存同步
 * 这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能
 */
public class TicketLock {
    /**
     * 服务号
     */
    private AtomicInteger serviceNum = new AtomicInteger(1);

    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();

    /**
     * 如果获取成功返回当前排队号 用于释放锁
     *
     * @return
     */
    public int lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        while (currentTicketNum != serviceNum.get()) {
            //DO noting 自旋等待锁
        }
        return currentTicketNum;
    }

    /**
     * 释放锁 传入当前持有锁的线程的排队号
     *
     * @param ticketNum 当前占有锁的ticketNum
     */
    public void unlock(int ticketNum) {
        serviceNum.compareAndSet(ticketNum, ticketNum + 1);
    }

    @Test
    public void test(){
        System.out.println(ticketNum.incrementAndGet());
        System.out.println(serviceNum.get());
    }
}


/**
 * 优化:释放锁需要传入占有锁线程的ticketNum   防止更改和线程做绑定即可  利用ThreadLocl
 */
class TicketLock2 {
    /**
     * 服务号
     */
    private AtomicInteger serviceNum = new AtomicInteger();
    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();
    /**
     * 新增一个ThreadLocal，用于存储每个线程的排队号
     */
    private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<>();

    public void lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        // 获取锁的时候，将当前线程的排队号保存起来
        ticketNumHolder.set(currentTicketNum);
        while (currentTicketNum != serviceNum.get()) {
            // Do nothing
        }
    }

    public void unlock() {
        // 释放锁，从ThreadLocal中获取当前线程的排队号
        Integer currentTickNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTickNum, currentTickNum + 1);
    }


}
