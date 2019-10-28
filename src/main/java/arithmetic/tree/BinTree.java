package arithmetic.tree;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/9/19 22:07
 */
public class BinTree {

    private static List<Node> nodeList = null;

    /**
     * 内部类：节点
     *
     * @author ocaicai@yeah.net @date: 2011-5-17
     */
    private static class Node {
        Node leftChild;
        Node rightChild;
        int data;

        Node(int newData) {
            leftChild = null;
            rightChild = null;
            data = newData;
        }
    }

    public void createBinTree(int[] array) {
        nodeList = new LinkedList<>();
        // 将一个数组的值依次转换为Node节点
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new Node(array[i]));
        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            // 左孩子
            nodeList.get(parentIndex).leftChild = nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).rightChild = nodeList
                    .get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).leftChild = nodeList
                .get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);
        }
    }

    /**
     * 先序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void preOrderTraverse(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /**
     * 中序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.leftChild);
        System.out.print(node.data + " ");
        inOrderTraverse(node.rightChild);
    }

    /**
     * 后序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void postOrderTraverse(Node node) {
        if (node == null)
            return;
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data + " ");
    }


    /**
     * 获取树的最大深度
     *
     * @param root
     * @return
     */
    public static int getMaxDepth(Node root) {
        if (root == null)
            return 0;

        int left = getMaxDepth(root.leftChild);
        int right = getMaxDepth(root.rightChild);
        return 1 + Math.max(left, right);

    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinTree binTree = new BinTree();
        binTree.createBinTree(array);
        // nodeList中第0个索引处的值即为根节点
        Node root = nodeList.get(0);

        System.out.println("先序遍历：");
        preOrderTraverse(root);
        System.out.println();

        System.out.println("中序遍历：");
        inOrderTraverse(root);
        System.out.println();

        System.out.println("后序遍历：");
        postOrderTraverse(root);
    }

    // 非递归前序遍历
    public List<Integer> preorderTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            res.add(current.data);

            if (current.rightChild != null) {
                stack.push(current.rightChild);
            }

            if (current.leftChild != null) {
                stack.push(current.leftChild);
            }
        }

        return res;
    }

    // 非递归中序遍历
    public List<Integer> inorderTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Node> stack = new Stack<>();

        Node p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.leftChild;
            } else {
                p = stack.pop();
                res.add(p.data);
                p = p.rightChild;
            }
        }

        return res;
    }

    //    // 非递归后序遍历
//    public List<Integer> postorderTraversal(Node root) {
//        List<Integer> res = new ArrayList<>();
//        if(root == null) {
//            return res;
//        }
//
//        Stack<Node> stack = new Stack<>();
//
//        Node p = root;
//
//        // 标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
//        Node pre = p;
//
//        while(p != null || !stack.isEmpty()) {
//            if(p != null) {
//
//                stack.push(p);
//                p = p.leftChild;
//
//            } else {
//                p = stack.pop();
//
//                if(p.rightChild == null || p.rightChild == pre) {
//                    res.add(p.data);
//                    pre = cur;
//                    p = null;
//                } else {
//                    stack.push(p);
//                    p = p.right;
//                    stack.push(p);
//                    p = p.left;
//                }
//            }
//        }
//
//        return res;
//    }
//
//    // 非递归层次遍历 (广度优先遍历)
    public List<Integer> levelTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            // current node
            Node current = queue.poll();
            res.add(current.data);

            if (current.leftChild != null) {
                queue.add(current.leftChild);
            }

            if (current.rightChild != null) {
                queue.add(current.rightChild);
            }
        }

        return res;
    }

}
