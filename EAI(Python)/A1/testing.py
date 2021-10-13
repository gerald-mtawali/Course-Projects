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



def create_tree(tree,lvl):
    print("entered the create tree method ")
    tree.append([])
    for node in tree[lvl-1]:
        #print("currently at node,", node.get_val())
        rock = Node(node, 'R')
        paper = Node(node, 'P')
        scissors = Node(node, 'S')

        node.add_child(rock)
        node.add_child(paper)
        node.add_child(scissors)

        tree[lvl].append(rock)
        tree[lvl].append(paper)
        tree[lvl].append(scissors)
    return tree

root = Node(None, 'root')
tree = []
lvl_one = [root]
lvl = 0
tree.append(lvl_one)
lvl += 1
tree = create_tree(tree, lvl)
lvl +=1
tree = create_tree(tree, lvl)

for lvl in tree:
    print('\n')
    print("["),
    for node in lvl:
        print(node.get_val()),
    print("]")


word = "helooworld"
print word[-5:]


for i in range(len(word)-1, len(word[-5:]), -1):
    print word[i]

print(word[::-1])