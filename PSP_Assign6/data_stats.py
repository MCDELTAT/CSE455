import numpy #used to find correlation
import scipy.stats #used to find pearson signifigance
import math
import csv

class TableInput:
	#define class variables
	def __init__(self, tempfile):
		self.fileName = tempfile
		self.tempx = []
		self.tempy = []
		self.x = []
		self.y = []
		self.r = 0.0
		self.r2 = 0.0
		self.slope = 0.0
		self.intercept = 0.0
		self.std_err = 0.0

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

	#find the correclation between the two sets of values x & y
	def corr(self):
		self.r = numpy.corrcoef(self.x,self.y)[0,1]
		self.r2 = (self.r*self.r)
		print("R correlation is: ", self.r)
		print("R2 correlation is: ", self.r2)

	#find the signifigance between values of x & y
	def signif(self):
		t = (self.r*(math.sqrt(5)))/(math.sqrt(1-(self.r2)))
		sigfinal = scipy.stats.pearsonr(self.x,self.y)[1]
		p = (1-(scipy.stats.t.sf(t, 5)))
		print("p Value is: ", p)
		print("t Value is: ", t)
		print("2*(1-p) Value is: ", sigfinal)

	#find the linear regression of values x & y
	def lineregress(self):
		self.slope, self.intercept, r_value, p_value, self.std_err = scipy.stats.linregress(self.x,self.y)
		print("Slope of line(B1) is: ", self.slope)
		print("Intercept of line (B0) is: ", self.intercept)
		print("Standard error is: ", self.std_err)

	#estimate lines of code based off confidence values
	def locInterval(self):
		n = len(self.x) #of values in x
		projection = (self.intercept+400)*self.slope
		sum = 0
		for x in range(0, n):
			sum += ((self.y[x]-self.intercept-(self.slope*self.x[x]))**2) 
		variance = (1/(n-2))*sum
		stddev = math.sqrt(variance)
		print("The Standard Deviation is: ",stddev)
		#do calculation for 85 confidence
		total = 0;
		for x in range(0,n): #get the average of all my x input values
			total += self.x[x]
		xavg = total/n
		denom = 0 # for denominator of sqrt value
		for x in range(0,n):
			denom += ((self.x[x]-xavg)**2)
		range85 = (self.slope*stddev*(math.sqrt(1+(1/n)+(((400-xavg)**2)/(denom)))))
		print("The range for 85 conf. is: ",range85)
		lpi85 = projection - range85
		upi85 = projection + range85
		print("The LPI for 85 conf. is: ",lpi85)
		print("The UPI for 85 conf. is: ",upi85)
		print('')
		#do calculation for 95 confidence
		range95 = (2.447*stddev*(math.sqrt(1+(1/n)+(((400-xavg)**2)/(denom)))))
		print("The range for 95 conf. is: ",range95)
		lpi95 = projection - range95
		upi95 = projection + range95
		print("The LPI for 95 conf. is: ",lpi95)
		print("The UPI for 95 conf. is: ",upi95)

file = TableInput('./data/LOCdata.csv')
file.parse_file()			
file.corr()
file.signif()
file.lineregress()
print('')
file.locInterval()
