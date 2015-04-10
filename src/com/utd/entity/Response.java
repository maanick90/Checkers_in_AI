package com.utd.entity;

public class Response {
	
	private String responseString;
	private Integer Player;
	private String startLocation;
	private String endLocation;
	private Integer PawnOrKing = 0;						// 0 means pawn and 1 means king
	
	public Response() {
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public Integer getPlayer() {
		return Player;
	}

	public void setPlayer(Integer player) {
		Player = player;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public Integer getPawnOrKing() {
		return PawnOrKing;
	}

	public void setPawnOrKing(Integer pawnOrKing) {
		PawnOrKing = pawnOrKing;
	}

}
