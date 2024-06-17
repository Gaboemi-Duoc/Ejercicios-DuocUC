import random

class Map:
	
	data = ""
	lenght = None

	def __init__(self, len):
		self.lenght = len
		choiceCount = 2
		storeCount = 3

		randomNum = random.random()


		for i in range(len):

			if choiceCount <= 0 or storeCount <= 0:
				if choiceCount <= 0 and storeCount <= 0:
					storeCount = 1	
				if choiceCount <= 0:
					map += "C"
					choiceCount = 3
				else: choiceCount -= 1
				if storeCount <= 0:
					map += "S"
					storeCount = 4
				else: storeCount -= 1
			else:
				if randomNum >= 0.9:
					map += "W"
				elif randomNum >= 0.6:
					map += "S"
				elif randomNum >= 0.3:
					map += "w"
				else:
					map += "G"
