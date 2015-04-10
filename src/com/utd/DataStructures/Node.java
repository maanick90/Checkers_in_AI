package com.utd.DataStructures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import com.utd.entity.Board;
import com.utd.entity.Response;
import com.utd.play.Play;

public class Node {
	
	public Board state;
	public Node parentLink;
	public Map<String, Node> childLink;
	
	/*
	 * Constructors defined here
	 */
	public Node() { 
	}
	
	public Node(Board state, Node parentLink, Map<String, Node> childLink) {
		this.state = state;
		this.parentLink = parentLink;
		this.childLink = childLink;
	}
	
	/*
	 * Display Methods here
	 */
	public void displayAllChildren() throws Exception {
		
		System.out.print("\n\nThe Parent is ");
		this.state.displayBoard();
		
		System.out.print("\n\nThe List of children are ");
		if(this.childLink == null)
			System.out.println("NONE");
		else
		{
			for(Map.Entry<String, Node> mapEntry : this.childLink.entrySet())
			{
				String tempStr[] = mapEntry.getKey().split(" ");
				System.out.print("\nMove from (" + tempStr[0]+","+tempStr[1]+") to ("+tempStr[2]+","+tempStr[3]+")");
				mapEntry.getValue().state.displayBoard();
			}
			System.out.print("\nTOTAL NO OF CHILDREN = " + this.childLink.size());
		}
	}
	
	public void displayChildToParent() throws Exception
	{
		if(this == null)
			return;
		System.out.print("\nChild to parent....");
		this.state.displayBoard();
		if(this.parentLink != null)
			this.parentLink.displayChildToParent();
	}
	
