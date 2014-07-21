package com.memoia.amznex7;

import java.util.Arrays;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class TreePrinterTest {

    private TreePrinter tp;
    private Node bst;
    private Node pbst;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        tp = new TreePrinter();
        bst = Node.loadFixture("valid-bst.json");
    }

    @Test
    public void walkHeight() throws Exception {
        tp.walk(bst, 0);
        assertEquals(3, tp.levels.size());
    }

    @Test
    public void walkGetsAtLeastOneLevelRight() throws Exception {
        tp.walk(bst, 0);
        assertEquals(Arrays.asList(3, 6, 10, 30), tp.levels.get(2));
    }

    @Test
    public void walkPartialBst() throws Exception {
        pbst = Node.loadFixture("partial-bst.json");
        tp.walk(pbst, 0);
        assertEquals(Arrays.asList(3, 6), tp.levels.get(2));
    }

    @Test
    public void printTreeCanExecuteAKnownCase() throws Exception {
        TreePrinter.printTree(Node.loadFixture("simple.json"));
    }
}
