package com.utd.DataStructures;

import com.utd.entity.Board;

public class Tree {
	
	public Node treeRoot;
	
	public Tree(Board board) {			// default constructor
		this.treeRoot = new Node(board, null, null);
	}
	
}	
