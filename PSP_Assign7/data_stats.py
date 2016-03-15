import csv
from llist import sllist, sllistnode

class TableInput:
	#define class variables
	def __init__(self, tempfile):
		self.fileName = tempfile
		self.tempx = []
		self.tempy = []
		self.x = []
		self.y = []

	#parse the given file and place the values in class x & y
	def parse_file(self):
		with open(self.fileName) as csvfile:
			readCSV = csv.reader(csvfile, delimiter=',')
			for row in readCSV:
				self.tempx.append(row[1])
				self.tempy.append(row[2])
				#print (row[1])
			self.x.extend(self.tempx)
			self.y.extend(self.tempy)
			self.x = list(map(int, self.x))
			self.y = list(map(float, self.y))

	#bubble sort implementation. sort by the selected value of the pair.
	def sortData(self, sortIndex):
		#setup empty list
		empty_lst = sllist()
		#fill the list
		print("The length of the input is: ",len(self.x))
		for i in range (0,len(self.x)):
			empty_lst.append([self.x[i],self.y[i]])
		length = len(empty_lst)
		print("The length of the list is: ",length)
		print("The unsorted list is: ",empty_lst)
		#run bubble sort
		for i in range(len(empty_lst)):
			for j in range(len(empty_lst)-1-i):
				if empty_lst[j][sortIndex] > empty_lst[j+1][sortIndex]:
					empty_lst[j][sortIndex], empty_lst[j+1][sortIndex] = empty_lst[j+1][sortIndex], empty_lst[j][sortIndex] #swap
		print("The sorted list is: ",empty_lst)

file = TableInput('./data/psp7data.csv')
file.parse_file()			
file.sortData(0) #sort by first item in pair
print('')
file.sortData(1) #sort by second item in pair
