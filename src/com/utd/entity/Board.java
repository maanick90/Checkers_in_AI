package com.utd.entity;

import com.utd.DataStructures.Successor;
import com.utd.play.Play;

public class Board {
	
	public String A[][];
	public int boardSize;

	public Board() {										// default constructor

		boardSize = 8;
		A = new String[][]{ {"0","2","0","2","0","2","0","2"},
							{"2","0","2","0","2","0","2","0"},
							{"0","2","0","2","0","2","0","2"},
							{"0","0","0","0","0","0","0","0"},
							{"0","0","0","0","0","0","0","0"},
							{"1","0","1","0","1","0","1","0"},
							{"0","1","0","1","0","1","0","1"},
							{"1","0","1","0","1","0","1","0"} };
	}

	public Board(Board board1)								// parameterized constructor

	{
		this.boardSize = board1.boardSize;
		this.A = board1.A;
	}
	
	public void setValuesTo(String[] str) throws Exception {				// given a String[], the value is set here
		
		this.boardSize = (int) Math.sqrt(str.length);
		for(int i=0, count=0;i<this.boardSize;i++)
			for(int j=0;j<this.boardSize;j++)
				this.A[i][j] = str[count++].trim();
	}

	public void displayBoard() throws Exception 			// display the board
	{
		System.out.print("\n\tDisplaying the board:");
		for(int i=0;i<boardSize;i++)
		{
			System.out.print("\n\t");
			for(int j=0;j<boardSize;j++)
			{
				System.out.print(A[i][j] + "  ");
			}
		}
	}

	public boolean boardsEqual(Board board1) throws Exception 		// returns TRUE if the 2 boards are same
	{
		if(this.boardSize != board1.boardSize)
			System.out.print("\nBoard size varies and THIS SHOULD NOT BE PRINTED!");

		for(int i=0;i<this.boardSize;i++)
			for(int j=0;j<this.boardSize;j++)
				if(!this.A[i][j].trim().equals(board1.A[i][j].trim()))
					return false;
		return true;
	}
	
	public boolean isGameEnds(String player) throws Exception				// returns TRUE if game ends
	{
		int count = 0;
		for(int i=0;i<this.boardSize;i++)
		{
			for(int j=0;j<this.boardSize;j++)
			{
				if(this.A[i][j].contains(player))
					++count;
			}
		}
		
		if(count == 0)
			return true;
		else
		{
			if(Successor.fnMapCoinsWithPossibleMoves(this, player).size() == 0)
				return true;
			else
				return false;
		}
	}

	/*
	 * Function that calculates the H(n)
	 */
	public int calculateHeuristicFunction(Board parentBoard) throws Exception
	{
		if(parentBoard == null)
			return 0;
		String a[][] = this.A;
		String computer = "2";					// assign computer player and human player here
		String human = null;
		String temp = (computer.equals("2"))?(human="1"):(human="2");
		
		int HN = 0;
		int coinValues = this.calculateCoinValues(computer) - this.calculateCoinValues(human);
		int opponentsBoundary = this.calculateOpponentsBoundary(computer) - this.calculateOpponentsBoundary(human);
		int rowValues = this.calculateRowValues(computer) - this.calculateRowValues(human);
		int becomingKingValues = this.calculateBecomingKing(parentBoard, computer) - this.calculateBecomingKing(parentBoard, human);
		int gapBetweenCoins = calculateGapBetweenCoins(computer) - calculateGapBetweenCoins(human);
		
		HN = coinValues + opponentsBoundary + rowValues + becomingKingValues + gapBetweenCoins;
//		System.out.println("H(n) value = " + HN);
//		System.out.println("\n>>>>>>>>>>>>>>>>>");
		return HN;
	}
	
	private int calculateGapBetweenCoins(String player)			// look at the closeness of the coins
	{
		if(noOfCoinsInTheBoard(player) < 6)
			return 0;
		int prevCoinPosition = -1;
		int sum = 0;
		for(int i=this.boardSize-1;i>=0;i--)
		{
			if(coinExists(i, player))
				prevCoinPosition = i;
			else if(prevCoinPosition != -1)
					sum += Math.abs((prevCoinPosition - i)) * 10;
		}
		return -1 * sum;
	}
	private boolean coinExists(int rowNo, String player)
	{
		for(int j=0;j<this.boardSize;j++)
			if(this.A[rowNo][j].contains(player))
				return true;
		return false;
	}
	private int noOfCoinsInTheBoard(String player)
	{
		int countCoins = 0;
		for(int i=0;i<this.boardSize;i++)
			for(int j=0;j<this.boardSize;j++)
				if(this.A[i][j].contains(player))
					++countCoins;
		return countCoins;
	}
	
	private int calculateBecomingKing(Board parentBoard, String player)			// consider a coin becoming a king in the next step
	{
		int diffKingCount = 0;
		String kingPlayer = player+""+player;
		for(int i=0;i<this.boardSize;i++)
		{
			for(int j=0;j<this.boardSize;j++)
			{
				if(this.A[i][j].equals(kingPlayer))
					diffKingCount += 1;
				if(parentBoard.A[i][j].equals(kingPlayer))
					diffKingCount -= 1;
			}
		}
		return diffKingCount * 1000;
	}

