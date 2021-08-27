import java.*;
public class Exe2{
	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		int M = Integer.parseInt(args[1]);
		double arr[] = new double[M*4];
		int k = 0;
		int j = 0;
		int n = 0;
		
		while( n<M && g(x)!=0) {
			arr[j] = n;
			arr[++j] = x;
			arr[++j] = f(x);
			arr[++j] = g(x);
			x = x - f(x) / g(x);
			j++;
			n++;
		}	
		
		System.out.println("n   Xn       F(Xn)    F'(Xn)");
		int p = 0;
		while( p<n*4 ){
			if(k==0){
				System.out.print(String.format ("%.0f   ", arr[p]));
			    k++;
			}else{
				System.out.print(String.format ("%.4f   ", arr[p]));
				k++;
			}
			if(k==4){
				System.out.println("");
			    k=0;
			}
			p++;
		}
	}

	public static double f(double x) {
		return (Math.atan(x));
	}
	public static double g(double x) {
		return (1/1+x*x);
	}
}


/*(Υπολογισμός sin x) Η άσκηση αυτή είναι ένα πρόγραμμα σε Java το οποίο θα υπολογίζει τιμές της συνάρτησης f (x) = sin x για οποιοδήποτε
x ∈ (−π/2, π/2). Το πρόγραμμα δέχεται ως είσοδο πρώτον μια τιμή x στο άνω
διάστημα και δεύτερον ένα μέγιστο επιτρεπτό σφάλμα E, και κατόπιν να υπολογίζει το sin x μέσω του πο­
λυωνύμου Taylor P n (x) του ημιτόνου γύρω από το x 0 = 0, προσθέτοντας τόσους όρους στο πολυώνυμο48
ώστε εγγυημένα το σφάλμα, δηλαδή η απόλυτη τιμή |R n (x)| του υπολοίπου R n (x), να είναι μικρότε­
ρο του E.


