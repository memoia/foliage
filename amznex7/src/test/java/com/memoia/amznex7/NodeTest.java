package com.memoia.amznex7;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class NodeTest {

    private Node n1;
    private Node n2;
    private Node n3;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        n1 = new Node(1, new Node(-5, null, null), null);
        n2 = new Node(2, null, null);
    }

    @Test
    public void equalityWithValidNodes() throws Exception {
        n2.value = 1;
        assertTrue(n1.equals(n2));
    }

    @Test
    public void inequalityWithValidNodes() throws Exception {
        assertFalse(n1.equals(n2));
    }

    @Test
    public void inequalityWithArbitraryObject() throws Exception {
        assertFalse(n1.equals(new Object()));
    }

    @Test
    public void compareToThrowsNullForNullNode() throws Exception {
        exception.expect(NullPointerException.class);
        n1.compareTo(n3);
    }

    @Test
    public void compareToWhenLessThan() throws Exception {
        assertTrue(n1.compareTo(n2) < 0);
    }

    @Test
    public void compareToWhenGreaterThan() throws Exception {
        assertTrue(n2.compareTo(n1) > 0);
    }

    @Test
    public void compareToWhenEqual() throws Exception {
        n2.value = 1;
        assertTrue(n2.compareTo(n1) == 0);
    }

    @Test
    public void toStringProducesExpectedFormat() throws Exception {
        assertEquals("<N 1 L: <N -5 L: null R: null> R: null>", n1.toString());
    }

    @Test
    public void hasBstIsTrue() throws Exception {
        n3 = new Node(2, new Node(1, null, null), new Node(3, null, null));
        assertTrue(n3.hasBst());
    }

    @Test
    public void hasBstIsTrueWhenLeftIsEqualToRoot() throws Exception {
        n3 = new Node(2, new Node(2, null, null), new Node(3, null, null));
        assertTrue(n3.hasBst());
    }

    @Test
    public void hasBstIsFalseWhenValuesNotOrdered() throws Exception {
        n3 = new Node(2, new Node(3, null, null), new Node(1, null, null));
        assertFalse(n3.hasBst());
    }

    @Test
    public void hasBstIsFalseWhenMissingLeaf() throws Exception {
        n3 = new Node(2, null, new Node(3, null, null));
        assertFalse(n3.hasBst());
    }

    @Test
    public void loadFixture() throws Exception {
        n3 = Node.loadFixture("/simple.json");
        assertEquals(new Node(9, null, null), n3);
    }

    @Test
    public void loadFixtureWithoutLeadingSlash() throws Exception {
        n3 = Node.loadFixture("simple.json");
        assertEquals(new Node(9, null, null), n3);
    }

    @Test
    public void loadFixtureMissingFile() throws Exception {
        n3 = Node.loadFixture("/does.not.exist.json");
        assertNull(n3);
    }

    @Test
    public void loadFixtureBadFormat() throws Exception {
        n3 = Node.loadFixture("/bad.format.json");
        assertNull(n3);
    }
}
