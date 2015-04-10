<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkers - Online</title>
<script type="text/javascript">
	var currentPlayer = 1;			// currentPlayer = 1 is HUMAN move and currentPlayer = 2 is COMPUTER move
	var firstClick = 1;				// firstClick = 1 is SOURCE selected and firstClick = 2 is DESTINATION selected
	var sourceLocation = "";
	function fnHumanMove(t)
	{
		var location = t.id;
		var temp = t.src.split("/");
		var temp1 = temp[temp.length-1].split(".");
		var coin = temp1[0];
		if(firstClick == 1 && (coin == "BlackCoin" || coin == "BlackKingCoin") && currentPlayer == 1)
		{
			firstClick = 2;
			sourceLocation = location;
			document.getElementById("idDiv1").innerHTML = "<p><b> Human selected coin at (" + sourceLocation + ") </b>";
		}
		else if(firstClick == 2 && currentPlayer == 1)
		{
			firstClick = 1;
			var destinationLocation = location;
			
			if(coin == "BlackBoard")
 			{
				fnAjaxCall(destinationLocation);			// after this, if currentPlayer = 2 means, computer has to move now	
 			}
			else
			{
				document.getElementById("idDiv1").innerHTML = "<p><b> Move properly </b>";
			}
		}
	}
	
	function fnAjaxCall(destinationLocation)
	{
		var xmlHttp = new XMLHttpRequest();
		var addURL = "?currentPlayer="+currentPlayer+"&sourceLocation="+sourceLocation+"&destinationLocation="+destinationLocation;
		var URL = "AJAX_PlayGameServlet" + addURL;
		xmlHttp.open("GET", URL, true);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() 
		{
			if(xmlHttp.status == 200 && xmlHttp.readyState == 4)
			{
				var tempResponse = xmlHttp.responseText;

				var response = tempResponse.split(";");

				if(response[0] != "0")			// move is invalid
				{
					document.getElementById("idDiv1").innerHTML = "<p><b>"+response[0]+"</b>";
				}
				else 							// move is validated
				{	
					var flag = fnCheckIfCrossOver(response[2], response[3]);
					if(response[1] == "1")																		// HUMAN response here
					{
						currentPlayer = 2;
						if(fnChangingToKing(1, response[3]))											// if coin changing to KING
							document.getElementById(response[3]).src = "Pictures/BlackKingCoin.png";
						else
							document.getElementById(response[3]).src = document.getElementById(response[2]).src;
						document.getElementById(response[2]).src = "Pictures/BlackBoard.png";						// put the source coin to destination location
						document.getElementById("idDiv1").innerHTML = "<p><b>Human moved from ("+response[2]+") to ("+response[3]+")<p>Computer has to Move now</b>";
						setTimeout(function() { fnAjaxCall("0 0")() }, 2000);
					}
					else if(response[1] == "2")																	// COMPUTER response here
					{
						currentPlayer = 1;
						if(fnChangingToKing(2, response[3]))
							document.getElementById(response[3]).src = "Pictures/WhiteKingCoin.png";
						else
							document.getElementById(response[3]).src = document.getElementById(response[2]).src;
						document.getElementById(response[2]).src = "Pictures/BlackBoard.png";
						document.getElementById("idDiv1").innerHTML = "<p><b>Computer moved from ("+response[2]+") to ("+response[3]+")<p>Human has to Move now</b>";
					}
				}	// end of move is valid
				
				if(response[response.length - 1] == "Human wins the game")
					document.getElementById("idDiv1").innerHTML = "<p><b>Human wins the game</b>";
				else if(response[response.length - 1] == "Computer wins the game")
					document.getElementById("idDiv1").innerHTML = "<p><b>Computer wins the game</b>";
			}		
		}
	}
	function fnChangingToKing(currentPlayer, dest)
	{
		var temp = dest.split(" ");
		var x = parseInt(temp[0]);
		if(currentPlayer == 1 && x == 0)
			return true;
		else if(currentPlayer == 2 && x == 7)
			return true;
		else 
			return false;
	}
	function fnCheckIfCrossOver(source, dest)
	{
		var temp = source.split(" ");
		var x1 = parseInt(temp[0]);
		var y1 = parseInt(temp[1]);
		temp = dest.split(" ");
		var x2 = parseInt(temp[0]);
		var y2 = parseInt(temp[1]);
		
		if(Math.abs(x1-x2) == 1)
			return false;
		else
		{
// 			alert("Coin moves from (" + x1 + ","+y1+") to ("+x2+","+y2+")" );
			if((x1-x2==2) && (y1-y2==2))						// one up left
				document.getElementById((x1-1) + " " + (y1-1) ).src = "Pictures/BlackBoard.png";
			else if( (x1-x2 == 2) && (y2-y1 == 2) )				// one up right
				document.getElementById((x1-1) + " " + (y1+1) ).src = "Pictures/BlackBoard.png";
			else if ((x2-x1 == 2) && (y1-y2 == 2))				// one down left
				document.getElementById((x1+1) + " " + (y1-1) ).src = "Pictures/BlackBoard.png";
			else if ((x2-x1 == 2) && (y2-y1 == 2))				// one down right
				document.getElementById((x1+1) + " " + (y1+1) ).src = "Pictures/BlackBoard.png";
			return true;
		}
	}
</script>
</head>

<body bgcolor="F5F6FA">

	<form action="">
		
		<hr>
			<center><h3>Checkers - Play Free Online</h3></center>
		<hr>
		<center>
		<table cellspacing="0">
		<%
			int boardSize = 8;
			for(int i=0;i<boardSize;i++)
			{
				out.print("<tr>");
				
				for(int j=-1;j<boardSize;j++)
				{
					if(j == -1)
					{
						out.print("<td><b><font size='4'> "+i+" &emsp; </font></b></td>");
						continue;
					}
					String choosePic = "";
					if(i <= 2)
					{
						if((i+j)%2 == 1)
							choosePic = "WhiteCoin.png";
						else 
							choosePic = "WhiteBoard.png";
					}
					else if(i >= 5)
					{
						if((i+j)%2 == 1)
							choosePic = "BlackCoin.png";
						else 
							choosePic = "WhiteBoard.png";
					}
					else
					{
						if((i+j)%2 == 0)
							choosePic = "WhiteBoard.png";
						else
							choosePic = "BlackBoard.png";
					}

					out.print("<td><img src='Pictures/"+choosePic+"' id='"+i+" "+j+"' width='60' height='60' onclick='fnHumanMove(this)' /></td>");
				}
				
				out.print("</tr>");
			}
			out.print("<tr><td></td>");
			for(int i=0;i<boardSize;i++)
				out.print("<td><center><b><font size='4'> "+i+"</font></b></center></td>");
			out.print("</tr>");
			
		%>
		</table>

		<div id="idDiv1">
			
		</div>
		</center>
	</form>

</body>
</html>