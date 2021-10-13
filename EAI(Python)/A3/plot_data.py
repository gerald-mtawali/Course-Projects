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

"""
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