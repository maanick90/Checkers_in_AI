package com.utd.play;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.utd.DataStructures.Successor;
import com.utd.entity.Board;
import com.utd.entity.Response;

public class Play {

/*	public static void main(String[] args) {
		
		// create a new game
		Board board = new Board();

		sampleMoves(board, player);											// Just test the moves
		doublePlayerGame(board);											// Make the game as 2-Player game

	}
*/
	public static void doublePlayerGame(Board board) throws Exception {
		
		System.out.println("Welcome to playing the game as Double player game:");
		board.displayBoard();
		
		String player = "1";
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			int x1, x2, y1, y2;
			System.out.print("\n\nPlayer " + player + " move:     Enter -1 to exit:");
			System.out.print("\nEnter (x1,y1) and (x2,y2):");
			x1 = scanner.nextInt();
			if(x1 == -1)
			{
				System.out.println("------ GAME ENDED ------");
				break;
			}
			y1 = scanner.nextInt();
			x2 = scanner.nextInt();
			y2 = scanner.nextInt();
			if(moveACoin(board, x1, y1, x2, y2, player, true, new Response()))
			{
				if(player.equals("1"))
					player = "2";
				else
					player = "1";
			}
			else
				System.out.println("Move continues:");
		}
		scanner.close();
	}

	/*
	 * STATIC Function to move a coin from (x1,y1) to (x2,y2)
	 * Validation of the move is done inside
	 */
	public static boolean moveACoin(Board board, int x1, int y1, int x2, int y2, String player, boolean humanMove, Response responseObj) throws Exception {					// given a start and end point, this function makes the move & return TRUE else return FALSE
		
//		System.out.println("\nYou are about to move from " + "(" + x1 +","+ y1 + ") to (" + x2 + "," + y2 +")");
		
		if(validateTheMove(board, x1, y1, x2, y2, player, humanMove, responseObj))					// TRUE if all moves are validated
		{
			board.A[x2][y2] = board.A[x1][y1];								// normal move to the end point
			board.A[x1][y1] = "0";
			
			if(player.equals("2") && x2 == board.boardSize-1)
			{
				board.A[x2][y2] = "22";
//				responseObj.setPawnOrKing(1);
			}
			else if(player.equals("1") && x2 == 0)
			{
				board.A[x2][y2] = "11";
//				responseObj.setPawnOrKing(1);
			}
//			board.displayBoard();
			return true;
		}
		return false;
	}
	
	// given a start and end point, this function will return TRUE if the move is valid
	public static boolean validateTheMove(Board board, int x1, int y1, int x2, int y2, String player, boolean humanPlays, Response responseObj) throws Exception {			
		
		String errorMsg = "";
		if(x1 < 0 || y1 < 0 || x1 >= board.boardSize || y1 >= board.boardSize)
			errorMsg = "START Point is out of boundary!";
		else if(x2 < 0 || y2<0 || x2>=board.boardSize || y2>=board.boardSize)
			errorMsg = "END Point is out of boundary!";
		else if(board.A[x1][y1].equals("0"))
			errorMsg = "Select a coin please!";
		else if(!board.A[x1][y1].contains(player))		
		{
			System.out.println("\nI am here = " + board.A[x1][y1] + ">>>" + player);
			errorMsg = "Select YOUR coin to move!";
		}
		else if(!board.A[x2][y2].equals("0"))
			errorMsg = "Coin exists in the END Point!";
		else if(board.A[x1][y1].equals("1") && x2>x1 || board.A[x1][y1].equals("2") && x2<x1)
			errorMsg = "Only forward moves are allowed!";
		else if(validateCrossOverCoins(board, x1, y1, x2, y2, player, humanPlays))
			errorMsg = "Choose a PROPER move!";
		else
			return true;											// return true if all moves are validated

		if(humanPlays)
		{
			System.out.print("\n" + errorMsg);
			responseObj.setResponseString(errorMsg);
		}

		return false;									
	}

	public static boolean validateCrossOverCoins(Board board, int x1, int y1, int x2, int y2, String player, boolean humanPlays) throws Exception {		// returns true if the move is invalid

		String a[][] = board.A;

		if(Math.abs(x1-x2)!=1 || Math.abs(y1-y2)!=1)
		{
			if(Math.abs(x1-x2)!=2 || Math.abs(y1-y2)!=2)									// check if it is a (2,2) step move
				return true;
			else if(neighborCoins(board, x1, y1, x2, y2, a[x1][y1], humanPlays))
				return true;
			else 
			{
				return false;
			}
		}
		else 
			return false;							//its a (1,1) step move
	}

	public static boolean neighborCoins(Board board, int x1, int y1, int x2, int y2, String player, boolean humanPlays) throws Exception {				// returns true if the move is invalid

		String a[][] = board.A;
		int flag = 0;
		if( (x2-x1 == 2) && (y2-y1==2) )											// right down diagonal
		{
			flag = 1;
			if((a[x1+1][y1+1].contains(player) || a[x1+1][y1+1].equals("0")))
				return true;
		}
		else if( (x2-x1==2) && (y1-y2==2))
		{
			flag = 2;
			if((a[x1+1][y1-1].contains(player) || a[x1+1][y1-1].equals("0")) )		//left down diagonal
				return true;
		}
		else if( (x1-x2==2) && (y2-y1==2))
		{
			flag = 3;
			if((a[x1-1][y1+1].contains(player) || a[x1-1][y1+1].contains("0")) )	// right up diagonal
				return true;
		}
		else if( (x1-x2==2) && (y1-y2==2) )
		{
			flag = 4;
			if((a[x1-1][y1-1].contains(player) || a[x1-1][y1-1].equals("0")) )		// left up diagonal
				return true;
		}
		
		if(humanPlays)
		{
			if(flag == 1)
				a[x1+1][y1+1] = "0";
			else if(flag == 2)
				a[x1+1][y1-1] = "0";
			else if(flag == 3)
				a[x1-1][y1+1] = "0";
			else if(flag == 4)
				a[x1-1][y1-1] = "0";
			else
				System.out.println("Inside NeighborCoins >>> This should not be printed!!!");
		}
		
		return false;
	}
}
