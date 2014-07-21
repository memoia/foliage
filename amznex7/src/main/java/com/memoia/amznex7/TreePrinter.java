package com.memoia.amznex7;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;


/**
 * Implements Question 2.
 */
public class TreePrinter {

    protected Map<Integer, ArrayList> levels;

    public TreePrinter() {
        this.levels = new LinkedHashMap<Integer, ArrayList>();
    }

    public static void printTree(Node tree) {
        TreePrinter tp = new TreePrinter();
        tp.walk(tree, 0);
        tp.print();
    }

    protected void walk(Node node, Integer height) {
        if (node == null) {
            return;
        }
        if (!levels.containsKey(height)) {
            levels.put(height, new ArrayList<Integer>());
        }
        walk(node.left, height + 1);
        levels.get(height).add(node.value);
        walk(node.right, height + 1);
    }

    protected void print() {
        for (List lvl : levels.values()) {
            System.out.println(StringUtils.join(lvl, ' '));
        }
    }
}
