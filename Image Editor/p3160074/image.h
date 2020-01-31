//Thomas Konstantinidis. 
#pragma once

#include "vec3.h"
#include "imageIO.h"
#include "array2d.h"

using namespace math;

namespace image {
	//define the Color as  a Vec3<float> in namespace image.
	typedef Vec3<float> Color;

	class Image : public Array2D<Color>, public ImageIO {
	public:
		//Constructor of Image
		Image(int width, int height) : Array2D(width, height) {}
		//Load and Save overrided, because is redefined with the same signature.
		bool load(const std::string & filename, const std::string & format) override;
		bool save(const std::string & filename, const std::string & format) override;
	};
}