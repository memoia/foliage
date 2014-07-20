package com.memoia.amznex7;

import org.apache.commons.io.input.AutoCloseInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Node implements Comparable<Node> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public int value;
    public Node left;
    public Node right;

    public Node() {
        // init empty object to appease Jackson when loading
        // from a fixture.
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Equality check by node's value.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Node)) {
            return false;
        }
        return (((Node)this).value == ((Node)other).value);
    }

    /**
     * Comparison by node's value.
     */
    @Override
    public int compareTo(Node other) throws NullPointerException, ClassCastException {
        if (other == null) {
            throw new NullPointerException();
        }
        if (this.value < other.value) {
            return -1;
        }
        else if (this.equals(other)) {
            return 0;
        }
        return 1;  // N.B: (this.value > other.value)
    }

    @Override
    public String toString() {
        return String.format("<N %1$s L: %2$s R: %3$s>", value, left, right);
    }

    /**
     * Does this node have children that conform to the definition of BST?
     */
    public boolean hasBst() {
        try {
            // TODO: determine why compareTo is misbehaving.
            return left.value <= value && value < right.value;
        } catch (NullPointerException err) {
            return false;
        }
    }

    /**
     * Generate a node tree from a JSON fixture file.
     */
    public static Node loadFixture(String path) throws NullPointerException, java.io.IOException {
        return mapper.readValue(
            new AutoCloseInputStream(Node.class.getResourceAsStream(path)),
            Node.class);
    }

}
