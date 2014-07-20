#!../env/bin/python -i

import node
from collections import defaultdict

tree = node.tree('ex2.json')

levels = defaultdict(list)

def walk(node, levels, height=0):
    if not node:
        return
    walk(node.left, levels, height + 1)
    levels[height].append(node.value)
    walk(node.right, levels, height + 1)

walk(tree, levels)

for (level, values) in sorted(levels.items()):
    print ' '.join(map(str, values))
