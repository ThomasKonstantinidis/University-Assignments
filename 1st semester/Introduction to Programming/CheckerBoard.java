import java.awt.*;
import acm.graphics.*;
import acm.program.*;

public class CheckerBoard extends GraphicsProgram   {
	public void run()   {
		int Size= readInt("Enter size: ");
		int n=0;
		while(n==0){
			int k=8;
		    while(k<=Size){
				if(k==Size){
					n=1;
				}
			    k=k*2;
		    }
			if(n==0){
				int S= readInt("Try again: ");
				Size=S;
			}			
		}
	    double squareSize = (double) getHeight() / Size ;
        for(int i = 0; i < Size; i++){
            for(int j = 0; j < Size; j++){
                double x = (j * squareSize) + (getWidth()-getHeight())/2;
                double y = squareSize * i;
                GRect square = new GRect(x,y,squareSize,squareSize);
                square.setFilled((i + j) % 2 != 0);
				square.setFillColor(Color.GRAY);
                add(square);
				GOval oval = new GOval(x+5,y+5,squareSize-10,squareSize-10);
			    if(i<4){
				    if(j%2==0){
					    if(i==1){
						    oval.setFilled(true);
						    oval.setFillColor(Color.RED);
						    add(oval);
						}
					}
					else{
						if(i==0){
						    oval.setFilled(true);
						    oval.setFillColor(Color.RED);
						    add(oval);
						}
						else if(i==2){
						    oval.setFilled(true);
						    oval.setFillColor(Color.RED);
						    add(oval);
						}
					}
				}
				else if(i>(Size-4) && i<=Size){
					if(j%2==0){
						if(i==(Size-1)){
						    oval.setFilled(true);
						    oval.setFillColor(Color.BLACK);
						    add(oval);
						}
						else if(i==(Size-3)){
						    oval.setFilled(true);
						    oval.setFillColor(Color.BLACK);
						    add(oval);
						}
					}
					else{
						if(i==(Size-2)){
						    oval.setFilled(true);
						    oval.setFillColor(Color.BLACK);
						    add(oval);
						}
					}
				}
            }
        }
    }	
}