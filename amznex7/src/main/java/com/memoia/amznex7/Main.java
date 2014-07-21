package com.memoia.amznex7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void printTree(String fixture) {
        Node tree = Node.loadFixture(fixture);
        TreePrinter.printTree(tree);
    }

    public static Node findLargestSubtree(String fixture) {
        Node tree = Node.loadFixture(fixture);
        return Subtree.findLargestBSTSubtree(tree);
    }

    public static void main(String[] args) {
        Node ex1 = findLargestSubtree("ex1.json");
        LOG.info(String.format(
            "Question 1 - largest subtree from ex1.json has node value %1$s (struct: %2$s)",
            ex1.value, ex1));

        LOG.info("Question 2 - tree printed by level:");
        printTree("ex2.json");
    }

}
