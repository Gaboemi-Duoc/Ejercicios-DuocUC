import random

class Map:
	
	data = ""
	lenght = None

	def __init__(self, size):
		self.lenght = size
		choiceCount = 2
		storeCount = 3

		randomNum = random.random()


		for i in range(size):

			if choiceCount <= 0 or storeCount <= 0:
				if choiceCount <= 0 and storeCount <= 0:
					storeCount = 1	
				if choiceCount <= 0:
					self.data += "C"
					choiceCount = 3
				else: choiceCount -= 1
				if storeCount <= 0:
					self.data += "S"
					storeCount = 4
				else: storeCount -= 1
			else:
				if randomNum >= 0.9:
					self.data += "W"
				elif randomNum >= 0.6:
					self.data += "S"
				elif randomNum >= 0.3:
					self.data += "w"
				else:
					self.data += "G"
