import numpy #used to find correlation
import scipy.stats #used to find pearson signifigance
import math
import csv

class TableInput:
	def __init__(self, tempfile):
		self.fileName = tempfile
		self.tempx = []
		self.tempy = []
		self.x = []
		self.y = []
		self.r = 0.0
		self.r2 = 0.0

	def parse_file(self):
		with open(self.fileName) as csvfile:
			readCSV = csv.reader(csvfile, delimiter=',')
			for row in readCSV:
				self.tempx.append(row[1])
				self.tempy.append(row[2])

			self.x.extend(self.tempx)
			self.y.extend(self.tempy)
			self.x = list(map(int, self.x))
			self.y = list(map(float, self.y))

	def corr(self):
		self.r = numpy.corrcoef(self.x,self.y)[0,1]
		self.r2 = (self.r*self.r)
		print("R correlation is: ", self.r)
		print("R2 correlation is: ", self.r2)

	def signif(self):
		t = (self.r*(math.sqrt(5)))/(math.sqrt(1-(self.r2)))
		sigfinal = scipy.stats.pearsonr(self.x,self.y)[1]
		p = (1-(scipy.stats.t.sf(t, 5)))
		print("p Value is: ", p)
		print("t Value is: ", t)
		print("2*(1-p) Value is: ", sigfinal)

file = TableInput('input.csv')
file.parse_file()			
file.corr()
file.signif()

#4 by add by 4, 2 Modified