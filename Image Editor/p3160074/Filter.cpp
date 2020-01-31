//Thomas Konstantinidis.
#include "Filter.h"
#include <math.h>
namespace image
{
	// Linear constructor
	FilterLinear::FilterLinear(Color x, Color y) {
		a = x;
		c = y;
	}
	//Linear copy constructor
	FilterLinear::FilterLinear(const FilterLinear &src) : a(src.a), c(src.c) {}
	

	// Gamma constructor
	FilterGamma::FilterGamma(float x) {
		if (x < 0.5){ 
			gamma = 0.5;
		}
		else if (x > 2) {
			gamma = 2;
		}
		else {
			gamma = x;
		}
		
	}
	// Gamma copy constructor
	FilterGamma::FilterGamma(const FilterGamma &src) : gamma(src.gamma){}

	// Operators
	Image FilterLinear::operator << (const Image & image) 
	{
		//Create an Image type "Nim" to eliminate the data.resize which wants const parametres 
		Image NIm(image);
		Color * data= NIm.getRawDataPtr();
		Color v;
		for (int i = 0; i < NIm.getWidth()*NIm.getHeight(); i++) {
			
			v = a * data[i] + c;
			v = v.clampToUpperBound(1.0);
			data[i] = v;
		}
		return NIm;
	}

	
	Image FilterGamma::operator << (const Image & image) {
		//Create an Image type "Nim" to eliminate the data.resize which wants const parametres 
		Image NIm(image); 
		Color * data = NIm.getRawDataPtr();
		Color v;
		for (int i = 0; i < NIm.getWidth()*NIm.getHeight(); i++) {
			//pow() raises v to a power.
			v.r = pow(data[i].r, gamma);
			v.g = pow(data[i].g, gamma);
			v.b = pow(data[i].b, gamma);
			
			data[i].r = v.r;
			data[i].g = v.g;
			data[i].b = v.b;
		}
		return NIm;
	}
}