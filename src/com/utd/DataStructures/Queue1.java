package com.utd.DataStructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Queue1 {
	
	Queue<Node> queueHead = new LinkedList<Node>();
	
	public Queue1() { 		// default construcor
	}
	
	public boolean addNodesToQueue(Node node1) throws Exception	// before adding we have to check if the 'board' already exists in the queue
	{
		Iterator<Node> itr = this.queueHead.iterator();
		while(itr.hasNext())
			if(itr.next().state.boardsEqual(node1.state))
				return false;
		
		this.queueHead.add(node1);
		return true;
	}
	
	@Override
	public String toString() { 		// printing the queue
		System.out.println("\nPRINTING THE QUEUE >>>>");
		Iterator<Node> itr = this.queueHead.iterator();
		while(itr.hasNext())
			try {
				itr.next().state.displayBoard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "";
	}

	public static void printQueue(Queue<Node> queueHead) throws Exception {
		
		System.out.println("\n>>>>>>>>>>>>>>>>>>>> PRINTING THE QUEUE <<<<<<<<<<<<<<<<<<<");

		int max =  Integer.MIN_VALUE;
		Node maxNode = null;
		Iterator<Node> itr = queueHead.iterator();
		while(itr.hasNext())
		{
			Node tempNode = itr.next();
			if(max < tempNode.state.calculateHeuristicFunction(tempNode.parentLink.state))
			{
				max = tempNode.state.calculateHeuristicFunction(tempNode.parentLink.state);
				maxNode = tempNode;
			}
		}
		System.out.println("Val = " + max);
		maxNode.state.displayBoard();
		
	}

}
