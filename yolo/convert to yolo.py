import os
import cv2


def convert(size, box):
	dw = 1./size[0]
	dh = 1./size[1]
	x = (box[0] + box[1])/2.0
	y = (box[2] + box[3])/2.0
	w = box[1] - box[0]
	h = box[3] - box[2]
	x = x*dw
	w = w*dw
	y = y*dh
	h = h*dh
	return (x,y,w,h)


srcPath = "/home/datateam/shubham/assam"
data = "/home/datateam/Vishal/ImageCS/Agarwood/Agarwood"

for image in os.listdir(srcPath):

	if "txt" in image:
		continue

	fname,_ = os.path.splitext(image)
	try:
		img = cv2.imread(os.path.join(srcPath, image))
		# print(img.shape)
		h,w = img.shape[:2]
		datafile = os.path.join(data, image+".txt")
	except Exception as ex:
		print type(img)

	try:
		# print(fname)
		# print(os.path.join(srcPath, fname+".jpg.txt"))
		print(datafile)
		with open(datafile) as f:
			txt = open(os.path.join("/home/datateam/Desktop/dummy", fname+".txt"),"w")
			# print "hihi"
			coordsLst = f.read().split('\n')[1:-1]
			for coord in coordsLst:
				vals = coord.split(",")
				x1 = int(vals[0])
				y1 = int(vals[1])
				x2 = int(vals[2])
				y2 = int(vals[3])
				bb = convert((w,h), (x1,x2,y1,y2))
				txt.write("0 " + " ".join([str(a) for a in bb]) + '\n')
			txt.close()
	except IOError as e:
		print(image+"Ex")
		# os.rename(os.path.join(srcPath, image), os.path.join("./deleted", image))
	# break