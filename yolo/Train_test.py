import os

srcPath = "/home/datateam/shubham/Try_Yolo_Fresh/darknet_copy/data/agarwood"

f = open("train.txt","w")

for img in os.listdir(srcPath):
	fn, e = os.path.splitext(img)
	if e == ".txt":
		continue
	print("/data/agarwood/"+img)
	f.write("/data/agarwood/"+img+'\n')

f.close()