	@Override
	public String toString() { 
		try {
			this.state.displayBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/*
	 * Children related methods defined here
	 */
	public void addChildren(Node child, Queue1 queue, String startMove, String endMove) throws Exception // add to Queue and add the child node 
	{
		if(queue.addNodesToQueue(child))					// TRUE when the child is added to the queue. Then add as a children also
		{
			if(this.childLink == null)
			{
				this.childLink = new LinkedHashMap<String, Node>();
			}
			childLink.put(startMove+" "+endMove, child);
			child.parentLink = this;				// set the parent of child to the current parent
		}
		return;
	}

	public void generateYourOwnChildren(Queue1 queue, String player) throws Exception	// given a node, it automatically expands its childLink thereby creating all the children nodes 
	{
		if(state.isGameEnds(player))
		{
			System.out.println("Game is ended");
			return;
		}
		List<String> listMovesWithIndices = Successor.listOfMovesWithStartAndEndIndex(this, player);				// This list contains the list of possible moves for the board 'root.state' in the template " x1 y1 x2 y2 "
//		System.out.println("\n\nList is " + listMovesWithIndices);
		if(listMovesWithIndices != null)
		{
			for(String str: listMovesWithIndices)
			{
				Board childBoard2 = this.state.generateChildrenBoard(str, player);
				Node childNode2 = new Node(childBoard2, this, null);
				Integer moves[] = Board.StringSplitAndToInteger(str);
				this.addChildren(childNode2, queue, moves[0] + " " + moves[1],moves[2] + " " + moves[3]);
			}
		}
		
//		System.out.println("\n\nDisplaying the board: ");
//		this.displayAllChildren();
	}

	/*
	 * You can specify the depth to which the tree has to be expanded
	 */
	public void generateSuccessorsToADepth(Queue1 queue, int count, String player) throws Exception 
	{
		state.displayBoard();
		if(count == 0)
			return;
		else
		{
			this.generateYourOwnChildren(queue, player);
			if(player.equals("2"))
				player = "1";
			else if(player.equals("1"))
				player = "2";
			else 
				System.out.println("Node.generateSuccessorsToADepth and THIS SHOULD NOT BE PRINTED!");
			
			if(this.childLink != null)
			{
				for(Map.Entry<String, Node> childNode : this.childLink.entrySet())
				{
						childNode.getValue().generateSuccessorsToADepth(queue, count-1, player);
				}
			}
		}	
	}

	/*
	 * Let the computer chooses the best move here
	 */
	public void chooseTheBestMove(String player, int depth, Queue1 queue, Response responseObj, HttpServletResponse response) throws Exception {

							String currentPlayer = player;
							String opponentPlayer = null;
							if(currentPlayer.equals("2"))
								opponentPlayer = "1";
							else if(currentPlayer.equals("1"))
								opponentPlayer = "2";

		PairForNextMove nextMove = new PairForNextMove();
		this.DFSHeuristicCalculation(depth, nextMove);
//		System.out.println("\nBest HN Value = " + nextMove.hnBestValue);

		String splitNextBestMove[] = nextMove.nextBestMove.split(" ");
		System.out.println("\n\nComputer moves from (" + splitNextBestMove[0] + "," + splitNextBestMove[1] +") to (" + splitNextBestMove[2] + "," + splitNextBestMove[3] + ")...");
//		this.state.displayBoard();
		if(!Play.moveACoin(this.state, Integer.parseInt(splitNextBestMove[0]), Integer.parseInt(splitNextBestMove[1]), Integer.parseInt(splitNextBestMove[2]), Integer.parseInt(splitNextBestMove[3]), currentPlayer, true, new Response()))
			System.out.println("Node.chooseTheBestMove() and THIS MUST MUST NOT BE PRINTED!");
		else
		{
			if(this.state.isGameEnds("1"))
				response.getWriter().print("0;2;"+splitNextBestMove[0]+" " + splitNextBestMove[1]+ ";" + splitNextBestMove[2]+" " + splitNextBestMove[3] +";0;Computer wins the game");
			else
				response.getWriter().print("0;2;"+splitNextBestMove[0]+" " + splitNextBestMove[1]+ ";" + splitNextBestMove[2]+" " + splitNextBestMove[3] +";0;Game Not Ended");
		}
		this.state.displayBoard();
		if(Successor.listOfMovesWithStartAndEndIndex(this, currentPlayer) == null)
			System.out.println("\n\n\n-------GAME ENDED------ Player "+opponentPlayer+" WINS");
		else if(Successor.listOfMovesWithStartAndEndIndex(this, opponentPlayer) == null)
			System.out.println("\n\n\n-------GAME ENDED------ Player "+currentPlayer+" WINS");
		
//		Queue1.printQueue(queue.queueHead);
		queue.queueHead.clear();
	}

	private void DFSHeuristicCalculation(int depth, PairForNextMove nextMove) throws Exception {
		
		if(this == null)
			return;
		else if(this.childLink == null)
		{
			nextMove.hnBestValue = this.state.calculateHeuristicFunction(this.parentLink.state);
			nextMove.nextBestMove = "";
			return;
		}
		else
		{
			int levelBestValue = 0;
			if(this.childLink != null)
			{
				String levelBestMove = "";
				if(depth%2 == 1)
					levelBestValue = Integer.MIN_VALUE;
				else if(depth%2 == 0)
					levelBestValue = Integer.MAX_VALUE;
				
				for(Map.Entry<String, Node> childNode : this.childLink.entrySet())
				{
					childNode.getValue().DFSHeuristicCalculation(depth+1, nextMove);
					int newVal = nextMove.hnBestValue;
					if(depth%2 == 1 && newVal >= levelBestValue)			// try to MAXIMIZE the heuristic value
					{
						levelBestValue = newVal;
						levelBestMove = childNode.getKey();
					}
					else if(depth%2 == 0 && newVal <= levelBestValue)		// try to MINIMIZE the heuristic value
					{
						levelBestValue = newVal;
						levelBestMove = childNode.getKey();
					}
				}

				nextMove.hnBestValue = levelBestValue;						// once all the child is traversed, 'val' contains the best value now and best move now
				if(levelBestMove == null)
					System.out.println("Node.DFSHeuristicCalculation and THIS SHOULD NOT BE PRINTED!");
				else
					nextMove.nextBestMove = levelBestMove;
//				System.out.println("\n------- Next Best move is "+levelBestMove+" ------------");
			}
			return;
		}
	}

}
