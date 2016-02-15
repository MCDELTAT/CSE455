import numpy
import csv

class TableInput:
	def __init__(self, tempfile):
		self.fileName = tempfile
		self.tempx = []
		self.tempy = []
		self.x = []
		self.y = []

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
		print("R correlation is: ",numpy.corrcoef(self.x,self.y)[0,1])
		print("R2 correlation is: ",(numpy.corrcoef(self.x,self.y)[0,1] * numpy.corrcoef(self.x,self.y)[0,1]))

file = TableInput('input.csv')
file.parse_file()			
file.corr()
