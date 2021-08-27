import java.awt.*;
import acm.graphics.*;
import acm.program.*;

public class LineGraph extends GraphicsProgram{


	public void run() {
		int K = readInt("Enter how many numbers you want: ");
		double[] array = new double[K];
		double[] x1 = new double[K];
		double[] y1 = new double[K];
		double i = readDouble("Number: ");
	    array[0]=i;
		double min=i;
		double max=i;
		int M=1;
		while( M < K ){
			i = readDouble("Number: ");
			array[M-1] = i;
			if(i>max){
			    max=i;
			}
			if(i<min){
				min=i;
			}
			M+=1;
		}
		double s=(getHeight()/max)/K;
		double N=getWidth()/K;
		int p;
		x1[0] = 1;
		y1[0] = (getHeight()- array[0]*s);
		for(p=0; p<K-1; p=p+1){
			GLine Border1 = new GLine( N*(p+1), (getHeight()- array[p+1]*s), x1[p], y1[p]);
			Border1.setColor(Color.RED);
			add(Border1);
			x1[p+1] = N*(p+1);
			y1[p+1] = (getHeight()- array[p+1]*s);
		}
		
    }
	
}