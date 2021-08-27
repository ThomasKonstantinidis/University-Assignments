import java.*;
public class Exe1{
	public static void main(String[] args) {
		double a = Double.parseDouble(args[0]);
		double b = Double.parseDouble(args[1]);
		double m = ( a + b )/2;
		double arr[] = new double[140];
		int n = Integer.parseInt(args[2]);
		int k = 0;
		int i = 0;
		while( i < n ) {
			arr[k] = i+1;
			arr[++k] = a;
			arr[++k] = b;
			arr[++k] = m;
			if( f(m) * f(a) < 0 ){
				arr[++k] = f(m);
				arr[++k] = f(a);
				arr[++k] = f(b);
				b = m;
			}else if(f(m) * f(b) < 0){
				arr[++k] = f(m);
				arr[++k] = f(a);
				arr[++k] = f(b);
				a = m;
			}
			m = ( a + b )/2;
			i++;
			k++;
		}	
		System.out.println("Repeat   a      b      m    f(m)    f(a)    f(b)");
		for(int p=1; p<141; p++){
			System.out.print(String.format ("%.2f   ", arr[p-1]));
			if(p%7 == 0)
				System.out.println("");
		}
				
	}

	public static double f(double x) {
		return x - Math.cos(x);
	}
}
