// Notes: 
// I'm not entirely sold on the two dimensional
// representation of the Sudoku grid.  Genetic algorithms
// typically use a single dimensional array as a genome,
// and if other stochastic optimization methods use the same
// idea, then it might be beneficial to change this to a
// single dimensional array.  The question is, what is the
// most efficient way to check the correctness of the current
// state, and is it at odds with this paradigm?
// 
// The other consideration is the verification method.  
// Genetic algorithms need to know how fit its population is
// in order to increase the fitness of the next population.
// This translates into the verification method needing to
// say how "off the mark" an answer is.  What's the best way to do this?
//
// The verify function needs to be refactored, as there is duplicate code.
// I'm also not proud of the code to count the duplicates in the various
// squares.  While functional, I feel that this solution is inelegant.
// It also uses more memory than I would like.


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
	// Note: Needs error checking to verify that state 
	// array is a 9x9 array.
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
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				currentState[i][j] = state[i][j];

		return 0;
	}
	
	// This function verifies the correctness of 
	// the current state.  If the current state is
	// a valid answer, verify() will return a zero.  
	// Otherwise, it will return the amount of incorrect squares
	// in each of the five tests: comparing currentState against
	// initial state, verifying that currentState has no blanks,
	// verifying that each row in currentState has a unique number
	// ranging from one through nine, verifying that each column
	// in currentState has a unique number ranging from one through 
	// nine, and finally, verifying that each square in currentState 
	// has a unique number ranging from one through nine.
	public int verify(){
		int incorrect = 0; // Number of incorrect squares.
		int match = 0; // Number of matches in an array
		
		// Verify that the nonzero values in initialState
		// are the same numbers as in currentState.
		for(int col = 0; col < 9; col++){
			for(int row = 0; row < 9; row++){
				if(initialState[row][col] != 0)
					if(initialState[row][col] != currentState[row][col])
						incorrect++;
			}
		}
		
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
		// numbers and that each number ranges
		// from one through nine.  How this is done
		// is that a third for loop cycles through
		// the array and counts up the matches.  If
		// the number of matches is greater than one,
		// then the number of matches minus one is added
		// to the incorrect variable, and the match variable
		// is zeroed out.
		for(int col = 0; col < 89; col++){
			for(int row = 0; row < 9; row++){
				for(int i = 0; i < 9; i++){
					if(currentState[col][row] == currentState[col][i])
						match++;
				}
				
				if(match > 1){
					incorrect += --match;
					match = 0;
				} else{
					match = 0;
				}
			}
		}
		
		// Verify that each column only has unique
		// numbers and that each number range
		// from one through nine.  How this is done
		// is that a third for loop cycles through
		// the array and counts up the matches.  If
		// the number of matches is greater than one,
		// then the number of matches minus one is added
		// to the incorrect variable, and the match variable
		// is zeroed out.
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++){
				for(int i = 0; i < 9; i++){
					if(currentState[col][row] == currentState[i][row])
						match++;
				}
				
				if(match > 1){
					incorrect += --match;
					match = 0;
				} else{
					match = 0;
				}
			}
		}
		
		// Verify that each square has only unique
		// numbers and that each number range 
		// from one through nine.
		// First, I made nine temporary 3x3 arrays,
		// then I basically put each square of the
		// sudoku into each array, then I checked
		// the validity of each square, incrementing
		// the incorrect variable by one for each 
		// duplicate in each square.
		
		// Arrays to store each box of the sudoku
		// for verification purposes.
		byte[][] tempOne = new byte[3][3];
		byte[][] tempTwo = new byte[3][3];
		byte[][] tempThree = new byte[3][3];
		byte[][] tempFour = new byte[3][3];
		byte[][] tempFive = new byte[3][3];
		byte[][] tempSix = new byte[3][3];
		byte[][] tempSeven = new byte[3][3];
		byte[][] tempEight = new byte[3][3];
		byte[][] tempNine = new byte[3][3];
		
		// Split up sudoku into nine separate arrays,
		// one array for each square.
		for(int row = 0; row < 9; row++){
			switch(row){
				case 0: case 1: case 2:
					for(int col = 0; col < 3; col++){
						tempOne[row][col] = currentState[row][col];	
					}
					
					for(int col = 3; col < 6; col++){
						tempTwo[row][col] = currentState[row][col];	
					}
					
					for(int col = 6; col < 9; col++){
						tempThree[row][col] = currentState[row][col];	
					}
					break;
				case 3: case 4: case 5:
					for(int col = 0; col < 3; col++){
						tempFour[row][col] = currentState[row][col];	
					}
					
					for(int col = 3; col < 6; col++){
						tempFive[row][col] = currentState[row][col];	
					}
					
					for(int col = 6; col < 9; col++){
						tempSix[row][col] = currentState[row][col];	
					}
					break;
				case 6: case 7: case 8:
					for(int col = 0; col < 3; col++){
						tempSeven[row][col] = currentState[row][col];	
					}
					
					for(int col = 3; col < 6; col++){
						tempEight[row][col] = currentState[row][col];	
					}
					
					for(int col = 6; col < 9; col++){
						tempNine[row][col] = currentState[row][col];	
					}
					break;
				default:
					System.out.println("You shouldn't be here...");
					break;
			}
		}
		
		// Verifies that each square has a unique integer from
		// one through nine.
		int matchOne = 0;
		int matchTwo = 0;
		int matchThree = 0;
		int matchFour = 0;
		int matchFive = 0;
		int matchSix = 0;
		int matchSeven = 0;
		int matchEight = 0;
		int matchNine = 0;
		
		// The first two for loops fix a number to be compared.
		for(int row = 0; row < 3; row++){
			for(int col = 0; col < 3; col++){
				
				// These two for loops cycles through the square,
				// counting the number of matches along the way.
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						if(tempOne[row][col] == tempOne[i][j])
							matchOne++;
						if(tempTwo[row][col] == tempTwo[i][j])
							matchTwo++;
						if(tempThree[row][col] == tempThree[i][j])
							matchThree++;
						if(tempFour[row][col] == tempFour[i][j])
							matchFour++;
						if(tempFive[row][col] == tempFive[i][j])
							matchFive++;
						if(tempSix[row][col] == tempSix[i][j])
							matchSix++;
						if(tempSeven[row][col] == tempSeven[i][j])
							matchSeven++;
						if(tempEight[row][col] == tempEight[i][j])
							matchEight++;
						if(tempNine[row][col] == tempNine[i][j])
							matchNine++;
					}
				}
				
				// If there is more than one match, the match variable
				// is decremented by one (so the original number isn't 
				// counted) and added to the number of incorrect
				// squares in the sudoku.
				if(matchOne > 1){
					incorrect += --matchOne;
					matchOne = 0;
				}else{
					matchOne = 0;
				}
				if(matchTwo > 1){
					incorrect += --matchTwo;
					matchTwo = 0;
				}else{
					matchTwo = 0;
				}
				if(matchThree > 1){
					incorrect += --matchThree;
					matchThree = 0;
				}else{
					matchThree = 0;
				}
				if(matchFour > 1){
					incorrect += --matchFour;
					matchFour = 0;
				}else{
					matchFour = 0;
				}
				if(matchFive > 1){
					incorrect += --matchFive;
					matchFive = 0;
				}else{
					matchFive = 0;
				}
				if(matchSix > 1){
					incorrect += --matchSix;
					matchSix = 0;
				}else{
					matchSix = 0;
				}
				if(matchSeven > 1){
					incorrect += --matchSeven;
					matchSeven = 0;
				}else{
					matchSeven = 0;
				}
				if(matchEight > 1){
					incorrect += --matchEight;
					matchEight = 0;
				}else{
					matchEight = 0;
				}
				if(matchNine > 1){
					incorrect += --matchNine;
					matchNine = 0;
				}else{
					matchNine = 0;
				}
			}
		}
		
		// Returns the number of incorrect numbers in the array.
		return incorrect;
	}
}
