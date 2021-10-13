# -*- coding: utf-8 -*-

# BFS and DFS implementations of practical One
# Iterates through a tree structure and gives the output

#from T1 import Node
import T1
import timeit
"""
create tree returns a tree of a certain depth 
lvl indicates the current level that we are adding to the tree 
"""
def create_tree(tree,lvl):
    print("entered the create tree method ")
    tree.append([])
    for node in tree[lvl-1]:
        #print("currently at node,", node.get_val())
        rock = T1.Node(node, 'R')
        paper = T1.Node(node, 'P')
        scissors = T1.Node(node, 'S')

        node.add_child(rock)
        node.add_child(paper)
        node.add_child(scissors)

        tree[lvl].append(rock)
        tree[lvl].append(paper)
        tree[lvl].append(scissors)
    return tree

""" converts the bfs sequence to the one that will be compared with breakable's moves"""
def print_sequence(bfs):
    sequence = []
    for i in range(len(bfs)):
        """
        iterate through the bfs list 
        for every node check to see whether it has a parent
        """
        seq_str = bfs[i].get_val()
        # if this isn't the first level
        if bfs[i].parent == None:
            continue
        if bfs[i].parent.get_val() != 'root':
            parent = bfs[i].parent
            while(parent != None and parent.get_val() != 'root'):
                'parent val + current sequence string '
                seq_str = parent.get_val() + seq_str
                parent = parent.parent
            sequence.append(seq_str)
        else:
            sequence.append(seq_str)
    return sequence

def bfs(root):
    frontier = [root]  # the frontier acts as our Queue
    traversed = []
    while frontier:
        node = frontier.pop(0)
        # check if the node has already been traversed
        if node not in traversed:
            traversed.append(node)
            # add the children of the node to the frontier queue
            for child in node.children:
                if child != None:
                    frontier.append(child)
    return traversed


def dfs(root):
    stack = [root]
    traversed =[]
    while(stack):
        #LIFO
        node = stack.pop(0)
        if node not in traversed:
            traversed.append(node)
            #node.children.reverse()
            for child in node.children:
                if child != None:
                    stack.insert(0,child)
    traversed.reverse()
    return traversed


def bfs_test():
    root = T1.Node(None, 'root')
    tree = []
    lvl_one = [root]
    tree.append(lvl_one)
    for i in range(1, 10):
        tree = create_tree(tree,i)
        """
    for lvl in tree:
        print "\n"
        for node in lvl:
            print node.get_val()
    """
    my_bfs = bfs(root)

    search_sequence = print_sequence(my_bfs)
    print(search_sequence)

    print('\n\n')

def dfs_test():
    root = T1.Node(None, 'root')
    tree = []
    lvl_one = [root]
    tree.append(lvl_one)
    for i in range(1, 10):
        tree = create_tree(tree,i)
        """
    for lvl in tree:
        print "\n"
        for node in lvl:
            print node.get_val()
    """
    my_dfs = dfs(root)
    """
    for b in my_bfs:
        print(b.get_val())
    """
    print('\n\n')




if __name__ == '__main__':
    start = timeit.default_timer()
    bfs_test()
    stop = timeit.default_timer()
    print("The total runtime of the BFS algorithm is ", stop-start, " seconds")

    """
    start2 = timeit.default_timer()
    dfs_test()
    stop2 = timeit.default_timer()
    print("The total runtime of the DFS algorithm is ", stop2-start2, " seconds")
    """







