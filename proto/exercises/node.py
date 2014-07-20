import json
from os.path import abspath, dirname, join as pjoin


class Node(object):
    def __init__(self, value, left, right):
        self.value = value
        self.left = left
        self.right = right

    def __nonzero__(self):
        return self.value is not None

    def __le__(self, other):
        return all((self, other)) and self.value <= other.value

    def __lt__(self, other):
        return all((self, other)) and self.value < other.value

    def __unicode__(self):
        return u'<N {} L: {}, R: {}>'.format(self.value, self.left, self.right)

    __repr__ = __unicode__


def load_fixture(name):
    """Returns decoded raw tree store"""
    here = dirname(abspath(__file__))
    with open(pjoin(here, 'fixtures', name), 'r') as fd:
        return json.load(fd)


def build_tree(raw_tree):
    """Returns Node tree from raw data"""
    if not raw_tree:
        return
    raw_node = raw_tree.get('node')
    if not raw_node:
        return
    return Node(raw_node.get('value'),
                build_tree(raw_node.get('left')),
                build_tree(raw_node.get('right')))


def tree(name):
    return build_tree(load_fixture(name))


def is_bst(node):
    if not node:
        return
    return node.left <= node < node.right


class Subtree(object):

    def findLargestBSTSubtree(self, node):
        self._reset(node)
        self._walk(self.root)
        return self._sub[0]

    def _reset(self, node):
        self.root = node
        self._sub = (None, 0)

    def _hold(self, node, leaves):
        if node is self.root:
            return
        if leaves > self._sub[1]:
            self._sub = (node, leaves)

    def _walk(self, node, leaves=0):
        if not is_bst(node):
            return 1
        lft_count = self._walk(node.left, leaves + 1)
        rgt_count = self._walk(node.right, leaves + 1)
        leaves = lft_count + rgt_count
        self._hold(node, leaves)
        return leaves
