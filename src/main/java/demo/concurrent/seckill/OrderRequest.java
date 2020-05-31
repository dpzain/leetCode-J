package demo.concurrent.seckill;

import java.io.Serializable;
import java.util.Random;

/**
 * @auther zhangyu(dpzain)
 * @date 2020/5/16 13:43
 */
public class OrderRequest  implements Serializable {

    private int goodId = new Random().nextInt(100000);//商品ID

    private int userId = new Random().nextInt(10000);//用户ID

    private int status;//0:未处理;1:正常;2:异常;


    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
