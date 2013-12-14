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
// Lastly, should I change the name of this class to Sudoku?
// it would make more sense, especially if every class I make is
// in the projects.personal package...

package project.personal;


// This class defines a Sudoku puzzle.  It has two representations of
// the puzzle - the initial state, and the current state, which is 
// the user's guess at the puzzle.  The idea of this class is to
// represent the start of the puzzle, the user's answer, and provide
// a way to check if the answer is correct.

public class Puzzle {
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
	public Puzzle(){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				initialState[i][j] = currentState[i][j] = 0;
	}
	
	// Constructor that takes a two dimensional array
	// as an argument.  This constructor takes the array
	// and copies itself into both Sudoku arrays.
	public Puzzle(byte[][] state){
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
}
