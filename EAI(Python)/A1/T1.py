import T2

class Node(object):
    def __init__(self,  parent = None,elem='', children =[]):
        self.val = elem
        self.parent = parent
        self.rock = self.paper=self.scissors = None
        self.children = [self.rock, self.paper, self.scissors]
    def get_val(self):
        return self.val
    def full_house(self):
        return self.rock !=None and self.paper != None and self.scissors != None
    def is_leaf(self):
        return self.rock == None and self.paper == None and self.scissors == None
    def add_child(self, c_node):
        if c_node.get_val() == 'R':
            self.children[0] = self.rock = c_node
        elif c_node.get_val() == 'P':
            self.children[1] = self.paper = c_node
        elif c_node.get_val() == 'S':
            self.children[2] = self.scissors = c_node

"""
# the search tree class that creates our tree
class SearchTree(object):
    # can choose how deep the tree goes
    def __init__(self, depth):
        self.root = Node('root')                    # root has no parent
        self.max_depth = depth
        self.num_elems = 1

    def size(self):
        return self.num_elems

    def add(self, elem):
        if self.root.full_house():
            self.insert(self.root, elem)
        else:
            child = Node(elem, self.root)
            self.root.add_child(child)
            print(" adding to root ")
        self.num_elems += 1

    def insert(self, curr_node, elem):
        # node has a full house
        if curr_node.full_house() == True:
            # insert into whichever child does not have a full house
            for child in curr_node.children:
                if child != None:
                    if child.full_house() == False:
                        self.insert(child, elem)
        # the node does not have a full list of children
        else:
            if elem == 'R' and curr_node.rock == None:
                child = Node(elem, curr_node)
                curr_node.add_child(child)
            elif elem == 'P' and curr_node.paper == None:
                child = Node(elem, curr_node)
                curr_node.add_child(child)
            elif elem == 'S' and curr_node.scissors == None:
                child = Node(elem, curr_node)
                curr_node.add_child(child)
            else:
                for child in curr_node.children:
                    self.insert(child, elem)
"""
if __name__ == '__main__':
    root = Node(None, 'root')

