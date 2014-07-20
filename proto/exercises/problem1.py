#!../env/bin/python -i

import node

tree = node.tree('ex1.json')

subtree = node.Subtree().findLargestBSTSubtree(tree)

print subtree
