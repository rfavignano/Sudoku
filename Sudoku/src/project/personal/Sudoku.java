// Notes: 
// I'm not entirely sold on the two dimensional
// representation of the Sudoku grid.  Genetic algorithms
// typically use a single dimensional array as a genome,
// and if other stochastic optimization methods use the same
// idea, then it might be beneficial to change this to a
// single dimensional array.  The question is, what is the
// most efficient way to check the correctness of the current
// state?
// 
// The other consideration is the verification method.  
// Genetic algorithms need to know how fit its population is
// in order to increase the fitness of the next population.
// This translates into the verification method needing to
// say how off an answer is.  What's the best way to do this?
//
// The verify function needs to be refactored, as there are
// duplicate algorithms.

package project.personal;


// This class defines a Sudoku puzzle.  It has two representations of
// the puzzle - the initial state, and the current state, which is 
// the user's guess at the puzzle.  The idea of this class is to
// represent the start of the puzzle, the user's answer, and provide
// a way to check if the answer is correct.

public class Sudoku {
	// The initial state of the puzzle.  This is
	// the start of the puzzle.  Zeros represent
	// spaces, otherwise each element in the array 
	// should be numbers between 1 - 9.
	private byte[][] initialState = new byte[9][9];
	
	// The current state of the puzzle.  This is
	// the user's answer.  Zeros represent spaces,
	// otherwise each element in the array should 
	// be numbers between 1 - 9.
	private byte[][] currentState = new byte[9][9];
	
	// Constructor with no arguments.  This creates
	// an empty board.  This is useful in case the 
	// user wants to enter in a completed Sudoku
	// to simply verify that his or her answer is
	// correct.
	public Sudoku(){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				initialState[i][j] = currentState[i][j] = 0;
	}
	
	// Constructor that takes a two dimensional array
	// as an argument.  This constructor takes the array
	// and copies itself into both Sudoku arrays.
	public Sudoku(byte[][] state){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				initialState[i][j] = currentState[i][j] = state[i][j];
	}
	
	// This prints the current state to the console.
	// For debugging purposes.
	public void printCurrentState(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(currentState[i][j]);
			}
			System.out.println("");
		}
	}
	
	// This prints the initial state to the console.
	// For debugging purposes.
	public void printInitialState(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(initialState[i][j]);
			}
			System.out.println("");
		}
	}

	// This returns the current state.
	public byte[][] getCurrentState(){
		return currentState;
	}
	
	// This returns the initial state.
	public byte[][] getInitialState(){
		return initialState;
	}
	
	// This sets the current state to whatever
	// the user wants.  Note that the initial
	// state will not get a function like this
	// so that the program can verify that the
	// squares in the current state that are 
	// filled in on the initial state are not
	// overwritten.
	public byte setCurrentState(byte[][] state){
		
		
		
		return 0;
	}
	
	// This function verifies the correctness of 
	// the current state.  If it's correcet, 
	// it will return a zero.  Otherwise, it will
	// return the amount of incorrect squares
	// in each of the four tests
	public int verify(){
		int incorrect = 0; // Number of incorrect squares.
		int match = 0; // Number of matches in an array
		byte[] temp = new byte[9];
		int tempCounter = 0;
		
		// Verify that there are no blank spots in the
		// sudoku.  Blank spots, in this program, are
		// represented by zeroes.  To do that, we'll
		// simply count the zeroes in the entire sudoku
		// and add them to 'incorrect'.
		for(int col = 0; col < 9; col++){
			for(int row = 0;row < 9; row++){
				if(currentState[col][row] == 0)
					incorrect++;
			}
		}
		
		// Verify that each row only has unique
		// numbers and that each number range
		// from one through nine.  How this is done
		// is that a third for loop cycles through
		// the array and counts up the matches.  If
		// the number of matches is greater than one,
		// than the number of matches minus one is added
		// to the incorrect variable, and the match variable
		// is zeroed out.
		for(int col = 0; col < 89; col++){
			for(int row = 0; row < 9; row++){
				for(int i = 0; i < 9; i++){
					if(currentState[col][row] == currentState[col][i])
						match++;
					if(match > 1){
						incorrect += --match;
						match = 0;
					}
				}
			}
		}
		
		// Verify that each column only has unique
		// numbers and that each number range
		// from one through nine.  How this is done
		// is that a third for loop cycles through
		// the array and counts up the matches.  If
		// the number of matches is greater than one,
		// than the number of matches minus one is added
		// to the incorrect variable, and the match variable
		// is zeroed out.
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++){
				for(int i = 0; i < 9; i++){
					if(currentState[col][row] == currentState[i][row])
						match++;
					if(match > 1){
						incorrect += --match;
						match = 0;
					}
				}
			}
		}
		
		// Verify that each square has only unique
		// numbers and that each number range 
		// from one through nine.
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 3; col++){
				temp[tempCounter] = currentState[col][row];
				tempCounter++;
			}
		}
		
		return incorrect;
	}
}
