package arithmetic.graph;


import java.util.*;


public class Digraph {
    private final int V;// 顶点总数，定义final，第一次初始化以后不可更改。
    private int E;// 边总数
    private Bag<BagData>[] adj;// {邻接表}顶点为数组下标，值为当前下标为顶点值所连通的顶点个数。

    private Map<Integer, BagData> points;// = new HashMap<>();

    public Digraph(int v) {
        points = new HashMap<>();
        this.V = v;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<BagData>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }


    public Map<Integer, BagData> getPoints() {
        return points;
    }

    /**
     * v和w是两个顶点，中间加一条边，增加稠密度。
     *
     * @param v 大V是顶点总数，v是顶点值，所以并v不存在大小限制
     * @param w 同上。
     */
    public void addEdge(BagData v, BagData w) {
        if (!points.containsKey(v.getIndex()))
            points.put(v.getIndex(), v);
        if (!points.containsKey(w.getIndex()))
            points.put(w.getIndex(), w);

        adj[v.getIndex()].add(w);
        E++;
    }

    /**
     * 返回一个顶点的连通顶点集合的迭代器
     *
     * @param v
     * @return Bag本身就是迭代器，所以返回该顶点的连通顶点集合Bag即可。
     */
    public Iterable<BagData> adj(int v) {
        return adj[v];
    }

    /**
     * 将图中所有方向反转
     *
     * @return 返回一个图将所有方向反转后的副本
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (BagData w : adj[v]) {// 遍历原图中跟v顶点连通的顶点w。
                R.addEdge(w, points.get(v));
            }
        }
        return R;
    }

    /**
     * 按照邻接表数组结构输出有向图内容
     *
     * @return
     */
    public String toString() {
        String s = points.size() + " vertices, " + E + " edges\n";
        for (int v = 0; v < points.size(); v++) {
            s += points.get(v).toString() + ": ";
            for (BagData w : this.adj(v)) {
                s += w.toString();
            }
            s += "\n";
        }
        return s;
    }

    public String getWeightPath() {
        Map<Integer, String> weightPaths = new HashMap<>();

        for (int v = 0; v < points.size(); v++) {
            int weights = 0;
            String path = "";
            weights += points.get(v).getNodeValue();
            path += points.get(v).getNodeName();
            path += "->";
            Stack<BagData> temp = new Stack<>();
            for (BagData w : this.adj(v)) {
                temp.push(w);
            }
            while (!temp.isEmpty()) {
                BagData w = temp.pop();
                weights += w.getNodeValue();
                path += w.getNodeName();
                path += "->";
            }
            path = path.substring(0, path.length() - 2);
            weightPaths.put(weights, path);
        }
        Map.Entry<Integer, String> result = null;
        Optional<Map.Entry<Integer, String>> max = weightPaths.entrySet().stream().max(Comparator.comparing(Map.Entry::getKey));
        if(max.isPresent()){
             result = max.get();
        }
        return "path = " + (result!=null?result.getValue():"null" )+ "  weight = " +(result!=null?result.getKey():"null") ;
    }
}

/**
 * 有向无环图
 */
class DirectedCycleV2 {
    private boolean[] marked;// 以顶点为索引，值代表了该顶点是否标记过（是否可达）
    private Stack<Integer> cycle; // 用来存储有向环顶点。
    // *****重点理解这里start****
    private int[] edgeTo;// edgeTo[0]=1代表顶点1->0, to 0的顶点为1。
    // *****重点理解这里end****
    private boolean[] onStack;// 顶点为索引，值为该顶点是否参与dfs递归，参与为true

    public DirectedCycleV2(Digraph digraph) {
        // 初始化成员变量
        marked = new boolean[digraph.V()];
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        cycle = null;
        // 检查是否有环
        for (int v = 0; v < digraph.V(); v++) {
            dfs(digraph, v);
        }
    }

    public String getWeightPath(Digraph d) {
        String result = "";
        if (this.hasCycle()) {
            System.out.println("输入错误，图是有环路的！");
        } else {
            result = d.getWeightPath();
        }
        return result;
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;// 递归开始，顶点上栈
        marked[v] = true;
        for (BagData w : digraph.adj(v)) {// 遍历一条边，v-> w
            // 终止条件：找到有向环
            if (hasCycle()) return;
            // 使用onStack标志位来记录有效路径上的点，如果w在栈上，说明w在前面当了出发点，
            if (!marked[w.getIndex()]) {
                edgeTo[w.getIndex()] = v;// to w的顶点为v
                dfs(digraph, w.getIndex());
            } else if (onStack[w.getIndex()]) {// 如果指到了已标记的顶点，且该顶点递归栈上。（栈上都是出发点，而找到了已标记的顶点是终点，说明出发点和终点相同了。）
                cycle = new Stack<>();

                for (int x = v; x != w.getIndex(); x = edgeTo[x]) {//起点在第一次循环中已经push了，不要重复
                    cycle.push(x);// 将由v出发，w结束的环上中间的结点遍历push到cycle中。
                }
                cycle.push(w.getIndex());// push终点
            }
        }
        onStack[v] = false;// 当递归开始结算退出时，顶点下栈。
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph d = new Digraph(3);
        BagData A = new BagData(0, "A", 1);
        BagData B = new BagData(1, "B", 2);
        BagData C = new BagData(2, "C", 2);

        d.addEdge(A, B);
        d.addEdge(B, C);
        d.addEdge(A, C);
//        d.addEdge(C, A);
        DirectedCycleV2 directedCycle = new DirectedCycleV2(d);
        String result = directedCycle.getWeightPath(d);
        System.out.println(result);
    }
}
