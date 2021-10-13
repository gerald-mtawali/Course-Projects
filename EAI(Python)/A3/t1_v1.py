#'P', 'S', 'P', 'S', 'P', 'R', 'P', 'P', 'R', 'R', 'S', 'R', 'P', 'P', 'R', 'P', 'S', 'P', 'S', 'P', 'R', 'R', 'S', 'R', 'S', 'P', 'P', 'P', 'S', 'S', 'R', 'P', 'P', 'R', 'S', 'S', 'R', 'S', 'P', 'R', 'R', 'S', 'P', 'P', 'S', 'S', 'R', 'S', 'R', 'R', 'S', 'P', 'R', 'R', 'S', 'P', 'S', 'R', 'P', 'R', 'P', 'R', 'P', 'S', 'S', 'R', 'S', 'R', 'P', 'P', 'S', 'S', 'R', 'S', 'R', 'R', 'R', 'S', 'S', 'S', 'S']

"""
EAI Practical 3: Genetic Algorithm

GA run through:
    - Initialize the population
    - Perform an evaluation of each individual
    - Parent Selection, take the best individuals(and some bad ones)
    - Crossover
    - Mutation
    - Survivor Selection

def gen_population(N):
    - an individual is a dict element
    - a population is a list of N individuals
    - return the population
def create_individual(empty dict):
    - initi K,V pairs in the dict
    - K = BFS iteration
    - V = randomly generated output
    - add every pair to the dict that was passed
    - perform for 81 pairs
    - return dict

def fitness_function(individual):
    - iterate through the csv file per row, collect the element on that row
    - iterate through the individual elements, check to see whether a gene in an individual matches
            the output value of the specific historical move
    - calculate the fitness score of the individual based on the weights
    - the individual will be updated with an extra element in the dict called 'score'

def parent_selection(population):
    - choose the parents based on the fitness functions of the individuals
    - for the sake of the pracitical, 4n individuals will be used, so need to take
        75% of the entire population, with 2/3 of the individuals being an "optimal" parent and the
        other 1/3 is a "worse" parent
    - Basically TOURNAMENT SELECTION

def gen_children(parents = []):
    - generate offspring from the best selected parents
    - N parents are to create N children
    - Create the children by creating a crossover point
    - the best parents should be with the worst parents, similar to playoff style selection
    - perform crossover N times

def crossover(parentOne, parentTwo):
    - take a certain index of parentOne and parentTwo
    - crossover the arrays to create two new offspring

def mutate(offspring):
    - take the string that is represented by the offspring, then flip
    -

def perform_ga():


"""
import csv
import random
import math
import time

BFS_SEQ = ['RRRR', 'RRRP', 'RRRS', 'RRPR', 'RRPP', 'RRPS', 'RRSR', 'RRSP', 'RRSS', 'RPRR',
           'RPRP', 'RPRS', 'RPPR', 'RPPP', 'RPPS', 'RPSR', 'RPSP', 'RPSS', 'RSRR', 'RSRP',
           'RSRS', 'RSPR', 'RSPP', 'RSPS', 'RSSR', 'RSSP', 'RSSS', 'PRRR', 'PRRP', 'PRRS',
           'PRPR', 'PRPP', 'PRPS', 'PRSR', 'PRSP', 'PRSS', 'PPRR', 'PPRP', 'PPRS', 'PPPR',
           'PPPP', 'PPPS', 'PPSR', 'PPSP', 'PPSS', 'PSRR', 'PSRP', 'PSRS', 'PSPR', 'PSPP',
           'PSPS', 'PSSR', 'PSSP', 'PSSS', 'SRRR', 'SRRP', 'SRRS', 'SRPR', 'SRPP', 'SRPS',
           'SRSR', 'SRSP', 'SRSS', 'SPRR', 'SPRP', 'SPRS', 'SPPR', 'SPPP', 'SPPS', 'SPSR',
           'SPSP', 'SPSS', 'SSRR', 'SSRP', 'SSRS', 'SSPR', 'SSPP', 'SSPS', 'SSSR', 'SSSP',
           'SSSS']

