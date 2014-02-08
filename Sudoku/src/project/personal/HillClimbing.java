// Notes:
// 1. Transfer sudoku initial state 2D array into an initial state 
// vector in the HillClimbing class.
// 2. Copy initial state vector to current state vector
// 3. If an element of the current state vector is blank in the 
// initial state vector, insert a random number.  Create a set of 
// 100 such solutions.
// 4. Copy each solution into a 2D array and run the Sudoku.verify 
// method, keeping the lowest scoring solution.  This solution will 
// be your starting point.
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
// implement a first choice, random restart variant of the 
// algorithm.  
//
// The first choice portion basically means that it
// will generate a number of starting points and choose the one
// that is the most correct solution.  Note that the class Sudoku
// has a method that gives you a tally of how many errors exist
// inside of a proposed solution.  Our version of the hill climbing
// algorithm will randomly generate a series of starting points and
// use the one with the lowest number of errors.
//
// The random restart portion refers to the algorithm's habit of
// choosing a new set of starting points and starting over again
// if the first path it chose was a dead end.

public class HillClimbing {
	private int[] initialState = new int[81];
	private int[] currentState = new int[81];
	
	// This will take a Sudoku, and transfer its
	// 2D representation into HillClimbing's 1D
	// representation of the solution for ease
	// of computation.
	public void setInitialState(Sudoku solution){
		
	}

	// This will take HillClimbing's 1D representation
	// of the solution it proposes as correct, and 
	// transfers it into Sudoku's 2D representation
	public Sudoku getCurrentState(){
		Sudoku solution = new Sudoku();
		return solution;
	}
	
	
}
