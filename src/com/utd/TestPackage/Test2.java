package com.utd.TestPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.utd.DataStructures.Node;
import com.utd.DataStructures.Queue1;
import com.utd.DataStructures.Test1;
import com.utd.DataStructures.Tree;
import com.utd.entity.Board;
import com.utd.entity.Response;

public class Test2 {

	public static void main(String[] args) throws Exception {

		String str =  "0  0  0  0  0  0  0  0 "   +  
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  2  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  1  0  0 "  +
					 " 0  0  0  0  0  0  0  0 " ;
		String strSplit[] = str.split("  ");
		Board parentBoard = new Board();
		parentBoard.setValuesTo(strSplit);
	
		String str1 = "0  0  0  0  0  0  0  0 "   +  
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  0  0  0  0 "  +
					 " 0  0  0  0  2  0  0  0 "  +
					 " 0  0  0  0  0  1  0  0 "  +
					 " 0  0  0  0  0  0  0  0 " ;
		String strSplit1[] = str1.split("  ");
		Board board = new Board();
		board.setValuesTo(strSplit1);
		board.displayBoard();
		
		Test1.computerPlaysGame(board, new Response(), new HttpServletResponse() {
			
			@Override
			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentType(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public PrintWriter getWriter() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendRedirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendError(int arg0, String arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getStatus() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Collection<String> getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
