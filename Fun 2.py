from os import system
system("cls")

import keyboard
from datetime import timedelta, datetime

matriz = [
	[[0,1,2], [3,4,5]],
	[[6,7,8], [9, 10, 11]]
	]
FUN = True
x = y = z = 0

xAxis = 0
#	-1		Left
#	0		Front
#	1		Right
#	2		Back

yAxis = 0
#	1  		UP
#	0		Front
#	-1 		DOWN
deltatime = timedelta(seconds=1)
lastTime = datetime.now()
tick = 0
while FUN == True:
	
	#if datetime.now() - lastTime >= timedelta(seconds=1):
		#print("SECOND")

	if datetime.now() - lastTime >= deltatime:
		lastTime = datetime.now()
		#print(tick)
		tick += 1

		if keyboard.is_pressed("left"):
			if xAxis != -1:
				xAxis -= 1
			else:
				xAxis = 2
			print(f"X Axis = {xAxis}")
		elif keyboard.is_pressed("right"):
			if xAxis != 2:
				xAxis += 1
			else:
				xAxis = -1
			print(f"X Axis = {xAxis}")
		elif keyboard.is_pressed("up"):
			if yAxis != 1:
				yAxis += 1
			print(f"Y Axis = {yAxis}")
		elif keyboard.is_pressed("down"):
			if yAxis != -1:
				yAxis -= 1
			print(f"Y Axis = {yAxis}")
		elif keyboard.is_pressed("space"):
			if yAxis == -1 and z != 0:
				z -= 1
			elif yAxis == 1 and z != 2:
				z += 1
			print("SPACE!!!")
			print(f"{x},{y},{z} = {matriz[x][y][z]}")
			
		