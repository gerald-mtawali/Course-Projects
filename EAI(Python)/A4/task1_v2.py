# Second version of task 1

"""
Implementation of the Backpropagation Algorithm
The ANN Class:
    has L number of Layers: input layer, output layer and H hidden layers
    has H+1 weight matrices
    Has L z_input matrices


"""

import numpy as np
import random
import csv

# HELPER FUNCTIONS
#ACTIVATION FUNCTION
def sig(my_vector,deriv=False):
    #print "My vector is ", my_vector
    out_vector = my_vector.copy()
    for i in xrange(len(out_vector)):
        out_vector[i] = 1 / (1 + np.exp(-out_vector[i]))
    if deriv:
        # returns the derivative; mainly used during backprop algorithm
        for i in xrange(len(out_vector)):
            out_vector[i] = out_vector[i] * (1 - out_vector[i])
    #print "My vector is still ", my_vector, "\nThe output vector is ", out_vector
    return out_vector

def csv_attempt(csv_name, data_size):
    train_set = []
    with open(csv_name, 'r') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        #for _ in range(0):
        #    next(csv_reader)
        for row in csv_reader:
            if(line_count <  100,000):
                dict_data = {'Historic Move': row[0], 'Target': row[1]}
                train_set.append(dict_data)
            line_count +=1
        """
        for exam in train_examples:
            print exam
        """
        random.shuffle(train_set)
        random.shuffle(train_set)
        print '\n'
        train_examples = []
        for i in xrange(data_size):
            train_examples.append(train_set[i])

        #for exam in train_examples:
        #    print exam
        print len(train_examples)
        return train_examples

def one_hot_encode(train_data):
    """
    One hot encode the data to for input and output neurons/layers
    iterate through the elements in the data
    the historical data is changed from a string to an input vector
    the target is changed from a string to the output vector
    return the encoded data for the
    """
    encode_data = []
    encode_key = {'R': [1,0,0], 'P': [0,1,0],'S': [0,0,1]}
    for d in train_data:
        encode = {}
        in_vec = []                 # the input vector for this example
        out_vec =[]                 # the output vector for this example
        for char in d['Historic Move']:
            if char == 'R':
                for e in encode_key['R']: in_vec.append(e)
            elif char == 'P':
                for e in encode_key['P']: in_vec.append(e)
            else:
                for e in encode_key['S']: in_vec.append(e)
        if(d['Target'] == 'R'):
            for e in encode_key['R']: out_vec.append(e)
        elif(d['Target'] == 'P'):
            for e in encode_key['P']: out_vec.append(e)
        else:
            for e in encode_key['S']: out_vec.append(e)
        encode['input'] = in_vec
        encode['target'] = out_vec
        encode_data.append(encode)

    #for data in encode_data:
    #    print data
    print len(encode_data)
    return encode_data

