class Exercise1
{

	private int[] leaves;
	private int[] solution;
	private int[] step;

	public Exercise1( int[] leaves )
	{
		this.leaves = leaves;
	}

	public void solveExercise1(){
		solution = new int[leaves.length];
		step = new int[leaves.length];
		if(leaves.length == 0 || leaves == null) step = null;
		if(leaves[0] == 0) step = null;
		
		for (int i=1; i < leaves.length; i++){
			step[i] = Integer.MAX_VALUE;
			for(int j=0; j<i; j++){
				if(j + leaves[j] >= i && step[j] != Integer.MAX_VALUE){
					step[i] = Integer.min(step[i], 1 + step[j]);
					if(leaves[i]+(i-j) > solution[j])
						solution[j] = leaves[i];
				}
			}
		}
	}

	public void printSolution()
	{
		System.out.print(step[step.length-1]+","+"{"+leaves[0]+", ");
		int sumsolution = 0;
		if(solution!=null){
			for( int i=0; i<solution.length; i++ )
			{
				if(sumsolution < leaves.length-1){
					System.out.print(solution[i]+", " );
					sumsolution = sumsolution + solution[i];
				}else{
					break;
				}
			}
		System.out.print(leaves[leaves.length-1]+"}");
		}else{
			System.out.print( " Solution for Exercise1 is empty." );
		}		
		
	}

	/*
	public void printInputData()
	{
		if( leaves !=null )
		{
			for( int i=0; i<leaves.length; i++ )
			{
				System.out.print( leaves[i]+" " );
			}
			System.out.println();
		} 
		else
			System.out.println("Input table is null.");
	}
	*/

}