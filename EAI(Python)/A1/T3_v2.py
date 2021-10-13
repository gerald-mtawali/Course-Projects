import random

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

def search_tree(search_flag, tree):
    if search_flag == 0:
        return bfs(tree)
    else:
        return dfs(tree)
def start_game():
    print("Please choose a search method \n 0.BFS \n 1.DFS \n: ")
    method = input()
    return method

"""
create tree returns a tree of a certain depth 
lvl indicates the current level that we are adding to the tree 
"""
def create_tree(tree,lvl):
    tree.append([])                         # add the new level
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

def play_move(prev_move=None,opp_move=None, rand=True):
    move = ''
    # rand is false only if the break sequence was found
    if not rand:
        # Paper beats Rock
        if opp_move == 'R':
            move = 'P'
        # Scissors beats Paper
        elif opp_move == 'P':
            move = 'S'
        # Rock beats Scissors
        elif opp_move == 'S':
            move = 'R'
    elif rand:
        if prev_move == 'R':
            move = random.choice(['P', 'S'])
        elif prev_move == 'S':
            move = random.choice(['R', 'P'])
        elif prev_move == 'P':
            move = random.choice(['R', 'S'])
        else:
            move = random.choice(['R', 'P', 'S'])
    return move

search_flag = start_game()  # chooses a dfs or bfs search
opp_history = []            # list of all of the opponents moves
break_flag = False          # False if break sequence hasn't been found, true otherwise
tree = []
agent_history = []          # stores the moves made by the agent
curr_seq = ""
break_seq = ""              # the guestimated break sequence
repeat_flag = False         # will notify when a repeat occurs
move_count = 0
breaks =[]                  # list that stores any of the possible break sequences
root = Node(None, 'root')
lvl_one = [root]
tree.append(lvl_one)


def bot_play():
    global opp_history
    global search_flag
    global curr_seq
    global move_count
    global root
    global breaks
    global repeat_flag
    global break_flag
    global tree
    opp_move = raw_input()
    opp_history.append(opp_move)
    move_count += 1
    curr_seq += opp_move
    #print("The current sequence of the bot is ", curr_seq)
    #print("The previous moves of the bot are ", opp_history)
    #print("The current move count is ", move_count)

    for i in range(len(opp_history)-1):
        if(len(opp_history) == 1):
            continue
        if opp_history[i] == opp_history[i+1]:
            #print("A repeat has occurred")
            repeat_flag = True

    if move_count >= 2:
        """ 
        Once a move has been played we need to check to create a tree and perform a search n that tree 
        """
        for i in range(1,move_count+1):
            tree = create_tree(tree, i)
        """
        for lvl in tree:
            print("\n")
            print("["),
            for node in lvl:
                print(node.get_val()),
            print("]")
        """
        search_sequence = search_tree(search_flag, root)
        search_sequence = print_sequence(search_sequence)

        print "The search sequence of the current tree is \n", search_sequence
        # if any of the sequences match the current sequence then we add it to the possible break sequences
        for seq in search_sequence:
            if seq == curr_seq:
                breaks.append(seq)
        #print("The current breaks array is ", breaks)
def agent_plays():
    """
    If the break sequence hasn't been found then we play a random move
    """
    global breaks
    global break_seq
    global move_count
    global repeat_flag
    global curr_seq
    global break_flag
    output = ''
    # first round
    """ there are no break sequences as of yet """
    if len(breaks)== 0:
        if len(agent_history) == 0:
            output = play_move(None, None, True)
            agent_history.append(output)
            #print("The agent plays",output)
        else:
            prev = agent_history[len(agent_history)-1]
            output = play_move(prev, None, True)
            agent_history.append(output)
            #print("The agent plays", output)
    else:
        """ 
        check if the repeat flag has been raised
        take the last known break sequence and set as the break sequence , and play according to the last move in that sequence
        """
        if repeat_flag and not break_flag :
            dummy_seq = breaks.pop()                    # take the last known break sequence
            dummy_seq = dummy_seq[-5:]                  # make it a value of 5 or less
            break_seq = dummy_seq[:len(dummy_seq)-1]
            #break_seq = break_seq[::-1]             # reverse the sequence
            if len(break_seq) != 0:
                break_flag = True
            #print("The break sequence was found to be ", break_seq)
            output = play_move(opp_move=break_seq[len(break_seq)-1], rand=False)
            agent_history.append(output)
            #print("The agent plays", output)
        if break_flag:
            #print("The break sequence was found to be ", break_seq)
            output = play_move(opp_move=break_seq[len(break_seq) - 1], rand=False)
            agent_history.append(output)
            #print("The agent plays", output)
        else:
            """
            play a move that wasn't the bots last move
            """
            # take the last move made by the bot and beat it
            last_move = opp_history[len(opp_history) - 1]
            output = play_move(opp_move=last_move, rand=False)
            agent_history.append(output)
            #print("The agent plays", output)
    return output

if not input:
    output = agent_plays()
else:
    bot_play()

