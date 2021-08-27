import acm.program.*;
public class exercise1 extends Program{
	public void run(){
		int d = readInt("Enter the number of current day: ");
		int month = readInt("Enter the current month: ");
		int year = readInt("Enter the current year: ");
		int day = readInt("Enter the current day as a number.For example: if day is Sunday put the number 0");
		int d2 = readInt("Enter the day you like: ");
		int m2 = readInt("Enter the month you like: ");
		int y2 = readInt("Enter the year you like: ");
		int sumup=sum(d, month, year, day, d2, m2, y2);
		int ddd=dd(sumup, day);
		String name;
		if(ddd==0){
			name="Sunday";
		}
		else if(ddd==1){
			name="Monday";
		}
		else if(ddd==2){
			name="Tuesday";
		}
		else if(ddd==3){
			name="Wednesday";
		}
		else if(ddd==4){
			name="Thursday";
		}
		else if(ddd==5){
			name="Friday";
		}
		else{
			name="Saturday";
		}
		println("Days since: " +sumup +" and " +d2 +"/" +m2 +"/" +y2 +" was " +name);
		
		
		
		
	}
	private int sum(int d, int month, int year, int day, int d2, int m2, int y2){
		int sumup=0;
		if (y2<=(year-1)) {   
		    int i=((year-y2)-1);
			while(i>0){
				if (((year-i)%4)==0 && (year-i)/100!=0 || ((year-i)%400)==0){
					sumup=sumup+366;
				}	
				else{
					sumup=sumup+365;
				}
				i=(i-1);
			}
			int monthfor=month-1;                                                 
			int sumup2=0;
		    while (monthfor>7){                                                   
				if (monthfor%2!=0){
					sumup2=sumup2+30;
				}
				else{
					sumup2=sumup2+31;
				}
				monthfor=monthfor-1;
			}
			while (monthfor>0 && monthfor<8){                                     
				if (monthfor%2!=0){
					sumup2=sumup2+31;
				}
				else{
					sumup2=sumup2+30;
				}
				monthfor=monthfor-1;
			}
			if ((year%4)==0 && (year/100)!=0 || (year%400)==0){
				if(sumup2>59){
				    sumup=sumup-1;
				}
                sumup=sumup+d+sumup2;				
			}
			else if(((year%4)!=0 || (year/100)==0) && (year%400)!=0){
				if(sumup2>59){
				    sumup=sumup-2;
				}
                sumup=sumup+d+sumup2;
			}
			int mfor=m2+1;
			int sumup3=0;
			while (mfor>1 && mfor<8){
				if (mfor%2==0){
					sumup3=sumup3+30;
				}
				else{
					sumup3=sumup3+31;
				}
				mfor=mfor+1;
			}
			while (mfor>7 && mfor<13){
				if (mfor%2==0){
					sumup3=sumup3+31;
				}
				else{
					sumup3=sumup3+30;
				}
				mfor=mfor+1;
			}
			if ((y2%4==0 && y2/100!=0 || y2%400==0)){
				if(m2+1<3){
				    sumup=sumup-1;
				}
                sumup=sumup+sumup3;
			}
			else if((y2%4)!=0 || (y2/100)==0 && (y2%400)!=0){
				if(m2+1<3){
				    sumup=sumup-2;
				}
                sumup=sumup+sumup3;
			}
			if (m2==1 || m2==3 || m2==5 || m2==7 || m2==8 || m2==10 || m2==12){
				sumup=sumup+(31-d2);
			}
			else if(m2==2 && ((y2%4==0 && y2/100!=0) || y2%400==0)){
				sumup=sumup+(29-d2);
			}
			else if(m2==2 && ((y2%4)!=0 || (y2/100)==0) && (y2%400)!=0){
				sumup=sumup+(28-d2);
			}
			else{
				sumup=sumup+(30-d2);
			}
		}
		else{
			int mfor=m2+1;
			int sumup3=0;
			if(month>(m2+1)){
				while (mfor>1 && mfor<8 && mfor<month){
				    if (mfor%2==0){
					   sumup=sumup+sumup3+30;
				    }
				    else{
					   sumup=sumup+sumup3+31;
				    }
				    mfor=mfor+1;
			    }
			    while (mfor>7 && mfor<13 && mfor<month){
				    if (mfor%2==0){
					   sumup=sumup+sumup3+31;
				    }
				    else{
					   sumup=sumup+sumup3+30;
				    }
				    mfor=mfor+1;
			    }
			    if (year%4==0 && year/100!=0 || year%400==0){
					if ((m2+1)<3){
				       sumup=sumup-1+d;
					}
				    sumup=sumup+sumup3;
			    }
			    else if((year%4)!=0 || (year/100)==0 && year%400!=0){
				    if ((m2+1)<3){
				       sumup=sumup-2+d;
					}
				    sumup=sumup+sumup3;
			    }
			    if (m2==1 || m2==3 || m2==5 || m2==7 || m2==8 || m2==10 || m2==12){
				   sumup=sumup+(31-d2);
			    }
			    else if(m2==2 && (year%4==0 && year/100!=0 || year%400==0)){
				   sumup=sumup+(29-d2);
			    }
			    else if(m2==2 && (year%4!=0 || year/100==0 && year%400!=0)){
				   sumup=sumup+(28-d2);
			    }
			    else{
				   sumup=sumup+(30-d2);
			    }
			}
			else if (month==(m2+1)){
			    if (m2==1 || m2==3 || m2==5 || m2==7 || m2==8 || m2==10 || m2==12){
				   sumup=sumup+(31-d2)+d;
			    }
			    else if(m2==2 && (year%4==0 && year/100!=0 || year%400==0)){
				   sumup=sumup+(29-d2)+d;
			    }
			    else if(m2==2 && (year%4!=0 || year/100==0 && year%400!=0)){
				   sumup=sumup+(28-d2)+d;
			    }
			    else{
				   sumup=sumup+(30-d2)+d;
			    }
			}
			else{
				sumup=sumup+(d-d2);
			}
		}
		return sumup;
	}

	private int dd(int sumup, int day){
		int d=day;
		while (sumup>0){
			sumup=sumup-1;
			d=d-1;
			if (d==-1){
				d=6;
			}
		}
		return d;
	}
}	