class ANN():
    def __init__(self,num_layers, numOfHiddNeurons, numOfInputNeurons, numOfOutputNeurons, learn_rate =0.5):
        #self.train_data = data
        self.num_layers = num_layers
        self.numOfHiddNeurons = numOfHiddNeurons
        self.numOfInNeurons = numOfInputNeurons
        self.numOfOutNeurons = numOfOutputNeurons
        #self.target = target                # target vector, what we are trying to achieve
        self.weights = []                   # the weights of the ANN
        self.biases = []                    # list of all the biases for the different layers
        self.layers = []                    # the layers in the network, contains the activation function
        self.z_in =[]                       # the z input for all the layers
        self.deltas = []                    # the deltas vector for the
        self.deltas_bias = []               # the delta values for the bias variable
        self.signals = []
        self.learn_rate = learn_rate
        # init the layers of the ANN


    def init_network(self, in_vector, target):
        if len(self.layers) == self.num_layers:
            self.layers = []

        for i in xrange(self.num_layers):
            if i == 0:
                self.layers.append(in_vector)
            elif i == self.num_layers-1:
                output = np.zeros(shape=[len(target)])
                self.layers.append(output)
                self.z_in.append(output)
            else:
                hidd_layer = np.zeros(shape=[self.numOfHiddNeurons])
                self.layers.append(hidd_layer)
                self.z_in.append(hidd_layer)

    def init_weights(self):
        """
        initialise the weights of the ANN
        there are L-1 weights for a neural network with L layers
        calculate the biases for the weights as well
        :return: the weights matrices
        """
        for i in xrange(self.num_layers-1):
            if i == 0:
                weight = np.random.rand(self.numOfInNeurons, self.numOfHiddNeurons)
                self.weights.append(weight)
                bias = np.random.rand(self.numOfHiddNeurons, 1)
                self.biases.append(bias)
            elif i == self.num_layers-2:
                weight = np.random.rand(self.numOfHiddNeurons, self.numOfOutNeurons)
                bias = np.random.rand(self.numOfOutNeurons,1)                       # the bias value
                self.biases.append(bias)
                self.weights.append(weight)
            else:
                weight = np.random.rand(self.numOfHiddNeurons,self.numOfHiddNeurons)
                bias = np.random.rand(self.numOfHiddNeurons,1)
                self.biases.append(bias)
                self.weights.append(weight)

    def feed_forward(self):
        # feed the network forward starting with the first hidden layer
        idx = 0
        k = 0
        for i in xrange(1,self.num_layers):
            #print i, idx
            z_input = np.dot(self.layers[i-1], self.weights[i-1])    # the z input
            #z_input += self.biases[idx]
            for j in xrange(len(z_input)):
                z_input[j] += self.biases[idx][j]
            #print "The z vector that is the input to the layer is ", z_input, z_input.shape
            act_input = sig(z_input,False)
            #print z_input, "\n", act_input
            self.z_in[idx] = z_input
            self.layers[i] = act_input                                          # activation of the z input
            idx +=1
    def set_weights(self, init_weight):
        for i in xrange(self.n_layers-1):
            self.weights.append(np.array(init_weight[i]))
            if i == 0:
                bias = np.random.rand(self.n_hidden, 1)
                self.biases.append(bias)
            elif i == self.n_layers - 2:
                bias = np.random.rand(self.n_output, 1)  # the bias value
                self.biases.append(bias)
            else:
                bias = np.random.rand(self.n_hidden, 1)
                self.biases.append(bias)
    def loss(self, output, target):
        # calculate the loss of the ANN
        error = np.zeros(shape=[len(target)])           # the error vector of the ANN
        #cost = 0
        for i in xrange(len(target)):
            error[i] += (target[i]-output[i])**2
        error = 0.5*error
        cost = sum(error)
        #print "The error vector is ", error, "\nThe cost is ", cost
        return error

    def backprop_error(self, target):
        """
        Function that backward propagates the error
        calculate the
        :return:
        """
        #print "The dell_output ", dell_out
        idx = 0
        for i in reversed(xrange(self.num_layers-1)):
            #print "The current i of the backprop is ",i
            if i == self.num_layers-2:
                # we are at the output+wN layer
                #out_error = self.layers[-1] - target
                out_signal = (self.layers[-1]-target)*sig(self.z_in[i],True)            # error signal
                delta_out = np.zeros((self.numOfHiddNeurons, len(target)))              # HxY
                for j in xrange(self.numOfHiddNeurons):                                 # the input to the weight(row)
                    for k in xrange(len(target)):                                       # the output of the weight(col)
                        delta_out[j][k] = out_signal[k]*self.layers[i][j]
                self.signals.append(out_signal)
                self.deltas.append(delta_out)

                bias_vec = np.zeros(shape=[len(out_signal)])
                for j in xrange(len(out_signal)):
                    bias_vec[j] = out_signal[j]
                self.deltas_bias.append(bias_vec)
            else:
                # iterate through the weight arrays
                hidd_error = np.dot(self.signals[idx], np.transpose(self.weights[i+1]))
                #print "The shape of the hidden error is ",hidd_error.shape
                hidd_signal = np.multiply(hidd_error, sig(self.z_in[i], True))
                delta_hidd = np.zeros((len(self.layers[i]), len(self.layers[i+1])))
                #print "The number of rows for the delta matrix is ", len(self.layers[i]), "The number of columns for the delta matrix is ", len(self.layers[i+1])

                #print "The shape of the next layer is ", self.layers[i].shape
                #print "The shape of the hidden signal is ", hidd_signal.shape
                if(i == 0):
                    # have to multiply with the input vector
                    input_vector = np.array([self.layers[i]])
                    hidd_signal_two = np.array([hidd_signal])
                    #print input_vector, input_vector.shape
                    delta_hidd = np.dot(np.transpose(input_vector), hidd_signal_two)

                else:
                    for j in xrange(len(self.layers[i])):
                        for k in xrange(len(self.layers[i+1])):
                            delta_hidd[j][k] = hidd_signal[k]*self.layers[i][j]
                    #delta_hidd = np.dot(hidd_signal, np.transpose(self.layers[i]))
                #print "The shape of the delta matrix is ", delta_hidd.shape
                self.signals.append(hidd_signal)
                self.deltas.append(delta_hidd)
                bias_vec = np.zeros(shape=[len(hidd_signal)])
                for j in xrange(len(hidd_signal)):
                    bias_vec[j] = hidd_signal[j]
                self.deltas_bias.append(bias_vec)
                idx += 1

    def update_weights(self):
        # weight += -eta*(layers[i])
        idx = len(self.signals)
        #print idx
        for i in xrange(len(self.weights)):
            #delta_transform = np.multiply(self.signals[idx-1], np.transpose(self.layers[i]))
            #print delta_transform
            #print i
            #print "The shape of the delta_transform matrix is ", self.deltas[i].shape
            self.weights[i] += -(self.learn_rate)*self.deltas[idx-1]
            for k in xrange(len(self.deltas_bias[idx-1])):
                self.biases[i][k] += -(self.learn_rate)*self.deltas_bias[idx-1][k]
            idx -=1

    def train(self, trainData, epochs):
        # iterate a certain  number of epochs
        # continuously calculate the error of an ANN for a specific epoch
        #print "The weights of the ANN are\n", self.weights
        #print "The ANN network looks like this right now \n", self.layers
        print "Going to randomly shuffle the training data three times "
        random.shuffle(trainData)
        random.shuffle(trainData)
        random.shuffle(trainData)
        epochCount = []
        errorCount = []
        for i in xrange(epochs):
            print i
            sum_error = 0.0
            count = 0.0
            for example in trainData:
                in_vector = np.array(example['input'])
                target = np.array(example['target'])
                #print "The input vector is ", in_vector
                #print "The target vector is ", target
                self.init_network(in_vector, target)                # initialise the network with the input and target vectors
                self.feed_forward()
                #print "THE NEURAL NETWORK AS IT IS NOW \n", self.layers, "\nTHE WEIGHTS OF THE NETWORK: \n",self.weights
                #print "The output of the network is ", self.layers[-1]
                # Calculate the loss of the ANN
                error_vec = self.loss(self.layers[-1], target)
                #print "The loss vector is \n", error_vec
                sum_error += sum(error_vec)
                self.backprop_error(target)
                self.update_weights()
                count += 1
            errorCount.append(sum_error/count)
            epochCount.append(i+1)
            #print count
            print "EPOCH #", i+1, "\tLEARN RATE: ", self.learn_rate, "\tERROR: ", sum_error/count
            print "-------------------------------------------------------------------------\n\n"
            #break
        return epochCount, errorCount


