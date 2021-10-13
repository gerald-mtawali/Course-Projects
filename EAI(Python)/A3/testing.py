import random

"""
individual = []
for i in xrange(40):
    individual.append(random.choice(['R', 'P', 'S']))

print individual

word = 'my goodness'

dict_ = {word:'okay'}
dict_[word] = 'goood'

print(dict_)

scores = [1,5,3,6,7,2,4]
temp = scores
print scores
temp.sort()
temp.reverse()

print scores, "\n", temp

my_list = []
i =0
while len(my_list) < 4:
    my_list.append(i)
    i+=1
print my_list
unsorted_list = [['R', 12],['P', 3], ['S', 1], ['R',10], ['P', 22], ['S', 19] ]
unsorted_list.sort(key=lambda x: x[1])

print unsorted_list

"""
# find two random points in a list and crossover the elements within the lists
parOne = []
parTwo = []
childOne = []
childTwo = []

for i in xrange(20):
    if i%2==0:
        parOne.append((i+2)*3)

for i in xrange(20):
    if i%2 == 0:
        parTwo.append((i+1)*2)
print(parOne, '\n', parTwo)

idx = random.randint(0,10)
secondIdx = random.randint(idx,10)
for i in xrange(10):
    if i>5 and i < 10:
        childOne.append(parTwo[i])
        childTwo.append(parOne[i])
    else:
        childOne.append(parOne[i])
        childTwo.append(parTwo[i])
print(childOne)
print(childTwo)

# have a list of certain length
an_array = [0, 2, 4, 6, 8, 10, 12, 14]
product = []
#print len(an_array)
x = -1

parents = []

i = 0
while(i< 8):
    chromosome = []
    for j in xrange(10):
        chromosome.append(random.randint(0,1))
        print j
    parents.append(chromosome)
    i+=1
print parents
chosen_parents = parents[:6]
print chosen_parents
print len(chosen_parents)
print len(parents)
idx = (len(parents)/4)-1
x = -1
for i in xrange(len(chosen_parents)/2):
    print chosen_parents[i],chosen_parents[x]
    x -= 1
print "get the other parents "
for i in xrange(0,idx,1):
    print chosen_parents[i],chosen_parents[i+idx]

my_tups = []
for i in xrange(5):
    my_tups.append((i,i+1))

#print my_tups
for i in xrange(36,45):
    print i

my_size = 8
for i in xrange(0, my_size/2):
    print i
for i in xrange(my_size/2, my_size):
    print i