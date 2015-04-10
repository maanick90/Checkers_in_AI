package com.utd.TestPackage;

import java.util.List;
import java.util.Map;

import com.utd.DataStructures.Node;
import com.utd.DataStructures.Successor;
import com.utd.entity.Board;
import com.utd.entity.Response;
import com.utd.play.Play;

public class TestKingBackwardMove {

	public static void main(String[] args) throws Exception {
		
		
		String str = "0  0  0  1  0  1  0  1 "   +  
				 	 " 0  0  0  0  1  0  1  0 "  +
					 " 0  0  0  0  0  1  0  1 "  +
					 " 0  0  0  1  0  0  0  1 "  +
					 " 0  0  0  0  1  0  1  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  2 "  +
					 " 0  0  0  0  0  0  1  0 " ;
		String strSplit[] = str.split("  ");
		Board board = new Board();
		board.setValuesTo(strSplit);
		
		board.displayBoard();
		
		if(board.isGameEnds("1"))
		{
			System.out.println("Computer Wins");
		}
		else if(board.isGameEnds("2"))
			System.out.println("Human wins");

	}

}
