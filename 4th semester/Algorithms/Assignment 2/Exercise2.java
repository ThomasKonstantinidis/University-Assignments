class Exercise2
{

	private int[][] caloriesAndFat; // 2D array. First row has the calories. Second row has the fat.
	private int wantedCalories; // Total calories.
	private int[] solution; // The selected products.

	public Exercise2( int[][] caloriesAndFat, int wantedCalories )
	{
		this.caloriesAndFat = caloriesAndFat;
		this.wantedCalories = wantedCalories;	
	}

	public void solveExercise2()
	{
		int a = Integer.MAX_VALUE;
		for (int i = 0; i < caloriesAndFat.length; i++) {
			for (int j = i; j < caloriesAndFat.length; j++) {
				int sumF = 0;
				for (int k = i; k <= j; k++) {
					sumF += caloriesAndFat[2][k];
					solution[k]+=caloriesAndFat[1][k];
				}
				if (sumF >= wantedCalories) {
					a = Integer.min(a, (j - i + 1));
					break;
				}
			}
		}
	}

	public void printSolution()
	{
		if(solution!=null)
			for( int i=0; i<solution.length; i++ )
			{
				System.out.print( solution[i]+" " );
			}
		else
			System.out.print( " Solution for Exercise2 is empty." );
				
		System.out.println();
	}

	/*
	public void printInputData()
	{
		if( caloriesAndFat !=null )
		{
			int rows = caloriesAndFat.length;
			int columns = caloriesAndFat[0].length;
			
			for( int i=0; i<rows; i++ )
			{
				for( int j=0; j<columns; j++ )
				{
					System.out.print( caloriesAndFat[i][j]+" " );
				}
				System.out.println();
			}
		} 
		else
			System.out.println("Input table is null.");
		System.out.println( "Total calories: "+wantedCalories );
	}
	*/

}