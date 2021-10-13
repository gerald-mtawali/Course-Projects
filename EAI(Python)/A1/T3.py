import T1, T2
import random

def start_game():
    print("Please choose a search method \n 0.BFS \n 1.DFS \n: ")
    method = input()
    return method

search_flag = start_game()  # chooses a dfs or bfs search
break_found = False         # flag to see if the break sequence has been found
match = False
break_sequence = ""         # the break sequence
opp_history = []            # a list of all previous moves made by the agent
agent_history = []
max_tree_size = 5
curr_seq = ""
move_count = 0              # number of moves since the break sequence was found
tree = [[],[],[],[],[],[]]
root = T1.Node(None, 'root')
lvl_one = [root]
tree[0] = lvl_one

def search_tree(search_flag, tree):
    if search_flag == 0:
        return T2.bfs(tree)
    else:
        return T2.dfs(tree)

"""
create tree returns a tree of a certain depth 
lvl indicates the current level that we are adding to the tree 
"""
def create_tree(tree,lvl):
    print("entered the create tree method ")
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
"""
tests the sequence found by DFS/BFS with the current break sequence iterated
if the sequences match then a break sequence has been found 
"""
def test_seq(curr_seq, search_seq):
    status = False
    for seq in search_seq:
        if seq == curr_seq:
            status = True
    return status


def agent_plays():
    global curr_seq
    global break_sequence
    global break_found
    global agent_history
    global opp_history
    global opp_move
    global tree
    global search_flag
    global match
    global move_count
    global root

    """ this is when the agent plays a move """
    if break_found:
        """ 
        once the break sequence has been found, perform a search based on the number of moves made by the bot 
        does the current sequence match the break sequence: if yes then play the next value of the break sequence 
        """
        print('the current number of moves is ', move_count)
        # add levels to the tree based on the number of moves
        for i in range(1, move_count):
            tree = create_tree(tree, i)
        for lvl in tree:
            print('\n')
            print('[')
            for node in lvl:
                print node.get_val()
            print(']')
        # generate a search sequence
        search_sequence = search_tree(search_flag, root)
        search_sequence = print_sequence(search_sequence)
        idx = 0
        print("The search sequence of the current tree is ", search_sequence)
        # compare all the sequences found in the search
        for seq in search_sequence:
            for i in range(len(seq)):
                if seq[i] == break_sequence[i]:
                    match = True  # the search sequence matches the current sequence of moves that have been played
                    idx = i
                elif seq[i] != break_sequence[i]:  # the moment a sequence doesn't match the break sequence go to the next sequence
                    match = False
                    continue
        if match:  # the search sequence matches the break sequence
            print('a match has been found')
            if idx != len(break_sequence)-1:        # if we have found a match prior to finding the full element
                opp_move = break_sequence[idx+1]
            else:
                opp_move = break_sequence[idx]
            output = play_move(opp_move, rand=False)
            agent_history.append(output)
            print(output)
        else:
            # take the last move made by the bot and beat it
            last_move = opp_history[len(opp_history) - 1]
            output = play_move(opp_move=last_move, rand=False)
            agent_history.append(output)
            print(output)
    else:
        # play a random move that isn't the last move made by the agent
        if len(agent_history) == 0:
            output = play_move()
            agent_history.append(output)
            print(output)
        else:
            prev = agent_history.pop()
            output = play_move(prev_move=prev)
            agent_history.append(output)
            print(output)

def bot_plays():
    """
    the opponent gets the chance to play a move
    add that move to the current sequence
    test that sequence against sequences generated from BFS/DFS
    """
    global curr_seq
    global break_sequence
    global break_found
    global opp_history
    global move_count
    opp_move = raw_input()
    opp_history.append(opp_move)

    curr_seq = curr_seq+opp_move  # add to the current sequence
    print("The current sequence of the bot is ", curr_seq)
    move_count += 1
    print("The moves that the bot has made is ",opp_history)
    # Once the move count exceeds the max tree size
    if move_count > max_tree_size and break_found:
        curr_seq = ""
        move_count = 0
        # reset the tree
        for i in range(len(tree)):
            if i != 0:
                tree[i] = []  # set the elements in the tree equal to naught

    if not break_found:
        """ 
        if the sequence has not yet been found we need to check if the bot has repeated itself
        then we know that the break sequence has been achieved 
        the idea is that this will only ever be done once in the beginning
        """
        for i in range(len(opp_history)-1):
            if(len(opp_history) == 1):
                continue
            # ensure that the move has not yet been repeated
            if opp_history[i] != opp_history[i+1]:
                break_sequence = curr_seq[:len(curr_seq)-1]
            # once the move has been repeated then we know we have achieved the break sequence
            elif opp_history[i] == opp_history[i+1]:
                print('\n break sequence found it is ', break_sequence)
                break_found = True


if __name__ == '__main__':
    game_count = 0
    while game_count < 10:
        agent_plays()
        bot_plays()
        game_count += 1