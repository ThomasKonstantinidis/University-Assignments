//Thomas Konstantinidis.
#include <iostream>
#include <string>
#include "../ppm/ppm.h"
#include "image.h"
#include "Filter.h"

using namespace std;
using namespace image;

int main(int argc, char* argv[]) {

	//midle: Take the image##.ppm from command
	//file: ***
	string file, midle;
	char* path;
	path = argv[argc - 1];
	midle = (string)path;
	try {
		//Essentially if given full path of data in command line, seperate the name of image from path of file. 
		if (midle.find("\\")) {
			char full[_MAX_PATH];
			if (_fullpath(full, path, _MAX_PATH) != NULL) {
				midle = (string)full;
				file = midle.substr(midle.find_last_of("\\") + 1);
			}
			else {
				printf("Invalid path\n");
			}
		}
		else {
			file = path;
		}

	}catch (...) {
		return 0;
	}
	
	//Create a default Image to load the data from command's image
	image::Image img(0, 0);
	//Load the data from command image to img
	img.load(file.c_str(), "ppm");
	//The new filtered name.
	string newName;

	//Know if expression "filtered_" already exist. 
	bool flag = true;

	//Possible filters that we will use. Anything else return.
	enum Cf { linear, gamma, blur };

	//Holds the type of filter.
	Cf type;
	//For linear filter.
	Color x, y;
	//For gamma filter.
	float k;
	image::Filter *f;
	
	
	
	if (string(argv[1]) == "filter") {
		for (int i = 2; i < (argc - 1); i++) {
			if (string(argv[i]) == "-f") {
				//These if search for the type of filter. I tried with static_cast<Cf> but it didn't work.
				if (string(argv[i+1]) == "gamma") {
					type = gamma;
					i++;
				}
				else if (string(argv[i+1]) == "linear") {
					type = linear;
					i++;
				}
				else {
					cout << "ERROR: Invalid filter" << endl;
					return 0;
				}
				//For any possible filter do the necessary.
				switch (type) {
				case gamma:

					k = atof(argv[++i]);
					image::Filter *f;
					f = new image::FilterGamma(k);
					img = *f << img;
					//Check if image didn't take the filtered_ extension.
					if (flag) {
						newName = "filtered_" + file;
						flag = false;
					}
					delete f;
					break;
				case linear:
					x = (atof(argv[++i]), atof(argv[++i]), atof(argv[++i]));
					y = (atof(argv[++i]), atof(argv[++i]), atof(argv[++i]));
					f = new image::FilterLinear(x,y);
					img = *f << img;
					if (flag) {
						newName = "filtered_" + file;
						flag = false;
					}
					delete f;
					break;
				default:
					break;
				}
			}else {
				perror("Invalid input. Details: -f is missing or incorrect.");
				return 0;
			}
		}
		img.save(newName, "ppm");
	}else {
		perror("Invalid input. Details: filter is missing or incorrect.");
		return 0;
	}
	return 1;
}