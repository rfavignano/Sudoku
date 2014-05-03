// The hill climbing algorithm can be found in the book Artificial
// Intelligence: A Modern Approach on page 122, as well as: 
// http://en.wikipedia.org/wiki/Hill_climbing_algorithm

package project.personal;

import java.util.Date;
import java.util.Random;

// This class implements the hill climbing algorithm to solve
// Sudokus.  Hill climbing is an algorithm of the local search
// optimization family whose purpose is to, more or less, march
// up a hill (or down into a valley) to find the best possible 
// solution.  If the landscape has lots of smaller hills, which
// represent good solutions (as opposed to the best solution), 
// this algorithm may get stuck.  Therefore, I have chosen to
// implement a steepest ascent, random restart variant of the 
// algorithm.  
//
// The steepest ascent portion refers to the algorithm analyzing
// all of its potential neighbors and choosing the one that moves
// the current state closer to a maximum (or minimum, as in the case
// of this algorithm).
//
// The random restart portion refers to the algorithm's habit of
// choosing a new set of starting points and starting over again
// if the first path it chose was a dead end (i.e. did not solve
// the sudoku in this program).

public class HillClimbing extends Solver {
	private int[][] currentState = new int[9][9];
	private int[][] neighborState = new int[9][9];
	private int[][] solutionState = new int[9][9];
	
	// This variable counts the number of times climb was called
	private int iteration = 0; 
	
	// I want to make this private to prevent people from instantiating
	// this class.  
	// Note...This isn't the intended way I wanted to use the class...
	public HillClimbing(){
		
	}
		
	// This method takes a sudoku puzzle as input and outputs
	// the puzzle's solution.  This method should be the only 
	// method that can be called  by a program using this class.  
	// It will take care of everything.
	public int[][] solve(Sudoku puzzle){
		
		// Make a copy of puzzle to work with.
		temp = new Sudoku(puzzle.getInitialState());
		
		// Copy the initial state of the puzzle to the Hill
		// climbing class.
		setInitialState(temp);
		
		// Copy initial state into current state.
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				currentState[i][j] = initialState[i][j];
		
		// Fill in the blanks with a random set of numbers.
		initializeState(currentState);
		
		// This sets the stage for the recursion.  Iteration
		// is used to limit the number of times climb is called
		// to prevent an infinite loop.
		iteration = 0;
		
		// Since climb and solve both depend on this 
		// to be either zero or a positive integer, 
		// I'll initially set this to negative one
		// to not screw things up with the algorithm
		// Then again, why not just call climb anyway?
		errors = -1;
		
		// Copy current state into neighbor state.
		// This is duplicate code, and a prime candidate
		// to be made into a method and placed in the
		// Solver class for other algorithms to use...
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				neighborState[i][j] = currentState[i][j];
		
		
		// Why do I have this here?  If I'm going to 
		// go into climb anyway, why not just shorten 
		// this section to just "return climb(currentState);"?
		if(errors == 0)
			return solutionState;
		else
			return climb(currentState);
	}
	
	private int[][] climb(int[][] neighborState){
		Date date = new Date();
		Random generator = new Random(date.getTime());
		int temporaryValue, randomRowIndex;
		int[] colIndex = new int[2];
		int neighborErrorCount;
		int currentErrorCount;
		
		if(iteration < 200){
			// Picks a random row in initialState, and if two random elements 
			// that belong in that row both equal zero, swap those values in 
			// the neighborState.
			randomRowIndex = generator.nextInt(9);

			do{
				for(int i = 0; i < 2; i++)
					colIndex[i] = initialState[randomRowIndex][generator.nextInt(9)];
			} while(initialState[randomRowIndex][colIndex[0]] != 0
					&& initialState[randomRowIndex][colIndex[1]] != 0);

			temporaryValue = neighborState[randomRowIndex][colIndex[0]];
			neighborState[randomRowIndex][colIndex[0]] = 
					neighborState[randomRowIndex][colIndex[1]];
			neighborState[randomRowIndex][colIndex[1]] = temporaryValue;
			
			// Get error count for neighborState
			temp.setCurrentState(neighborState);
			neighborErrorCount = temp.verify();
			
			// Get error count for currentState
			temp.setCurrentState(currentState);
			currentErrorCount = temp.verify();
			
			// Increment iteration to keep track
			// of how many times climb was called.
			// If it was called 200 times, then
			// we need to restart the whole process.
			iteration++;
			
			if(neighborErrorCount == 0)
				return neighborState;
			else if(neighborErrorCount >= currentErrorCount)
				return climb(currentState);
			else
				return climb(neighborState);
		}
		else return solve(temp);
	}
}
