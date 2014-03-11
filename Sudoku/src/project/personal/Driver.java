// This is the test driver for the Sudoku class.
// I may use this to test drive other classes as they're written.

package project.personal;

public class Driver {
	
	public static void printSudoku(int[][] puzzle){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(puzzle[i][j]);
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		
		// This is an unsolved sudoku I pulled
		// off the internet...
		int[][] unsolvedPuzzle = 
			{{2, 0, 0, 0, 0, 0, 5, 3, 0},
			{0, 0, 1, 0, 0, 0, 0, 6, 0},
			{3, 9, 8, 0, 2, 6, 4, 0, 0},
			{6, 0, 9, 8, 5, 0, 0, 0, 0},
			{5, 0, 0, 6, 0, 7, 0, 0, 1},
			{0, 0, 0, 0, 9, 2, 6, 0, 5},
			{0, 0, 4, 3, 7, 0, 1, 8, 6},
			{0, 3, 0, 0, 0, 0, 9, 0, 0},
			{0, 8, 5, 0, 0, 0, 0, 0, 2}};
		
		// ...and here is the same sudoku,
		// solved.
		int[][] solvedPuzzle = 
			{{2, 6, 7, 9, 4, 1, 5, 3, 8},
			{4, 5, 1, 7, 8, 3, 2, 6, 9},
			{3, 9, 8, 5, 2, 6, 4, 1, 7},
			{6, 1, 9, 8, 5, 4, 7, 2, 3},
			{5, 4, 2, 6, 3, 7, 8, 9, 1},
			{8, 7, 3, 1, 9, 2, 6, 4, 5},
			{9, 2, 4, 3, 7, 5, 1, 8, 6},
			{7, 3, 6, 2, 1, 8, 9, 5, 4},
			{1, 8, 5, 4, 6, 9, 3, 7, 2}};
		
		// This is an uninitialized puzzle
		// to use as a temporary variable.
		int[][] tempPuzzle;				
				
		// The following creates three instances
		// of the object Sudoku for each situation
		// the class will find itself dealing with:
		// a completely blank sudoku, an unsolved 
		// sudoku, and a solved sudoku.
		Sudoku blankSudoku = new Sudoku();
		Sudoku unsolvedSudoku = new Sudoku(unsolvedPuzzle);
		Sudoku solvedSudoku = new Sudoku(solvedPuzzle);
		
		// Testing printCurrentState...
		System.out.println("Testing printCurrentState...");
		blankSudoku.printCurrentState();
		System.out.println();
		unsolvedSudoku.printCurrentState();
		System.out.println();
		solvedSudoku.printCurrentState();
		System.out.println();
		System.out.println("-------");
		
		// Testing printInitialState...
		System.out.println("Testing printInitialState...");
		blankSudoku.printInitialState();
		System.out.println();
		unsolvedSudoku.printInitialState();
		System.out.println();
		solvedSudoku.printInitialState();
		System.out.println();
		System.out.println("-------");
		
		// Testing getCurrentState...
		System.out.println("Testing getCurrentState...");
		tempPuzzle = blankSudoku.getCurrentState();
		printSudoku(tempPuzzle);
		System.out.println();
		tempPuzzle = unsolvedSudoku.getCurrentState();
		printSudoku(tempPuzzle);
		System.out.println();
		tempPuzzle = solvedSudoku.getCurrentState();
		printSudoku(tempPuzzle);
		System.out.println();
		System.out.println("-------");
		
		// Testing getInitialState...
		System.out.println("Testing getInitialState...");
		tempPuzzle = blankSudoku.getInitialState();
		tempPuzzle[1][1] = 6;
		printSudoku(tempPuzzle);
		System.out.println();
		tempPuzzle = unsolvedSudoku.getInitialState();
		printSudoku(tempPuzzle);
		System.out.println();
		tempPuzzle = solvedSudoku.getInitialState();
		printSudoku(tempPuzzle);
		System.out.println();
		System.out.println("-------");
		
		// Testing setCurrentState...
		System.out.println("Testing setCurrentState...");
		System.out.println("Original unsolvedSudoku.currentState...");
		unsolvedSudoku.printCurrentState();
		tempPuzzle = unsolvedSudoku.getCurrentState();
		for(int i = 0; i < 9; i++)
			tempPuzzle[i][0] = 9;
		unsolvedSudoku.setCurrentState(tempPuzzle);
		System.out.println();
		System.out.println("Modified unsolvedSudoku.currentState...");
		unsolvedSudoku.printCurrentState();
		System.out.println();
		System.out.println("-------");
		
		// Testing verify...
		System.out.println("Number of errors in blank sudoku: " + blankSudoku.verify());
		System.out.println();
		System.out.println("Number of errors in unsolved sudoku: " + unsolvedSudoku.verify());
		System.out.println();
		System.out.println("Number of errors in solved sudoku: " + solvedSudoku.verify());
		System.out.println();
	}

}
