Amazon Code Exercise Set 7: Binary Search Trees
===============================================

Full description of requirements in exercise-7.pdf.


Get up and running
------------------

This package was developed on OS X, using JDK 7.
It assumes you have GNU Make, curl, etc. The Makefile
has a ``JAVA_HOME`` variable that should "just work"
on OS X with JDK 7, or on RHEL with OpenJDK. Otherwise
please change it to the proper value.

With that, you should just be able to type ``make``
to download all dependencies, run tests, generate a
coverage report, and view output from sample data
for each question in exercise-7.pdf.


Notes
-----

There are no requirements for tree storage. Trees
will be read from JSON structures on disk, for
ease of modification, sampling different scenarios, etc.

Common requirements:

> You may use the JDK or the C++ STL. Your solution
> will be evaluated on correctness, runtime complexity (big-O),
> and adherence to coding best practices.
>
> A complete answer will include the following:
> 1. Document your assumptions
> 2. Explain your approach and how you intend to solve the problem
> 3. Provide code comments where applicable
> 4. Explain the big-O run time complexity of your solution.
>    Justify your answer.


Question 1: Largest Subtree which is a Binary Search Tree (BST)
---------------------------------------------------------------

> Given a binary tree, find the largest subtree which is a Binary Search
> Tree (BST), where largest means subtree with largest number of nodes in
> it. You should return the root of the largest subtree which is a BST, or
> null if there is no BST within the given tree. If two or more subtrees
> are the same size and also the largest BSTs, you may return any of them.

*Introduction to Algorithms, 2nd ed. MIT Press*, defines BST as:

> Let `x` be a node in a BST. If `y` is a node in
> the left subtree of `x`, then the
> value of `y` &lt;= the value of `x`. If `y` is a node
> in the right subtree of `x`, then the value of
> `y` &gt; the value of `x`.

The theorem for in-order walking is proven to take &Theta;(n) time
(pp. 254-255), which would suggest O(n) time, too. I guess this
makes sense since we have to touch each item.

A couple of ideas:

1. Assuming we expect extremely large trees, we might benefit
   from a "meta-BST" that is solely responsible for searching
   for nodes that have subtrees with given heights. Finding
   the maximum-sized subtree would then be a simple maximum
   search, and take O(h) time. Additionally, it might
   be safe to assume that the height of the "meta-tree" would
   be smaller than the height of its associated tree, given
   that a large random tree may have a number of nodes that all
   have subtrees of the same height. It also is more aligned with
   the "spirit" of traditional uses for a BST, as the original
   data structure wouldn't necessarily be queried for its subtree
   heights; we would assume it serves some other purpose.
2. Idea #1 is overkill for this exercise. We might consider
   adding a "level" attribute to each node, assuming that as
   the BST is built, the insertion algorithm uses the parent
   node's level and adds one. Then we'd do (something) with it.
   The problem with this approach is that we might need a
   bi-directional tree to make this usable---added code
   complexity and run time. A variation would be to keep
   track of the height of subtrees within parents. Again,
   a bi-directional tree would be necessary, to traverse
   back up the chain and increment counters on an insertion.
   Searching the BST wouldn't be any faster, as we'd still
   have to walk the entire tree, assuming that the
   "value" for each node isn't the size of its subtree.
3. We modify postorder traversal such that the returned
   value is a tuple of the root node and the total number
   of leaf nodes from its subtrees, updating a reference
   to the "biggest node" as we go. This, I think, is the
   best option for this problem. Run time would be O(n),
   and few changes are required to the traditional textbook
   algorithm.

As noted in the tests associated with this third strategy,
there's a flaw in the design---at least, in the way I
implemented it. If any node lacks both children, the entire
subtree of that node isn't considered, even if the subtree
itself is a BST. Possible strategies for improvement are
noted in the comments.


Question 2: Tree Printing
-------------------------

> You are given a binary tree where each node contains an integer. Print
> the tree out to the console level by level, with each level on its own
> line and each node's value separated by a space (but leaving no
> trailing spaces at the end of a line). You may assume that the tree is
> non-empty, but not necessarily sorted, balanced, or complete.

Some ideas:

1. My initial thought here is to traverse the tree (in any order),
   maintaining a hash with an integer key serving as the "level",
   and the value being a list. Then, as the tree is
   walked, the left and right values could be "merged" by level.
   There's at least one problem with this approach, I think.
   We would be iterating through the data set twice: once to convert
   to a table, and again to print it out. It might not matter
   much, but maybe one day we'll run into a massive tree and reconsider.
2. Maybe there's a way that doesn't require running through the data
   twice. A tree is a kind of graph, and graph searching, as discussed
   in the book (p. 532 onwards) can be accomplished using both
   breadth-first and depth-first techniques. A breadth-first traversal
   strategy might be more appropriate for this problem, since we want
   elements organized by level. We could use a list as a stack or a
   queue to sequentially add each pair of child nodes and then pop
   and print them, but that won't satisfy the formatting requirement.

For now, I'll assume less-than-massive trees. This won't be efficient.
There's the complete tree traversal, and a two-dimensional loop over a
table. The first we already have accepted to take O(n) time. For the
second, the table has `h` rows, with `n` split among them. If we consider
the situation of one node per row, then `h` can be aliased to `n`, for a
grand total of O(n + n^2). That hurts. On a second iteration, we might
consider a strategy known to be more performant.
