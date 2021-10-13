import csv
import random

def csv_demo():
    filename = "data.csv"
    hist_moves = []
    output = []
    with open(filename, 'r') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if(line_count < 81):
                hist_moves.append(row[0])
                output.append(row[1])
                line_count+=1
            else:
                continue
    print(hist_moves, "\n", len(hist_moves))
    print("\n")
    print(output, "\n", len(output))


def csv_demo_two():
    filename = "data.csv"
    rows = []
    with open(filename, 'r') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for _ in range(0):
            next(csv_reader)
        for row in csv_reader:
            if(line_count < 243):
                rows.append(row)
            line_count +=1

    print(rows, "\n",len(rows))
if __name__ == "__main__":
    #csv_demo()
    csv_demo_two()
