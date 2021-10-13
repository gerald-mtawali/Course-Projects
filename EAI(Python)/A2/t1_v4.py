#!/usr/bin/env python

import pickle
import math

record = open('games.txt', 'a+')
#record.write("A game of RPS\n")


class SearchAgent(object):
    def __init__(self, sequence):
        self.seq = sequence
        self.move_count = 0
        self.move_history = []                      # history of all moves played by the agent
        self.belief_states = []                     # keeps track of all the belief states
        self.found_idx = 0
        self.found_seq = False

    def init_state(self, opp_move):
        """
        generates the initial belief state based on the opponent's first move of the match
        """
        idx = []
        for i in xrange(len(self.seq)):
            if self.seq[i] == opp_move:
                idx.append(i)
        self.belief_states.append(idx)

    def init_move(self):
        """
        plays the first move of the game based on the most frequent element in the list
        :return:
        """
        beat_move = {'R': 'P', 'P':'S', 'S':'R'}
        seq_list = []
        for i in self.seq:
            seq_list.append(i)
        first_move = max(set(seq_list), key=seq_list.count)
        self.move_count += 1
        self.move_history.append(beat_move[first_move])
        return beat_move[first_move]

    def predict_next_state(self):
        """
        predict the next states based on the last belief state
        :return:
        """
        move_set = ["R", "P", "S"]
        next_states = []                # holds the next states for
        for move in move_set:
            play =[]
            state = self.belief_states[len(self.belief_states)-1]               #take the last belief state
            for b in state:
                if (b+self.move_count) < len(self.seq)-1 and self.seq[b+self.move_count] == move:
                    play.append({'current belief idx': b, 'bot could play': move, 'nxt move idx': b+self.move_count})
            next_states.append(play)
        return next_states

    def play_move(self, next_states=[]):
        """
        plays a move based on the cheapest path found related to the next possible belief states that can be chosen
        """
        beat_move = {"R":"P", "P":"S", "S":"R"}
        cheap_move = ""
        if self.found_seq == True:
            if (self.found_idx+self.move_count)-1 < len(self.seq):
		print "The sequence has been found "
		print "THe length of the sequence is ", len(self.seq), "The index value is ", self.found_idx+self.move_count
                #print "The bot will play ", self.seq[self.found_idx+self.move_count]," therefore I will play", beat_move[self.seq[self.found_idx+self.move_count]], " next"
                self.move_history.append(beat_move[self.seq[(self.found_idx+(self.move_count))-1]])
		self.move_count += 1
                return beat_move[self.seq[(self.found_idx+(self.move_count))-1]]
            #else:
                #print "We have reached the max number of elements in the list "
        else:
            for state in next_states:
                """ find the cheapest path; the farther away from the middle it is the less likely it will be chosen """
                for i in state:
                    i['path cost'] = math.fabs(i['current belief idx'] - math.ceil(len(self.seq)/2))/len(self.seq)
            mins = []
            for state in next_states:
                m = 1
                i_MIN ={}
                for i in state:
                    if i['path cost'] < m:
                        m = i['path cost']
                        i_MIN = i
                mins.append(i_MIN)
            min_val = 4
            #print mins
            for m in mins:
                if len(m) != 0 and m['path cost'] < min_val:
                    min_val = m['path cost']
                    cheap_move = m['bot could play']
            self.move_history.append(beat_move[cheap_move])
            self.move_count += 1

            return beat_move[cheap_move]

    def goal_test(self, opp_move):
        """
        performs a goal test to see whether we have reached our goal
        the goal is to only have one index in the belief states, the index from which the opponent started playing
        if the agents prev_move beat the opponent move:
            we only proceed with the belief states that include the prev move
        else if the prev move didn't beat opponent:
            we proveed with belief states that do not include the previous move
        :param opp_move:
        :return:
        """
        if self.found_seq == False:
            # if there are no belief states, then obviously it is only after the first move
            if len(self.belief_states) == 0:
                self.init_state(opp_move)
            else:
                beat_move = {"R":"P", "P":"S", "S":"R"}
                #print self.move_history[len(self.move_history)-1]
                if beat_move[opp_move] == self.move_history[len(self.move_history)-1]:
                    next_state = []
                    # iterate through the prev belief state, if the next index in the sequence returns the last move played then we good
                    for b in self.belief_states[len(self.belief_states)-1]:
                        if (b+self.move_count) < len(self.seq)-1 and self.move_history[len(self.move_history)-1] == beat_move[self.seq[b+self.move_count-1]]:
                            next_state.append(b)
                    self.belief_states.append(next_state)
                    if len(next_state) == 1:
                        #print "The solution has been found at move", self.move_count
                        self.found_idx = next_state[0]
                        self.found_seq = True
			record.write("Found at Move number "+str(my_agent.move_count)+" length of sequence is "+str(len(sequence))+" the starting index"+str(my_agent.found_idx)+"\n")
			record.close()
                # else the prev move didn't beat the opponent
                else:
                    next_state = []
                    for b in self.belief_states[len(self.belief_states)-1]:
                        if (b+self.move_count) < len(self.seq)-1 and self.move_history[len(self.move_history)-1] != beat_move[self.seq[b+self.move_count-1]]:
                            next_state.append(b)
                    self.belief_states.append(next_state)
                    if len(next_state) == 1:
                        #print "The solution has been found at move", self.move_count, "of sequence length ", len(self.seq)
                        self.found_idx = next_state[0]
                        self.found_seq = True
			record.write("Found at Move number "+str(my_agent.move_count)+" length of sequence is "+str(len(sequence))+" the starting index"+str(my_agent.found_idx)+"\n")
			record.close()
                        #return True
        #else:   # the solution has been found
            #self.play_move()

 
def agent_play(my_agent,opp_move):
    output = ""
    if my_agent.found_seq == False:
	print my_agent.move_count
        my_agent.goal_test(opp_move)
        output = my_agent.play_move(my_agent.predict_next_state())
        print output 
    else: # the goal has been found
	#print my_agent.move_count
        output = my_agent.play_move()
	print output 
    return output

if input == "":
	sequence = pickle.load(open('sequence.pkl'))
	print sequence
	my_agent = SearchAgent(sequence)
    # move count = 0
    output = my_agent.init_move()
else:
    output = agent_play(my_agent,input)


