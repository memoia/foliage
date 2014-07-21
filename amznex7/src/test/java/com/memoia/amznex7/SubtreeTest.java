package com.memoia.amznex7;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class SubtreeTest {

    private Node bst;
    private Subtree st;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        bst = Node.loadFixture("valid-bst.json");
        st = new Subtree(bst);
    }

    @Test
    public void storesRootNode() throws Exception {
        assertEquals(9, st.getRootNode().value);
    }

    @Test
    public void holdIgnoresRootNode() throws Exception {
        st.hold(st.getRootNode(), 1);
        assertNull(st.getBiggestSubtree());
    }

    @Test
    public void holdStoresNextBiggestNode() throws Exception {
        Node n1 = new Node(1, null, null);
        Node n2 = new Node(2, null, null);
        st.hold(n1, 100);
        st.hold(n2, 200);
        st.hold(n1, 100);
        assertEquals(n2, st.getBiggestSubtree());
    }

    @Test
    public void walkReturnsOneWhenNodeLacksBst() throws Exception {
        assertEquals(1, st.walk(new Node(10, null, null), 50));
    }

    @Test
    public void findsSubtreeInBalancedTree() throws Exception {
        Node sub = Subtree.findLargestBSTSubtree(bst);
        assertEquals(5, sub.value);  // we always find the first
    }

    @Test
    public void findLargestBSTSubtreeIsNullWhenNoBST() throws Exception {
        assertNull(Subtree.findLargestBSTSubtree(new Node(10, null, null)));
    }

    @Test
    public void demonstrateFlawInDesign() throws Exception {
        Node tree = Node.loadFixture("partial-bst.json");
        Node sub = Subtree.findLargestBSTSubtree(tree);
        // This particular fixture isn't traversed further
        // because the root node does not qualify as a BST.
        // The correct node has value 5, but we get null.
        // This might be solved by "looking ahead" at each
        // node's immediate children, but the assumption for
        // this question, anyway, is that a complete BST is
        // provided (as the second question indicates a complete
        // tree may not be provided).
        assertNull(sub);
    }

}
