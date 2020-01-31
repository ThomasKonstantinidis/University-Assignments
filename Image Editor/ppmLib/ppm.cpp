#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <vector>
#include "ppm.h"

using namespace std;


namespace image {

	float * ReadPPM(const char * filename, int * w, int * h) {

		int width, height, maxColor;

		//Binary file. Open it for reading. 
		ifstream file(filename, ios::in | ios_base::binary);

		//Check if file opened .
		if (!file.is_open()) {
			perror ("Opening file");
			return 0;
		}
		//Measures the elements of file, from beginning to end.
		file.seekg(0, file.end); 

		//tellg() pass the length of file to the integer.
		int length = file.tellg();
		if (length == 0) {
			perror("File is empty!");
		}
		//Puts the position to beginning in input sequence.
		file.seekg(0, file.beg);

		//The type of file(ex. P6 )
		string header;

		//Passes the header from file metadata.
		file >> header;

		//Check if file is the correct type.
		if (strcmp(header.c_str(), "P6") != 0) {
			perror("This is not a P6 format.");
			return 0;
		}
		//Passes the width from file metadata.
		file >> width;
		if (width <= 0) {
			perror("Invalid width.");
		}
		//Passes the height from file metadata.
		file >> height;
		if (height <= 0) {
			perror("Invalid height.");
		}
		//Passes the maxColor from file metadata.
	    file >> maxColor;
		if (maxColor != 255) {
			perror("Invalid Max Value of Color.");
		}
		*w = width;
		*h = height;
		size_t size = 3*width*height;
		unsigned char *buffer = new unsigned char[size];
		float *pixels = new float[size];

		//This is for the character that is between metadata and binary.
		file.get(); 
		//Reads the binary file and fills the buffer.
		file.read((char *)buffer, size);
		//Change the value to be [0,1] limited.
		for(size_t i=0; i<size; i++){
			pixels[i] = buffer[i] /255.0f;
		}
		file.close();
		//Dismiss memory
		delete[] buffer;
		return pixels;
	}

	bool WritePPM(const float * data, int w, int h, const char * filename) {

		size_t size = 3*w*h;
		unsigned char *buffer = new unsigned char[size];
		for (size_t i = 0; i < size; i++) {
			//Change the value to be [0,255] again.
			buffer[i] = (unsigned char) (data[i] * 255);
		}
		//Binary file. Open it for writing.
		ofstream myfile(filename, ios::out | ios::binary);
		if (myfile.is_open()) {
			//Write to the image the metadata.
			myfile << "P6" << "\n" << w << "\n" << h << "\n" << "255" << endl;
			//Write the elements of the buffer to myfile.
			myfile.write((char*)buffer, size);
			myfile.close();
			delete[] buffer;
			return 1;
		}
		delete[] buffer;
		return 0;
	}

}