	private int calculateRowValues(String player) { 			// consider ROW values 

		String a[][] = this.A;
		Integer fibonacciArray[] = generateFibonacciToACertainNo(this.boardSize);				// generate a Fibonacci array
		int sum = 0;
		int pawnValue = 2, kingValue = 4;
		int subtract = 0;
		if(player.equals("2"))
			subtract = 0;
		else
			subtract = this.boardSize - 1;
		
		for(int i=0;i<this.boardSize;i++)
			for(int j=0;j<this.boardSize;j++)
			{
				if(a[i][j].equals(player))
					sum += (pawnValue * (Math.abs(subtract - i) + 1) * fibonacciArray[Math.abs(subtract - i)] * 10);
				else if(a[i][j].contains(player))
					sum += (kingValue * (Math.abs(subtract - i) + 1) * fibonacciArray[Math.abs(subtract - i)] * 10);
			}
		return sum;
	}

	private int calculateOpponentsBoundary(String player) { 	// consider the boundary  
		
		String a[][] = this.A;
		int sum = 0;
		int pawnValue = 1, kingValue = 2, ownBoundaryValue = 30, opponentBoundaryValue = 50;				// set the values to calculate the heuristic function 
		int boundaryStart = 0, boundaryEnd = 0;
		if(player.equals("2"))
		{
			boundaryStart = 0;
			boundaryEnd = 3;
		}
		else if(player.equals("1"))
		{
			boundaryStart = 4;
			boundaryEnd = 7;
		}
		else
			System.out.println("I am inside calculateOpponentsBoundary and THIS SHOULD NOT BE PRINTED!");
		
		for(int i=0;i<this.boardSize;i++)
			for(int j=0;j<this.boardSize;j++)
			{
				if(a[i][j].equals(player) && i>=boundaryStart && i<=boundaryEnd)
					sum += (pawnValue * ownBoundaryValue);
				else if(a[i][j].equals(player))
					sum += (pawnValue * opponentBoundaryValue);
				else if(a[i][j].contains(player) && i>=boundaryStart && i<=boundaryEnd)
					sum += (kingValue * ownBoundaryValue);
				else if(a[i][j].contains(player))
					sum += (kingValue * opponentBoundaryValue);
			}
		
		return sum;
	}

	private int calculateCoinValues(String player) {			// consider coin values 

		int sum = 0;
		String a[][] = this.A;
		for(int i=0;i<this.boardSize;i++)
			for(int j=0;j<this.boardSize;j++)
				if(a[i][j].equals(player))						// for PAWN coins
					sum += 50;
				else if(a[i][j].contains(player))				// for KING coins
					sum += 150;
		return sum;
	}

	
	/*
	 * Generate children boards here with a given moveString
	 */
	public Board generateChildrenBoard(String moveString, String player) throws Exception 
	{
		Board newBoard = new Board();
//		System.out.print("\nMove = " + moveString);
		Integer moves[] = StringSplitAndToInteger(moveString);
		
		copy2Boards(newBoard, this);
		if(!Play.moveACoin(newBoard, moves[0], moves[1], moves[2], moves[3], player, false, new Response()))
		{
//			System.out.println("\nI am inside Board.generateChildrenBoard and THIS SHOULD NOT BE PRINTED!");
		}
		
		return newBoard;
	}
	
	
	/*
	 * Additional STATIC Functions
	 */
	public static Integer[] StringSplitAndToInteger(String moveString) throws Exception {	// just split the string and generate the index 

		String movesSplit[] = moveString.split(" ");
		Integer []moves = new Integer[movesSplit.length];
		for(int i=0;i<movesSplit.length;i++)
			moves[i] = Integer.parseInt(movesSplit[i]);

		return moves;
	}
	
	public static void copy2Boards(Board toBoard, Board fromBoard) throws Exception {		// copy 2 board values 
		
		if(toBoard.boardSize != fromBoard.boardSize)
		{
			System.out.println("Board.copy2Boards and THIS SHOULD NOT BE PRINTED!");
			return;
		}
		for(int i=0;i<toBoard.boardSize;i++)
			for(int j=0;j<toBoard.boardSize;j++)
				toBoard.A[i][j] = fromBoard.A[i][j];
	}

	private Integer[] generateFibonacciToACertainNo(int n) {		// a normal function to generate Fibonacci series upto 'n'
		
		Integer fibonacciArray[] = new Integer[n];
		int a = 1, b = 2;
		int index = 0;
		fibonacciArray[index++] = a;
		fibonacciArray[index++] = b;
		
		for(int i=index;i<n;i++)
		{
			fibonacciArray[i] = a+b;
			a = b;
			b = fibonacciArray[i];
		}

		return fibonacciArray;
	}

}
