package arithmetic.problem;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/3/28 13:14
 * 类似  (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 7 -> 0 -> 8
 * 342 + 465 = 807
 */
public class ListNumber {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode header = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode tmp = header;
        int curry = 0;//进位数

        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = x + y + curry;
            curry = sum / 10;
            tmp.next = new ListNode(sum % 10);
            tmp = tmp.next;

            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }
        //当最后一位加完后还有进位 会创建一个新的节点
        if (curry > 0) {
            tmp.next = new ListNode(curry);
        }
        return header.next;

    }
    public static void main(String[] args) {
        //封装一下 ListNode put 操作即可
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode L1 = new ListNode(5);
        ListNode L2 = new ListNode(6);
        ListNode L3 = new ListNode(4);
        L1.next = L2;
        L2.next = L3;
        ListNode resultNode =addTwoNumbers(l1,L1);

        //封装一下 遍历获取结果即可
        System.out.println(resultNode.val);
        System.out.println(resultNode.next.val);
        System.out.println(resultNode.next.next.val);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        val = value;
    }
}










