//Thomas Konstantinidis. 
namespace math
{
	//Declaration of getWidth().
	template <typename T>
	const unsigned int Array2D<T>::getWidth() const
	{
		return width;
	}
	//Declaration of getHeight().
	template <typename T>
	const unsigned int Array2D<T>::getHeight() const
	{
		return height;
	}
	//Declaration of getRawDataPtr().
	template <typename T>
	T * Array2D<T>::getRawDataPtr()
	{
		return buffer.data();
	}
	//Declaration of setData().
	template <typename T>
	void Array2D<T>::setData(const T * const & data_ptr)
	{
		int size = sizeof(*data_ptr);
		buffer.resize(size);
		if (sizeof(buffer) != size)
		{
			perror("The vector couldn't resize!");
			return;
		}
		if (width <= 0 || height <= 0)
		{
			perror("Invalid Vector's width!");
			return;
		}
		for (int i = 0; i < width*height * 3; i++)
		{
			buffer.push_back(data_ptr[i]);
		}

	}
	//Declaration of operator ().
	template <typename T>
	T & Array2D<T>::operator () (unsigned int x, unsigned int y)
	{
		if ((x <= width) && (x <= height) && (x*y >= 0))
		{
			return buffer[width*y + x];
		}
		return 0;
	}
	//Constructor of Array2D.
	template <typename T>
	Array2D<T>::Array2D(unsigned int width, unsigned int height, const T * data_ptr) : width(width), height(height), buffer()
	{
		if (width > 0 && height > 0)
		{
			buffer.resize(width*height);
			setData(data_ptr);
		}

	}
	//Copy constructor Array2D.
	template <typename T>
	Array2D<T>::Array2D(const Array2D &src) : width(src.width), height(src.height), buffer(src.buffer){}
	
	//Destructor of Array2D.
	template <typename T>
	Array2D<T>::~Array2D()
	{
		buffer.clear();
		//cout << "Destructor called succesfully!" << endl;
	}
	//Declaration of operator =.
	template <typename T>
	Array2D<T> & Array2D<T>::operator = (const Array2D<T> & right)
	{
		if (&right == this) {
			return *this;
		}
		width = right.getWidth();
		height = right.getHeight();

		buffer.resize(width*height);
		buffer = right.buffer;
		return *this;
	}
}

