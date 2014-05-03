// The purpose of this class is to provide a template
// for the various optimization algorithms that this 
// program will implement.  If I find that I'm rewriting
// a section of code among all of the various optimization
// classes, I'll move it here so it's available to every class
// I write, allowing me to not write it more than once.

package project.personal;


import java.util.Random;
import java.util.Date;

public abstract class Solver {
	protected int[][] initialState;
	protected int[] numberBank = new int[9];
	protected Sudoku temp;
	protected int errors;
	
	/*
	 
	// This method creates an ordered array of integers
 	// from one through nine...

	protected void populateNumberBank(){
		for(int i = 0; i < 9; i++)
			numberBank[i] = i + 1;
	}
	
	*/
	
	// This function creates a random ordering of integers
	// from one through nine.
	protected void populateNumberBank(){
		Date date = new Date();
		Random generator = new Random(date.getTime());
		int element;
		
		for(int i = 0; i < 9; i++)
			numberBank[i] = 0;
		
		for(int i = 0; i < 9; i++){
			do{
				element = generator.nextInt(9) + 1;
			} while(existsInArray(numberBank, element));
			
			numberBank[i] = element;
		}

	}
	
	protected void setInitialState(Sudoku solution){
		initialState = solution.getInitialState();
	}
	
	protected boolean existsInArray(int[] array, int element){
		for(int i = 0; i < 9; i++){
			if(array[i] == element)
				return true;
		}
		return false;	
	}
	
	protected void initializeState(int[][] solution){
		populateNumberBank();
		// Goal: If the current value of numberBank[] is 
		// nowhere else in the initialState[i] row and if there exists an element in
		// initialState[i] that equals zero, then assign a number from numberBank.
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				for(int k = 0; k < 9; k++)
					switch(solution[i][j]){
						case 0:
							if(!existsInArray(solution[i], numberBank[k])){
								solution[i][j] = numberBank[k];
								//System.out.println("The current value is..." + solution[i][j]);
							}
							//System.out.println("Exiting case 0...");
							break;
						
						default: 
							//System.out.println("Exiting the default case...");
							break;	
					}
	}
	
	/*
	protected void initializeState(int[][] solution){
		populateNumberBank();
		
		// Nested loop to populate solution with 
		// the numbers from numberBank that are
		// not used in solution.
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++){
					
			}
	}
	*/
	public abstract int[][] solve(Sudoku puzzle);
}