# **************MAIN OF THE FIRST PART OF TASK 2***********************************
if __name__ == '__main__':
    #input_vector = np.array([0,1,0,1,0,0])
    #output_vector = np.array([0,1,0])
    n_hidd = 12                                  # number of hidden nodes with which we mess about with
    n_in = 12
    n_out = 3
    n_layers = 3
    testANN = ANN(n_layers, n_hidd, n_in, n_out, 0.5)
    # Initialize the weights of the ANN
    testANN.init_weights()
    trainExamples = csv_attempt('data.csv', 500)
    trainData = one_hot_encode(trainExamples)

    epochCount, errorCount = testANN.train(trainData, 100)
    print "The epoch count: epochs = ", epochCount, "\n The error count: errors = ", errorCount
    print "The final weight matrix of the ANN is: \n", testANN.weights
    for i in xrange(len(testANN.layers[1])):
        print "++",testANN.layers[1][i], "++"
    #for w in testANN.weights:
    #    print w
    #print testANN.layers
"""

# *************************************MAIN OF THE SECOND PART OF TASK 2***********************************
weight_container = [[[0.82712636, 0.08846472, 0.23262327, 0.32809298, 0.52577924,
        0.47826361, 0.66086397, 0.93521614, 0.08447844, 0.92181771,
        0.24873478, 0.35713593],
       [0.19034092, 0.98263456, 0.9975876 , 0.04012389, 0.674717  ,
        0.68182085, 0.65900267, 0.54746333, 0.50221755, 0.84734208,
        0.05602997, 0.28565852],
       [0.87296786, 0.41019455, 0.65481757, 0.68292552, 0.11778744,
        0.24248209, 0.98061141, 0.73598904, 0.67905923, 0.66146654,
        0.77197796, 0.41656513],
       [0.32731936, 0.3440892 , 0.13668253, 0.76867146, 0.88587697,
        0.43420025, 0.25866168, 0.32179103, 0.24222296, 0.68154417,
        0.85791517, 0.66482521],
       [0.08907777, 0.64805845, 0.00873487, 0.28515988, 0.42981109,
        0.2892929 , 0.97437745, 0.70916199, 0.65820161, 0.30835953,
        0.79992219, 0.01186371],
       [0.28107703, 0.46467882, 0.86549424, 0.68463948, 0.19957545,
        0.95528395, 0.60191408, 0.27928927, 0.76591172, 0.35038312,
        0.69341473, 0.62928778],
       [0.95114775, 0.28290698, 0.4768827 , 0.55654768, 0.97637575,
        0.93521305, 0.71735265, 0.44317778, 0.48561895, 0.50742016,
        0.10945259, 0.96029331],
       [0.11348533, 0.93940439, 0.97532624, 0.04383069, 0.06879024,
        0.20326671, 0.85710211, 0.0325551 , 0.95939602, 0.1272305 ,
        0.70116875, 0.62171578],
       [0.32005733, 0.71012883, 0.25884665, 0.73141775, 0.67313605,
        0.44368716, 0.94524855, 0.68550883, 0.44291372, 0.62783328,
        0.035366  , 0.72040492],
       [0.85024519, 0.16768847, 0.76852773, 0.17074133, 0.41547021,
        0.16158034, 0.05423377, 0.7150982 , 0.92661208, 0.91009662,
        0.79700849, 0.69969604],
       [0.36618922, 0.60710517, 0.39720664, 0.88727993, 0.03123876,
        0.26261939, 0.634229  , 0.99276957, 0.78526406, 0.44238814,
        0.55996791, 0.0267766 ],
       [0.73384078, 0.62890255, 0.19558941, 0.37550602, 0.06659591,
        0.6454706 , 0.04797559, 0.95474701, 0.51638482, 0.84532723,
        0.98494541, 0.38723532]],
		[[0.31943488, 0.50445712, 0.78097186, 0.96397838, 0.26774926,
        0.02794165, 0.48036445, 0.39862729, 0.72360457, 0.41514525,
        0.93100346, 0.75882356],
       [0.36263245, 0.95024509, 0.28790907, 0.58507137, 0.7052115 ,
        0.84988442, 0.75541384, 0.51468392, 0.02992196, 0.83144749,
        0.09040432, 0.29139877],
       [0.56179391, 0.07281066, 0.53858547, 0.44847148, 0.81551263,
        0.91868409, 0.11772543, 0.31079428, 0.81586711, 0.61663192,
        0.80233624, 0.26357081],
       [0.62306675, 0.33736295, 0.22729627, 0.49519279, 0.64988588,
        0.9863275 , 0.47841511, 0.68432273, 0.27251441, 0.52836318,
        0.22786393, 0.93052213],
       [0.8106621 , 0.26155164, 0.46573282, 0.62938423, 0.77290892,
        0.25408325, 0.80768152, 0.41328074, 0.41178814, 0.74941074,
        0.71546615, 0.17563344],
       [0.84074397, 0.33655215, 0.07791194, 0.17382864, 0.6996205 ,
        0.36776665, 0.98839293, 0.06025075, 0.27943834, 0.63059392,
        0.95440393, 0.20085453],
       [0.08025094, 0.99587579, 0.67408415, 0.63911069, 0.71827571,
        0.97905638, 0.45584386, 0.20344871, 0.64423121, 0.82449579,
        0.48827392, 0.01450921],
       [0.45762119, 0.27183571, 0.44040478, 0.52811383, 0.28794419,
        0.52870039, 0.54497201, 0.68785959, 0.1012623 , 0.32365333,
        0.6176699 , 0.0385317 ],
       [0.47184238, 0.18294416, 0.64731391, 0.01358547, 0.35739928,
        0.00396789, 0.35696496, 0.73365186, 0.27656114, 0.41504502,
        0.41220979, 0.5401396 ],
       [0.40091146, 0.27889703, 0.12180199, 0.24854219, 0.99071026,
        0.00256987, 0.3830512 , 0.24522283, 0.23675226, 0.7051121 ,
        0.26598208, 0.96464115],
       [0.92697083, 0.67274372, 0.33035307, 0.75704264, 0.21086044,
        0.98644784, 0.8702035 , 0.71403245, 0.20478885, 0.48596882,
        0.94805915, 0.73887084],
       [0.13892542, 0.50249212, 0.86811282, 0.17513246, 0.51541639,
        0.13412327, 0.02529476, 0.04021956, 0.80660056, 0.71312335,
        0.58586797, 0.86180168]],
		[[ 2.88697159e-01,  1.52610682e-01,  6.93482062e-01,
         8.46651789e-01,  2.20066772e-01,  5.48050211e-01,
         4.88983868e-01,  5.65602236e-01,  3.27584970e-01,
         6.67599870e-01,  8.28420640e-01,  7.60091984e-01],
       [ 7.56638200e-01,  8.33963177e-01,  7.11870260e-01,
         6.49259591e-01,  4.55939193e-01,  6.00801889e-01,
         1.93109843e-01,  5.79044048e-02,  9.89753460e-01,
         7.53740860e-01,  9.17604894e-01,  3.60295142e-01],
       [ 5.74294799e-02,  5.83941439e-01,  4.04088476e-01,
         6.52104094e-01,  1.04945089e+00,  9.33756972e-01,
         9.52286009e-02,  3.24245988e-01,  2.00269809e-01,
         4.48390157e-01,  8.47530793e-01,  1.28913592e-01],
       [ 9.92418526e-01,  5.88192313e-01,  9.81214380e-01,
         8.04194575e-01,  9.55951812e-01,  5.97738222e-01,
        -9.45052780e-02,  3.65780390e-01,  6.07414601e-01,
         3.28446271e-01,  1.87484893e-01,  5.06524097e-01],
       [ 7.18196925e-01,  4.89478290e-01,  1.88037029e-01,
         9.44271529e-01,  5.16473074e-01,  4.10945647e-01,
         1.71587093e-01,  5.76066217e-01,  4.74293443e-01,
         1.04433499e-01,  1.67780907e-01,  7.23055342e-01],
       [ 3.20237143e-01,  3.38518145e-01,  3.42597199e-01,
         2.46314738e-01,  1.57808510e-01,  2.05045812e-01,
         1.19357330e-01,  2.15111027e-01,  8.62938123e-01,
         5.01652055e-01, -1.35165012e-04,  2.59061531e-01],
       [ 5.71353634e-01,  7.50250806e-01,  5.46181013e-01,
         6.65598795e-01,  7.33633180e-01,  4.07776746e-01,
         7.33167495e-01,  3.98340184e-01,  7.46295515e-01,
         5.80094604e-01,  8.72795141e-01,  8.85293350e-01],
       [ 1.59146653e-01,  5.72718006e-01,  9.43086109e-01,
         4.93279989e-01,  1.27971597e-01,  9.89180596e-01,
         6.04552011e-01,  4.83646381e-01,  5.41867225e-01,
         9.78979559e-02,  1.20196508e-01,  8.29744574e-01],
       [ 1.00756274e-01,  3.87725677e-01,  1.96214102e-01,
         3.43970839e-01,  2.49587709e-01,  4.37301152e-01,
         1.63580113e-01,  7.88395457e-01,  3.59598848e-01,
         8.40984803e-01,  9.58101368e-01,  8.34125231e-02],
       [ 1.14692242e-02,  2.45901077e-01,  5.10541428e-01,
         3.10150712e-01,  2.09902173e-01,  7.89407124e-01,
         4.37626470e-01,  3.30783805e-01,  1.56871450e-01,
         7.30707611e-01,  7.37278036e-01,  5.31983687e-01],
       [ 1.46184342e-01,  4.39377112e-01,  5.30993534e-01,
         5.24303848e-01,  7.12497002e-01,  3.68518108e-01,
         7.17937614e-01,  8.65000577e-01,  4.21334288e-01,
         5.71768891e-01,  4.40269502e-01,  4.41885884e-01],
       [ 9.92027714e-01,  1.83784006e-01,  1.25339572e-01,
         6.82601783e-01,  1.27165845e-01,  9.09033296e-01,
         6.36783429e-01,  2.51268891e-01,  6.98134820e-01,
         7.24073731e-01,  2.09512114e-01,  9.65777477e-01]],
		[[ 0.15366653, -0.39594965,  0.06612392],
       [-0.13187677, -0.17583193, -0.40333997],
       [-0.08753674, -0.2838433 , -0.50686028],
       [-0.18340026, -0.57704693,  0.38004998],
       [ 0.50518339, -0.0698165 , -0.47179848],
       [ 0.44863737,  0.11800398,  0.01839492],
       [-0.09727896,  0.17368705,  0.28065246],
       [ 0.00669403, -0.69324692, -0.07530323],
       [ 0.25597583, -0.04337689, -0.03486171],
       [ 0.27604601, -0.20065069, -0.24744276],
       [ 0.05533295, -0.46690744,  0.29908752],
       [-0.14878999,  0.23229936,  0.12093643]]]
def encode_data(output_vector):
    # return the move to play based upone the output of the ANN
    encodeData = []
    # idx = 0
    bigValue = max(output_vector)
    for idx in xrange(len(output_vector)):
        if bigValue == output_vector[idx]:
            encodeData.append(1)
        else:
            encodeData.append(0)
    print encodeData
    return encodeData

def play_hand(encode_vector):
    # return the move to play based upone the output of the ANN
    encodeKey = {'R': [1, 0, 0], 'P': [0,1,0], 'S':[0,0,1]}
    output = ''
    if(encode_vector == encodeKey['R']):
        output = 'R'
    elif(encode_vector == encodeKey['P']):
        output = 'P'
    else:
        output = 'S'
    print output
    return output
output = [0.89221, 0.124543, 0.0123]
o1 = [0.2042171, 0.31459239, 0.23039889]
o2 = [0.34457683, 0.25667061, 0.17912895]
o3 =  [0.67062251, 0.20692577, 0.12833287]
move = []
#for o in output:
#    x = math.ceil(2*o-1)
#    move.append(round(x)**2)
#print move
input_vector = [1,0,0 ,0,0,1, 0,0,1, 0,1,0]
target = [0, 0, 0]
def agent_plays(rpsAgent, count=0, opp_move='', prev = ''):
    print count
    #input_vec = np.zeros(12)
    print input_vector
    if(count > 1):
        # X_t-2 = X_t-1 and Y_t-2 = Y_t-1
        input_vector[0] = input_vector[6]
        input_vector[1] = input_vector[7]
        input_vector[2] = input_vector[8]

        input_vector[3] = input_vector[9]
        input_vector[4] = input_vector[10]
        input_vector[5] = input_vector[11]

        if(prev == 'R'):
            input_vector[6] = 1
            input_vector[7] = 0
            input_vector[8] = 0
        elif(prev == 'P'):
            input_vector[6] = 0
            input_vector[7] = 1
            input_vector[8] = 0
        else:
            input_vector[6] = 0
            input_vector[7] = 0
            input_vector[8] = 1

        if(opp_move == 'R'):
            input_vector[9] = 1
            input_vector[10] = 0
            input_vector[11] = 0
        elif(opp_move == 'P'):
            input_vector[9] = 0
            input_vector[10] = 1
            input_vector[11] = 0
        else:
            input_vector[9] = 0
            input_vector[10] = 0
            input_vector[11] = 1
    print input_vector
    rpsAgent.init_network(input_vector, target)
    #print rpsAgent.layers
    rpsAgent.feed_forward()
    output = encode_data(rpsAgent.layers[-1])
    output = play_hand(output)
    #print output

    return output

RPS MATCH PLAYING 
if input == "":
     The initializer of everything
    count = 0
    prevMove = ""
    agentBOT = RPS_ANN(5, 12, 12, 3, 0.05)
    agentBOT.set_weights(weight_container)
    print "Initial start of the rounds "
    output = agent_plays(agentBOT)
    prevMove = output
    count += 1
else:
    print "The match has begun ", count
    prevMove = agent_plays(agentBOT, count, input, prevMove)
    output = prevMove
    count += 1
"""
""""""