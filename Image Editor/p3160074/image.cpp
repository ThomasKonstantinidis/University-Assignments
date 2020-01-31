//Thomas Konstantinidis. 
#include <iostream>
#include <string>
#include <vector>
#include "vec3.h"
#include "image.h"
#include "../ppm/ppm.h"
#include <locale>
#ifndef IMAGE
#define IMAGE

namespace image {
		//Declaration of load.
		bool Image::load(const std::string & filename, const std::string & format) {
			//This for loop convert taken format to lower case.
			std::locale loc;
			for (std::string::size_type i = 0; i < format.length(); ++i) {
				std::tolower(format[i], loc);
			}
			//Compare the converted format with "ppm".
			if (format.compare("ppm"))
			{
				return 0;
			}
			int h, w;
			//if buffer is not empty , clear(). This way we will be sure that buffer has the appropriate elements after for**.
			if (!buffer.empty()) buffer.clear();

			try {
				//Read the image
				float *img = ReadPPM(filename.c_str(), &w, &h);
				width = w;
				height = h;

				buffer.resize(width*height);
				//**
				for (int i = 0; i < width*height; i++)
				{
					//Pass R,G,B to a single i of buffer. 
					buffer[i] = Color(img[i * 3], img[i * 3 + 1], img[i * 3 + 2]);
				}
				return 1;
			}
			catch (void *error) {
				return 0;
			}
		}

		bool  Image::save(const std::string & filename, const std::string & format) {
			//This for loop convert taken format to lower case.
			std::locale loc;
			for (std::string::size_type i = 0; i < format.length(); ++i) {
				std::tolower(format[i], loc);
			}
			//Compare the converted format with "ppm".
			if (format != "ppm"){
				return 0;
			}
			//Float data is nessecery because WritePPM need float argument.
			float *data = new float[width * height * 3];

			for (int k = 0; k < width*height; k++) {
				data[k * 3] = buffer[k].r;
				data[k * 3 + 1] = buffer[k].g;
				data[k * 3 + 2] = buffer[k].b;
			}

			try {
				//Write image to a new.
				WritePPM(data, width, height, filename.c_str());
				delete data;
			}
			catch (void *error) {
				return 0;
			}
			return 1;
		} 
	
}

#endif
