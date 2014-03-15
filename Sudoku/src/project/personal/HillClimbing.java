// Notes:
// 1. Transfer sudoku initial state 2D array into an initial state 
// vector in the HillClimbing class. 
// Update: I think I'm going to try using the 2D array as is for ease
// of programming.
// 2. Copy initial state vector to current state vector
// 3. If an element of the current state vector is blank in the 
// initial state vector, insert a random number.  Create a set of 
// 100 such solutions.
// Update: I've been doing some reading, and I think I will implement an
// integer array with the numbers 1 - 9 in each element, and 
// randomly pick one for to go into each row so no two numbers
// are chosen twice to speed up calculations.  
// 4. Copy each solution into a 2D array and run the Sudoku.verify 
// method, keeping the lowest scoring solution.  This solution will 
// be your starting point.
// Update: Since they're already in 2D arrays, we simply need to run
// the Sudoku.verify function after transferring the answers
// to a variable of type Sudoku to verify the answer. 
// 5. Copy your starting point back into the current state vector.
// 6. For each element in the array that is zero in the initial vector, 
// add or subtract one from it (as long as it doesn't go above 9 or below 
// 1) and run the verify algorithm on the solution.  If it's better than 
// what you intiially had, copy it into a temporary vector.  As the loop 
// progresses through the current state vector, keep a vector holding the 
// lowest scoring solution.  Once you find it, copy that into the current 
// state and start again.
// 7. If the method in #6 works through the array and finds that every 
// proposed solution is worse than what you currently have, go back to #3 
// and start over.
// 
// The hill climbing algorithm can be found in the book Artificial
// Intelligence: A Modern Approach on page 122, as well as: 
// http://en.wikipedia.org/wiki/Hill_climbing_algorithm

package project.personal;

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
// the current state closer to the solution.
//
// The random restart portion refers to the algorithm's habit of
// choosing a new set of starting points and starting over again
// if the first path it chose was a dead end.

public class HillClimbing extends Solver {
	private int[][] currentState = new int[9][9];
	private int[][] neighborState = new int[9][9];
	
	// I made this private to prevent people from instantiating
	// this class.  
	private HillClimbing(){
		
	}
		
	// This class will solve a sudoku that is fed into it.
	// This class should be the only class that can be called
	// by a program.  It will take care of everything.
	public int[][] solve(Sudoku puzzle){
		
		// Copy initial state into current state.
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				currentState[i][j] = initialState[i][j];
		
		
		
		return currentState;
	}
	
	
}
