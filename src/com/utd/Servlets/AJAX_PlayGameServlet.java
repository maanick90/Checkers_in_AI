package com.utd.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utd.DataStructures.Test1;
import com.utd.entity.Board;
import com.utd.entity.Response;

/**
 * Servlet implementation class AJAX_PlayGame
 */
@WebServlet("/AJAX_PlayGameServlet")
public class AJAX_PlayGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Board board = new Board();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer currentPlayer = Integer.parseInt(request.getParameter("currentPlayer"));
		String sourceLocation = (String) request.getParameter("sourceLocation");
		String destinationLocation = (String) request.getParameter("destinationLocation");
		
//		response.getWriter().print("0;1;"+startLocation+";"+endLocation+";0");
		
		Response responseObj = new Response();
		responseObj.setResponseString("0");
		
		String[] tempStart = sourceLocation.split(" ");
		int x1 = Integer.parseInt(tempStart[0]);
		int y1 = Integer.parseInt(tempStart[1]);
		String[] tempEnd = destinationLocation.split(" ");
		int x2 = Integer.parseInt(tempEnd[0]);
		int y2 = Integer.parseInt(tempEnd[1]);

		try {
//			Test1.PlayGame(board, x1, y1, x2, y2, responseObj, response);
//			this.board.displayBoard();
//			System.out.println("Response String = " + responseObj.getResponseString());
			
			if(currentPlayer == 1)
			{
				Test1.humanPlaysGame(board, x1, y1, x2, y2, responseObj);
				if(board.isGameEnds("2"))
					response.getWriter().print(responseObj.getResponseString()+";"+"1;"+x1+" "+y1+";"+x2+" "+y2+";0;Human wins the game");
				else
					response.getWriter().print(responseObj.getResponseString()+";"+"1;"+x1+" "+y1+";"+x2+" "+y2+";0;Game Not Ended");
			}
			else if(currentPlayer == 2)			// Computer moves now
			{
				Test1.computerPlaysGame(board, responseObj, response);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
