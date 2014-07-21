package com.memoia.amznex7;


/**
 * Implements Question 1.
 */
public class Subtree {

    private Node rootNode;
    private Node biggestSubtree;
    private int biggestSubtreeLeafCount;

    public Subtree(Node rootNode) {
        this.rootNode = rootNode;
        biggestSubtree = null;
        biggestSubtreeLeafCount = 0;
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public Node getBiggestSubtree() {
        return this.biggestSubtree;
    }

    public int getBiggestSubtreeLeafCount() {
        return biggestSubtreeLeafCount;
    }

    public static Node findLargestBSTSubtree(Node tree) {
        Subtree st = new Subtree(tree);
        st.walk(tree, 0);
        if (st.getBiggestSubtreeLeafCount() == 1) {
            return null;
        }
        return st.getBiggestSubtree();
    }

    protected void hold(Node node, Integer leafCount) {
        if (node == rootNode) {
            return;
        }
        if (leafCount > biggestSubtreeLeafCount) {
            biggestSubtree = node;
            biggestSubtreeLeafCount = leafCount;
        }
    }

    protected int walk(Node node, int leaves) {
        if (!node.hasBst()) {
            return 1;
        }
        leaves = (walk(node.left, leaves + 1) +
                  walk(node.right, leaves + 1));
        hold(node, leaves);
        return leaves;
    }

}
