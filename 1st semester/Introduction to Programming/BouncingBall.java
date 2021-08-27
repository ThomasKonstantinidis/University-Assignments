import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class BouncingBall extends GraphicsProgram{
	
	private static final int PAUSE_TIME = 2;
	private static final double BALL_SIZE = 100;
	
	public void  run(){
		
		double X = (getWidth()-BALL_SIZE) /2;
		double Y = (getHeight()-BALL_SIZE)/2;		
		GOval ball = new GOval(X,Y,BALL_SIZE,BALL_SIZE);
		ball.setFilled(true);
		ball.setFillColor(Color.RED);
		add(ball);
		double dx = 1;
		double dy = 1;

		while (true){
			ball.move(dx,dy);
			pause(PAUSE_TIME);
			double x = ball.getX();
			double y = ball.getY();
			
			if (x >=getWidth()-BALL_SIZE){
				dx=-dx;
			}
			if (y>= getHeight()-BALL_SIZE){
				dy=-dy;
			}
			if (x==0){
				dx=-dx;
			}
			if (y==0){
				dy=-dy;
	        }
		}	
    }
}	