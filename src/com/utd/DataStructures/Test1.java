package com.utd.DataStructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import com.utd.entity.Board;
import com.utd.entity.Response;
import com.utd.play.Play;

public class Test1 {

	public static void main(String[] args) {
		
		System.out.println("Welcome to CHECKERS GAME!!!!");
		
		String str = "0  2  0  2  0  2  0  2 "   +  
					 " 2  0  2  0  2  0  2  0 "  +
					 " 0  0  0  0  0  2  0  2 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  2  0  2  0 "  +
					 " 0  0  0  0  0  1  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 " ;
		String strSplit[] = str.split("  ");
		
		try {
			
//			PlayGame();
			
/*			Tree tree = new Tree(new Board());						// maintain a SINGLE TREE throughout the game
			Node root = tree.treeRoot;								// get the 'root' of the tree for further computing
			root.state.setValuesTo(strSplit);				// set a custom Board here
			Queue1 queue = new Queue1();							// maintain a SINGLE QUEUE throughout the game
			queue.addNodesToQueue(root);
			
//			playTheGame();

			
			 * Generate the successors to a certain depth
			 
			int depth = 1;
			String startPlayer = "2";
			root.generateSuccessorsToADepth(queue, depth, startPlayer);
			System.out.print("\n-------------------");
			System.out.print("\nQueue Size = " + queue.queueHead.size());
			System.out.print("\n-------------------");
			
			
			 * Computer chooses the best move now
			 
//			testFunctions(queue, root);
//			testFunctions1(queue, root);
			root.chooseTheBestMove(startPlayer, queue);
			
			
			 * Simply print the queue size
			 
*/			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	public static void PlayGame(Board board, int x1, int y1, int x2, int y2, Response responseObj, HttpServletResponse response) throws Exception
	{
		humanPlaysGame(board, x1, y1, x2, y2, responseObj);
		if(responseObj.getResponseString() == "0")			// if Human move is validated, then make the computer move
		{
				computerPlaysGame(board, responseObj, response);
		}
	}
	
	public static void computerPlaysGame(Board board, Response responseObj, HttpServletResponse response) throws Exception {
		
		Tree tree = new Tree(board);						// maintain a SINGLE TREE throughout the game
		Node root = tree.treeRoot;								// get the 'root' of the tree for further computing
		Queue1 queue = new Queue1();							// maintain a SINGLE QUEUE throughout the game
		queue.addNodesToQueue(root);
		
		/*
		 * Generate the successors to a certain depth
		 */
		int depth = 3;
		String startPlayer = "2";
		root.generateSuccessorsToADepth(queue, depth, startPlayer);
		
		/*
		 * Computer chooses the best move now
		 */
		root.chooseTheBestMove(startPlayer, depth, queue, responseObj, response);
	}

	public static void humanPlaysGame(Board board, int x1, int y1, int x2, int y2, Response responseObj) throws Exception
	{
		System.out.print("\n\n--------------- HUMAN MOVE -------------------");
		if(Play.moveACoin(board, x1, y1, x2, y2, "1", true, responseObj))
		{
			System.out.println("\nHuman moves from ("+x1+","+y1+") to ("+x2+","+y2+") ");
			board.displayBoard();
		}
//		return board;
	}
}
