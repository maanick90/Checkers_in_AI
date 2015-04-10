package com.utd.TestPackage;

import com.utd.entity.Response;

public class Test1Object {
	
	public static void main(String[] args) {
		
		
		Response response = new Response();
		response.setResponseString("0");
		
		fn1(response);
		
		System.out.println(response.getResponseString());
		
	}

	private static void fn1(Response response) {
		
		response.setResponseString("Senthil");
		System.out.println(response.getResponseString());
	}
	
}