class GA():
    def __init__(self, gen_num, pop_size):
        """
        init a genetic algorithm with the number of generations that need to be performed
        """

        self.gen_num = gen_num          # the number of generations to create
        self.pop_size = pop_size        # size of the population to be generated
        #self.move_goal = {'R':{'R':1, 'P':0, 'S':0}, 'P':{'R':-0, 'P':1, 'S':0}, 'S':{'R': 0, 'P': 0, 'S':1}}
        self.csv_index = 0              # index of where we are on in the csv file
        self.parents=[]                 # the parents array for every generation
        self.population = []            # population of the genetic algorithm
        self.fitness_scores = []        # keeps track of the fitness scores for the individuals in the population
        self.children =[]


    def gen_population(self):
        for i in xrange(self.pop_size):
            self.population.append(self.gen_individual())
        return self.population

    def gen_individual(self):
        """
        :return: an individual based off
        """
        individual = []
        for x in BFS_SEQ:
            seq = (x,random.choice(['R', 'P', 'S']))
            individual.append(seq)
        return individual

    def fitness_function(self, ind):
        """
        :param ind: individual that is going to be evaluated
        :return: the individuals with they're respoective individual score
        """
        fit_score = 0
        elem = ()
        #fit_scores =[]
        with open('data.csv', 'r') as csv_file:
            csv_reader = csv.reader(csv_file)

            for _ in range(self.csv_index):
                next(csv_reader)

            for row in csv_reader:
                #print ind[1],row[1]
                elem = (row[0], row[1])

                if ind[0] ==row[0] and ind[1] == row[1]:
                    fit_score-=1
                elif (ind[0] == row[0]) and (ind[1] != row[1]):
                    fit_score+=1
                else:
                    fit_score+=0

        """
        print ind[0]
        if ind[0] == elem[0] and ind[1] == elem[1]:
            fit_score -= 1                      # add to the fitness score
        else:
            fit_score += 1
        """
        #self.fitness_scores.append(fit_score)
        return fit_score

    def cross_over(self, par_one, par_two):
        children =[]
        # take the middle index of parOne and parTwo
        mid_idx = math.ceil(81/2)
        # create two indices chosen at random
        firstIdx = random.randint(0, 65)
        secondIdx = random.randint(firstIdx, 81)
        #print(firstIdx,secondIdx)
        # take genes [0:mid_idx], and [mid_idx:] and crossover the two parents
        child_one = []
        child_two =[]
        # iterate through the length of the strings(81)
        for i in xrange(81):
            if i > firstIdx and i < secondIdx:
                child_one.append(par_two[i])
                child_two.append(par_one[i])
            else:
                child_one.append(par_one[i])
                child_two.append(par_two[i])
        # add the two children to the children list
        #self.children.append(child_one)
        #self.children.append(child_two)

        children.append(child_one)
        children.append(child_two)
        return children

    def mutate(self, children):
        # cause a mutation in the children
        # select a random index and then swap the bit
        idx_array = []
        idx_one = 36
        idx_two = 45
        for child in children:
            for i in xrange(81):
                if i >= 36 and i < 45:
                    idx_array.append(child[i])
            idx_array.reverse()
            x =0
            for j in xrange(36,45):
                #print j
                #print "The child and index are ",child[j], idx_array[x]
                child[j] = idx_array[x]
                x+=1
        return children
    def random_move(self, move):
        if move == 'R':
            return random.choice(['P', 'S'])
        elif move == 'P':
            return random.choice(['R','S'])
        else:
            return random.choice(['R', 'P'])

    def parent_select(self, population, fit_scores):
        parents_ = []
        i = 0
        opt_count = (self.pop_size)/2               # optimal is 50% of the population size
        rand_count = (self.pop_size)/2             # choose random number of individuals from the parent/population

        score_select = []
        for i in xrange(len(fit_scores)):
            score_select.append([i,fit_scores[i]])

        score_select.sort(key=lambda x:x[1])                     # the best scores are those with higher scores
        print score_select
        fit_parents = []
        bad_parents = []
        # take the opt_count amount of min values from fitness
        for score in score_select:
            if len(fit_parents) < opt_count:
                fit_parents.append(population[score[0]])

        for parent in fit_parents:
            parents_.append(parent)

        while len(bad_parents) < rand_count:
            rand_item = random.choice(score_select)
            bad_parents.append(population[rand_item[0]])

        print len(bad_parents)
        print len(fit_parents)
        for parent in bad_parents:
            parents_.append(parent)
        # once the parents are chosen a crossover of the parents can be performed
        # perform
        """
        idx = (self.pop_size/4)-1
        x = -1
        
        for i in xrange(len(self.parents)/2):
            self.cross_over(self.parents[i], self.parents[x])
        """
        #print len(self.children)
        return parents_


    def survivor_select(self, children, population, fitness_score):
        # from the list of children take the length/2 best children
        num_of_child = self.pop_size/2
        opt_child = []
        scores=[]
        y = 0
        for child in children:
            child_score = 0
            for i in xrange(len(child)):
                child_score += self.fitness_function(child[i])
            print child, child_score
            scores.append((y,child_score))
            y+=1
        #score_select = []
        #for i in xrange(len(self.fitness_scores)):
         #   score_select.append([i,self.fitness_scores[i]])
        print "The children scores are "
        scores.sort(key=lambda x:x[1])
        print scores
        for i in xrange(len(scores)):
            # the best x individuals should stay
            # the worst x-1 individuals should be swapped out
            if i < self.pop_size/2:
                population[scores[i][0]] = children[scores[i][0]]
                print "replacing population index ", scores[i][0]
        return population
    def find_solution(self, population):
        solution = []
        scores = []
        y = 0
        for pop in population:
            pop_score = 0
            for  i in xrange(len(pop)):
                # iterate through all the individuals in the population
                # find the one with the smallest value
                pop_score += self.fitness_function(pop[i])
            scores.append((y, pop_score))
            y+=1
        scores.sort()
        print "The final survivor scores are ",scores
        solution = population[scores[0][0]]
        return solution

    def alg_run(self):
        """
        The implementation of the genetic algorithm process
         init the population aka population = gen_population
         while criteria not met
            fitness scores = fitness(for individuals within the population)
            select parents based off fitness score
            parents = parent_select
            children = crosseover(parenti parentx)
            child = mutate(children)
            child_scores = fitness(child)
            population = survivor select
        """

        population = self.gen_population()
        print "The initial population is: "
        for pop in population:
            print pop
        idx = 0
        while(idx < self.gen_num):
            print "*********************************************"
            print "GENERATION ", idx + 1
            print "*********************************************"
            fitness_scores = []
            for pop in population:
                fit_score = 0
                for i in xrange(len(pop)):
                   fit_score += self.fitness_function(pop[i])
                print "another individual "
                fitness_scores.append(fit_score)
            print fitness_scores
            parents = self.parent_select(population, fitness_scores)
            x = -1
            children = []
            for i in xrange(len(parents)/2):
                childs = self.cross_over(parents[i], parents[x])
                x-= 1
                children.append(childs[0])
                children.append(childs[1])

            children = self.mutate(children)
            print children
            population = self.survivor_select(children, population, fitness_scores)
            idx+=1
            self.csv_index+=1
            print "The population right now is ", population

        strategy = self.find_solution(population)
        return strategy

