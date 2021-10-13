"""
TASK 1:
Implementation of BackProp Algorithm for Artificial Neural Network

Perform BackProp on a 3+ layered ANN with D(12?) inputs

INPUT LAYER:
    a 1x13 input vector, 12 neurons from the historical moves + extra bias neuron = 1

HIDDEN LAYER:
    number of layers and neurons is arbitrary
    multiply input vector with a (DxH) weight matrix
    have 1x(H+1) hidden layer vector
    multiply first layer with a (H+1)xH weight matrix added 1 for the bias neuron
    multiply second layer with (H+1)xC weight matrix, with C being number of output neurons

OUTPUT LAYER:
    a 1x3 output vector
    compare y'(actual output) with y(expected output) in order to calculate the loss

Activation Function
    either sig or tanh depending on normalized data

BACKPROP ALG:
    Input x: set all the activations for the input layer, according to how it's set up from the csv
    Feedforward: For every layer compute the input variable z_l = (w_(l)*a_(l-1)) + b_l
    Output Error: d_l = element wise product of the output cost gradient descent and
                        sig(z_L) elem_wise(C, sig(z_L)) z_L input at the output layer
    Backprop the error: for every layer starting with the last layer(L-1, L-2,....,2),
                        compute d_l = (transpose(w_(l+1))d_(l+1)) element_wise_multiply
                        sig(z_l)
    Output: The gradient of the cost function is given as dC/dw_jk = a_(l-1)d_l
            and dC/db_l = d_l

every vector contains
"""
import numpy as np

"""
def sigmoid(x, deriv=False):
    out = 1/(1+np.exp(-x))
    if deriv:
        out = out * (1 - out)
    return out
"""
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



