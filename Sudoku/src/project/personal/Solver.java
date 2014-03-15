// The purpose of this class is to provide a template
// for the various optimization algorithms that this 
// program will implement.  If I find that I'm rewriting
// a section of code among all of the various optimization
// classes, I'll move it here so it's available to every class
// I write, allowing me to not write it more than once.

package project.personal;

public abstract class Solver {
	protected int[][] initialState;
	protected int[] numberBank = new int[9];
	
	protected void populateNumberBank(){
		for(int i = 0; i < 9; i++)
			numberBank[i] = i + 1;
	}
	
	protected void setInitialState(Sudoku solution){
		initialState = solution.getInitialState();
	}
	
	protected void initializeState(int[][] solution){
		populateNumberBank();
		
		// Nested loop to populate solution with 
		// the numbers from numberBank that are
		// not used in solution.
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++){
					
			}
	}

	public abstract int[][] solve(Sudoku puzzle);
}