def ga_test():
    my_ga = GA(8,16)
    #individual = my_ga.gen_individual()
    hps = my_ga.alg_run()
    hps_normalized = []
    for strat in hps:
        #print strat[1],
        hps_normalized.append(strat[1])
    print "The optimal strategy is ", hps
    print "The strategy normalized is ", hps_normalized
if __name__ == '__main__':
    start_time = time.time()
    ga_test()
    end_time = time.time()
    print "The total runtime in seconds was ", end_time-start_time



"""
**********************************************************
CODE FOR PLOTTING 
**********************************************************
import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d

generations = [1,2,3,4]
pop_size = [1,2,3,4,5,6,7,8]
gen_one = [57346, 46528, 92548, 81740, 50440, 47186, 69694, 49396]
gen_two =[40485, 46527, 47185, 47185, 50441,47185, 69693, 46527]
gen_three = [26980, 46528, 46528, 46528, 46528 , 47186, 69694, 46528]
gen_four = [46527 ,26981, 46251, 46527, 45461, 47187, 69693, 46527]
avgs = []
iter_one_two = [40485,  46527,  46527,  47185,  47185,  47185,  50441,  69693] # corresponds to a generation
iteration_two = []
gens_one = [gen_one, gen_two, gen_three, gen_four]
for i in xrange(4):
    avgs.append(sum(gens_one[i]))
print avgs
for i in xrange(4):
    avgs[i] = (avgs[i]/8)
print avgs
w = [21818,78008, 46642, 55866, 94236, 60800, 102182,80142]
x = [33525, 78009, 46641, 56357,55865, 60799, 102181, 46641]
y = [33526, 33526, 46640, 46640, 55864, 60800, 102182,46640]
z = [ 20509,33527,38625, 46639, 46639,60799, 46639, 46639]

it_two=[w,x,y,z]
avg_two = []
for i in xrange(4):
    avg_two.append(sum(it_two[i]))
for i in xrange(4):
    avg_two[i] = (avg_two[i]/8)
#plt.plot(generations, avgs,'g--', label = ' N =8, Generation Number = 4')
#plt.plot(generations, avg_two,'b--', label = 'N=8 , Generation Number = 4')

gens_two = [1,2,3,4,5,6]
it_three = []
it_three_gen_one = [83142, 44024, 64624, 81168]
it_three_gen_two = [54581, 54067, 81169, 64623]
it_three_gen_three = [53130, 54066, 50158, 64622]
it_three_gen_four = [50159, 54067, 50159, 52927]
it_three_gen_five = [50158, 50158, 50158, 52926]
it_three_gen_six =  [50157, 50157, 50157, 52925]

it_four = []
it_four_gen_one = [75800, 58914, 34444, 73962]
it_four_gen_two = [34445, 34445, 34445, 73963]
it_four_gen_three = [34444, 34444, 34444, 73962]
it_four_gen_four = [34445, 34445, 34445, 34445]
it_four_gen_five = [34444, 34444, 34444, 34444]
it_four_gen_six =  [34443, 34443, 34443, 34443]

it_four =  [it_four_gen_one, it_four_gen_two, it_four_gen_three, it_four_gen_four, it_four_gen_five, it_four_gen_six]
it_three = [it_three_gen_one, it_three_gen_two, it_three_gen_three, it_three_gen_four, it_three_gen_five, it_three_gen_six]
avg_four = []
avg_five = []
for i in xrange(6):
    avg_four.append(sum(it_three[i]))
    avg_five.append(sum(it_four[i]))

for i in xrange(6):
    avg_four[i] = avg_four[i]/4
    avg_five[i] = avg_five[i]/4

#plt.plot(gens_two, avg_four,'c--', label = 'N=4, Generation Number = 6')
#plt.plot(gens_two, avg_five,'r--', label = 'N=4 Generation Number = 6')


it_five =[]
it_six=[]

it_five_gen_one = [68488, 29868, 59306, 51880, 80190, 70662, 33822, 51284, 50350, 71292, 69452, 67500]
it_five_gen_two = [31803, 29867, 59305, 49413, 49157, 70661, 38565, 51285, 50349, 38603, 69451, 50191]
it_five_gen_three = [31802, 49272, 19142, 49412, 36304, 70660, 38602, 51286, 28172, 38602, 69452, 39998]
it_five_gen_four = [11845, 49271, 18813, 28499, 31801, 70661, 29609, 51285, 28171, 38601, 69453, 28171]

it_six_gen_one = [34912, 77682, 60898, 54178, 28636, 38958, 84212, 53642, 58886, 43030, 81362, 60340]
it_six_gen_two = [34013, 37653, 60897, 42359, 28635, 38959, 43031, 43031, 36025, 43031, 81361, 60339]
it_six_gen_three = [37346, 28940, 28952, 42360, 28634, 38958, 31696, 43030, 40230, 41758, 81362, 60338]
it_six_gen_four = [28633, 31695, 28939, 42359, 29147, 38957, 31695, 28437, 40229, 41757, 81361, 27729]

it_five = [it_five_gen_one, it_five_gen_two,it_five_gen_three, it_five_gen_four]
it_six = [it_six_gen_one, it_six_gen_two,it_six_gen_three, it_six_gen_four]
avg_six = []
avg_sev = []
for i in xrange(4):
    avg_six.append(sum(it_five[i]))
    avg_sev.append(sum(it_six[i]))

for i in xrange(4):
    avg_six[i] = avg_six[i]/12
    avg_sev[i] = avg_sev[i]/12

plt.plot(generations, avg_six,'k--', label = 'N=12, Generation Number = 4')
plt.plot(generations, avg_sev,'m--', label = 'N=12 Generation Number = 4')

plt.xlabel('Generation Number')
plt.ylabel('Avg Fitness Score')
plt.title("Efficiency of GA on Population Data ")
plt.legend()
plt.show()


from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt



fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

gen_size =[1,2,3,4,5,6,7,8,9,10]
pop_size =[0,2,4,6,8,10,12,14,16]
fitness_val =[46528, 47186, 49396, 50440, 57346, 69694, 81740, 92548]



ax.scatter(gen_size, pop_size, fitness_val, c='r', marker='o')

ax.set_xlabel('X Label')
ax.set_ylabel('Y Label')
ax.set_zlabel('Z Label')

ax.title("Efficiency of GA on population data ")
"""