class ANN():
    def __init__(self, input_, output_, hidd_num=1, learn_rate=0.5):
        """
        :param input_: The input vectors, in this case it is the two historical moves
        :param output_: the expected output of the neural network , the second column of the data file
        :param hidd_num: the number of hidden layers that the user wants
        """
        # create the vectors for the separate layers
        self.in_vector = np.array(input_)
        self.exp_output = output_
        self.num_hid= hidd_num
        self.hidden_layers=[]                   # the matrix storing all the hidden layer vectors
        self.weights=[]                         # the vector storing all the weight matrices, H+1 weights
        self.out_vector=[]                      # the output vector that is calculated
        self.learn_rate = learn_rate            # store the learning rate of the NN
        self.z_s = []                           # the inputs of the neurons
        self.deltas = []                        # all the deltas associated with the ANN(in reverse order of the )

    def init_weights(self):
        # initializes the weights for the ANN given the input and the output
        for i in xrange(self.num_hid+1):
            # input to hidden layer
            print i
            if i == 0:
                weight = np.random.rand(len(self.in_vector), 5)
                self.weights.append(weight)
            # last hidden layer to the output layer
            elif i == (self.num_hid+1)-1:
                print "at the hidden layer "
                weight = np.random.rand(5, len(self.exp_output))
                self.weights.append(weight)
            else:
                weight = np.random.rand(5,5)
                self.weights.append(weight)

    def feed_forward(self):
        """
        :return: the hidden layers and the output layers after everything has been fed forward
        """
        x = 1
        for i in xrange(self.num_hid+1):
            if i == 0:
                print "Creating Hidden Layer #", i+1
                z_hid = np.dot(self.in_vector, self.weights[0])
                self.hidden_layers.append(sig(z_hid))
                self.z_s.append(z_hid)
            elif i == self.num_hid:
                print "Creating the Output Vector "
                z_out = np.dot(self.hidden_layers[i-1], self.weights[-1])
                self.out_vector = sig(z_out)
                self.z_s.append(z_out)
            else:
                print "Creating Hidden Layer #",i+1
                z_hid = np.dot(self.hidden_layers[i-1], self.weights[x])
                self.hidden_layers.append(sig(z_hid))      # CURRENTLY PERFROMING CALCULATIONS BEFORE SIG
                self.z_s.append(z_hid)
                x+=1

    def loss(self):
        """
        :return: a vector representing the loss for each output vector compared to it's expected
        """
        error = []
        for i in xrange(len(self.exp_output)):
            error.append((self.exp_output[i]-self.out_vector[i])**2)
            error[i] = 0.5*error[i]
        return error

    def back_prop(self):
        """
        - Calculate the error of the output aka the loss() function
        - The greater the error the greater the change in the weight update
        - for every weight connected to that neuron we need to calculate dell_one and dell two
        - dell_one = output_error*output_vector*(1-output_vector)
        - dell_two = learn_rate*(dot(dell_one, transpose(weight_kj)))
        - weight_kj += dell_two
        """
        # calculate the loss of the ANN
        error_vector = np.array(self.loss())
        cost = sum(error_vector)

        print "The error vector of the ANN is \n", error_vector
        print "The cost of the ANN is \n",cost
        idx = 0
        for i in reversed(xrange(len(self.weights))):
            """
            iterate through the weights, starting from the final weight to the first weight in the list
            """
            print i
            if(i == len(self.weights)-1):
                # if we are at the last layer aka the output, take the last element of the z's array
                tmp_vector = np.array(error_vector)[np.newaxis]
                #tmp_vector.reshape(len(self.z_s[i]),1)
                print "The temp vector is  ",tmp_vector, tmp_vector.shape

                out_error = np.multiply(np.transpose(tmp_vector),sig(self.z_s[i], True))
                print "The output error is ", out_error, out_error.shape
                #dell_out = np.dot(out_error, np.transpose(self.weights[i]))
                dell_out = np.dot(self.weights[i], out_error)
                print "The error with which we update the weights is \n", dell_out
                self.weights[i] -= self.learn_rate*dell_out

                print "The updated weight \n", self.weights[i]
                self.deltas.append(dell_out)

            else:
                # change all the weights associated with the hidden layers
                # at this point the indices of the weights and the hidden layers should match up
                hidd_error = np.multiply(np.dot(self.deltas[idx],np.transpose(self.weights[i])),sig(self.z_s[i]))
                self.weights[i] -= self.learn_rate*hidd_error
                print "The updated weight \n",self.weights[i]
                self.deltas.append(hidd_error)
                #print "The error of hidden layer "
                idx += 1



    def back_pass(self):
        """
        The revised version of the backprop algorithm
        1) Calculate the output signals
            - calculate the sig'(output_vector)
            - calculate the output signal = (out_vector[k] - target[k])* sig'(out_vec)
        2) Calculate the Hidden->Output weight gradients, aka the delta value
            - dell_w[j,k] = out_signal[k]*hidden_Nodes[j]
        3) Calculate the output node bias delta using the output signal
            - iterate through the number of output nodes
            - dell_b[k] = out_signal[k]*1.0
        4) Calculate the hidden layer signals
            - iterate through the number of hidden nodes for the specific layer
            - create a sum value
            - iterate through all the values of the next layer(l+1)
            - sum += prev_layer[k]*self.weights[j,k], can be considered as the error
            - then calculate the derivatives of the h_layer or the sig'(hidd_layer[])
            -
        :return:
        """
        error_vector = np.array(self.loss())
        cost = sum(error_vector)
        print "The error vector is \n", error_vector, "\nThe cost is ", cost
        idx = 0
        for i in reversed(xrange(len(self.weights))):
            print i
            if(i == len(self.weights)-1):
                # if we are calculating the output to hidden layer
                # compute the output signals
                deriv = sig(self.out_vector, True)
                print deriv
                out_signals = deriv*(self.out_vector- self.exp_output)
                print "The output signal is \n",out_signals

                # now we need to comput the hidden-to-output deltas
                delt_out = np.zeros((len(self.hidden_layers[0]), len(self.out_vector)))
                print delt_out
                print "The hidden layer we are dealing with is ", self.hidden_layers[i-1]
                for j in xrange(len(self.hidden_layers[0])):
                    for k in xrange(len(self.out_vector)):
                        delt_out[j][k] = out_signals[k]*self.hidden_layers[i-1][j]
                print "The delta matrix is \n", delt_out
                self.deltas.append(delt_out)
                # update the bias neurons as well
            else:
                # calculate the hidden error of the layer
                hidd_layer = self.hidden_layers[i]              # select the current hidden layer
                weight_mat = self.weights[i+1]                  # select the current weight matrix
                hidd_error = np.dot(self.deltas[idx], np.transpose(weight_mat))
                delta_hidd = np.multiply(hidd_error, sig(self.z_s[i], True))

                print "The hidden error vector is \n", hidd_error
                print "The hidden delta is \n", delta_hidd

                self.deltas.append(delta_hidd)                  # add the deltas variable to the list of deltas
                idx += 1

if __name__ == "__main__":
    """
    Example: a) SR,P
             b) SRSR,R
    """
    # output vector is the 'R' = 1,0,0
    output_vector = np.array([1,0,0])
    print output_vector
    # The input will be two historical moves
    input_vector = np.array([0,1,0,1,0,0])
    test_ANN = ANN(input_vector, output_vector, 3)
    test_ANN.init_weights()
    test_ANN.feed_forward()
    print "The Input Layer of the ANN: "
    print test_ANN.in_vector
    print "The transpose of the vector ", test_ANN.in_vector.transpose(), test_ANN.in_vector.shape
    print "The Weights of the ANN:"
    print test_ANN.weights, "\n"
    print "The Hidden Layers of the ANN:"
    print test_ANN.hidden_layers, "\n"
    print "The Output Layer of the ANN: "
    print test_ANN.out_vector,"\n"

    print "The Z_input vector of the ANN: "
    print test_ANN.z_s, "\n"

    print "The Loss of the ANN is:"
    #print test_ANN.loss()

    print "Testing the BACKPROP ALGORITHM"
    #test_ANN.back_prop()
    #print "Checking the differences in weight values \n", test_ANN.weights
    #print "Checking the deltas array for the different layers \n", test_ANN.deltas
    test_ANN.back_pass()
    print "The deltas matrix of the ANN, \n", test_ANN.deltas
