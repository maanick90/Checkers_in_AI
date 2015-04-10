package com.utd.DataStructures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.utd.entity.Board;
import com.utd.entity.Response;
import com.utd.play.Play;

public class Successor {
	
	/*
	 * Given a position(x1,y1) in a board, it generates a List<PossibleEndMoves>
	 * PossibleEndMove = x2 + " " + y2 
	 */
	public static List<String> listPossibleMoves(Board board, int x1, int y1) throws Exception
	{
		String a[][] = board.A;
		String player = a[x1][y1];
		
		List<String> listPossibleMoves = new ArrayList<String>();
		
		/*
		 * PAWN coins, so only single-forward and cross-forward moves are considered
		 */
//		if(x1 == 5 && y1 == 5)
//			board.displayBoard();
		for(int i=2,count1=1; count1<=2; i--,count1++)
			for(int j=i,count2=1; count2<=2; j=j-2*i,count2++)
			{
				int mul = 1;
				if(player.contains("1"))											// change the direction for player'1'
					mul = -1;
				if(Play.validateTheMove(board, x1, y1, x1+mul*i, y1+j, player, false, new Response()))
					listPossibleMoves.add((x1+mul*i) + " " + (y1+j));				// add FORWARD moves the list
			}

		/*
		 * KING Coins, so consider both single-backward and cross-backward coins also 
		 */
		if(player.length() == 2)
		{
			for(int i=2,count1=1; count1<=2; i--,count1++)
				for(int j=i,count2=1; count2<=2; j=j-2*i,count2++)
				{
					int mul = 1;
					if(player.contains("1"))									// change the direction for player'1'
						mul = -1;
					if(Play.validateTheMove(board, x1, y1, x1-(mul * i), y1-j, player, false, new Response()))
						listPossibleMoves.add((x1-(mul * i)) + " " + (y1-j));				// add BACKWARD moves the list
				}
		}
		
		return listPossibleMoves;
	}

	/*
	 * Returns a 'Map' that contains possible 'startMove' and possible List<endMoves> for the given 'board'
	 */
	public static Map<String, List<String>> fnMapCoinsWithPossibleMoves(Board board, String player) throws Exception
	{
		Map<String, List<String>> mapCoinsWithPossibleMoves = new LinkedHashMap<String, List<String>>();
		
		for(int i=0;i<board.boardSize;i++)
		{
			for(int j=0;j<board.boardSize;j++)
			{
				if(board.A[i][j].contains(player))
				{
					List<String> tempList = listPossibleMoves(board, i, j);
					if(tempList.size() > 0)
					{
						mapCoinsWithPossibleMoves.put(i+" "+j, tempList);
					}
				}
			}
		}
		return mapCoinsWithPossibleMoves;
	}

	
	/*
	 * List<StartIndex +" "+ EndIndex>
	 * StartIndex = x1 + " " + y1
	 * EndIndex   = x2 + " " + y2
	 */
	public static List<String> listOfMovesWithStartAndEndIndex(Node root, String player) throws Exception {
		
		List<String> outputList = null;
		
		Map<String, List<String>> mapCoinsWithPossibleMoves = Successor.fnMapCoinsWithPossibleMoves(root.state, player);
		if(mapCoinsWithPossibleMoves != null)
		{
			for(Map.Entry<String, List<String>> entry : mapCoinsWithPossibleMoves.entrySet())
			{
				if(outputList == null)
					outputList = new ArrayList<String>();
				
//				System.out.print("\nCoin " + entry.getKey() + " can move to ");
				List<String> tempList = entry.getValue();
				for(String str: tempList)
				{
					String strOutputList = entry.getKey();
//					System.out.print("(" + str + "), ");
					strOutputList = strOutputList.concat(" " + str + " ");
					outputList.add(strOutputList);
				}
			}
		}
		return outputList;
	}

}
