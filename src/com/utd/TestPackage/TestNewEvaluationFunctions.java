package com.utd.TestPackage;

import com.utd.entity.Board;

public class TestNewEvaluationFunctions {
	
	public static void main(String[] args) throws Exception {
		
		String str =  "0  0  0  2  0  2  0  2 "   +  
				 	 " 0  1  0  0  2  0  2  0 "  +
					 " 0  0  0  0  0  2  0  2 "  +
					 " 0  0  0  2  0  0  0  2 "  +
					 " 0  0  0  0  2  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  2  0  2 "  +
					 " 0  0  0  0  0  0  0  0 " ;
		String strSplit[] = str.split("  ");
		Board parentBoard = new Board();
		parentBoard.setValuesTo(strSplit);
		parentBoard.displayBoard();
		
		String str1 = "11  0  0  2  0  2  0  2 "   +  
 					 " 0  0  0  0  2  0  2  0 "  +
					 " 0  0  0  0  0  2  0  2 "  +
					 " 0  0  0  2  0  0  0  2 "  +
					 " 0  0  0  0  2  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  22  0  22  0 " ;
		String strSplit1[] = str1.split("  ");
		Board board = new Board();
		board.setValuesTo(strSplit1);
		board.displayBoard();
		
		
		
		System.out.println("\n" + board.calculateHeuristicFunction(parentBoard) + " >> ");
		
	}

}
