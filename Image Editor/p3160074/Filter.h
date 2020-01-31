//Thomas Konstantinidis. 
#include "image.h"
#include <string>
#ifndef FILTER
#define FILTER
namespace image{
	//Definition of general filter
	class Filter {
	public:
		virtual Image operator << (const Image & image) = 0;

	};


	//Definition of linear filter
	class FilterLinear : public Filter {
	protected:
		Color a, c;
	public:
		FilterLinear(Color x, Color y);

		FilterLinear(const FilterLinear &src);

		Image operator << (const Image & image);
	};

	//Definition of gamma filter
	class FilterGamma : public Filter {
	protected:
		float gamma;
	public:
		FilterGamma(float x);

		FilterGamma(const FilterGamma &src);

		Image operator << (const Image & image);
	};
}
#endif