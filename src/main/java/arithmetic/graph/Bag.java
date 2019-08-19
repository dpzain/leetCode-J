package arithmetic.graph;

import java.util.Iterator;

//我们有⼀一个有向⽆无环图，权重在节点上。
//需求：从⼀一个起点开始，找到⼀一条节点权重之和最⼤大的最优路路径。
//输⼊入: n个节点，m个路路径，起点
//输出: 最优路路径的权重值之和
//举例例:
//3个节点与权重: A=1, B=2, C=2
//3条路路径: A->B, B->C, A->C
//起点: A
//输出: 5 (最优路路径是 A->B->C ， 权重之和是 1+2+2=5)
//请考虑算法效率优化，考虑异常情况（⽐比如输⼊入的图有环路路）要避免死循环或者崩溃。
//---------------------
// 定义一个背包集合，支持泛型，支持迭代
@SuppressWarnings("all")
public class Bag<Item> implements Iterable<Item> {

    private class BagNode<Item> {
        Item item;
        BagNode next;
    }

    BagNode head;
    int size;

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            BagNode node = head;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public Item next() {
                Item item = (Item) node.item;
                node = node.next;
                return item;
            }
        };
    }

    public Bag() {
        head = new BagNode();
        size = 0;
    }

    // 往前插入
    public void add(Item item) {
        BagNode temp = new BagNode();
        // 以下两行代码一定要声明，不可直接使用temp = head，那样temp赋值的是head的引用，对head的所有修改会直接同步到temp，temp就不具备缓存的功能，引发bug。。
        temp.next = head.next;
        temp.item = head.item;
        head.item = item;
        head.next = temp;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

}

@SuppressWarnings("all")
class BagData {
    private Integer index;
    private String nodeName;
    private Integer nodeValue;

    public BagData() {

    }

    public BagData(Integer index, String nodeName, Integer nodeValue) {
        super();
        this.index = index;
        this.nodeName = nodeName;
        this.nodeValue = nodeValue;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(Integer nodeValue) {
        this.nodeValue = nodeValue;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "BagData [index=" + index + ", nodeName=" + nodeName + ", nodeValue=" + nodeValue + "]";
    }

}