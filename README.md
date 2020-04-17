INTRODUCTION:


Checkers is a game similar to Chess. It is played on an 8x8 draughts board and consists of 12 white pieces and 12 black pieces. A piece can move only in the forward diagonal direction to an unoccupied space. If the other player’s piece sits on the next diagonal space, then it can jump over thereby capturing the other player’s piece, provided there is a space next to the opponent’s piece. There is another type of piece called KING piece which can move in both forward and backward directions.
 
APPLICATION:
    
	
This project is a web application project in which one player is Human and the other is the computer which is basically an application developed using Artificial Intelligence technologies like Game Trees, Minimax algorithm, Alpha-Beta pruning. I have used Java, J2EE, JavaScript, Ajax, JSP, and Servlets.
 
A board’s representation at any point is considered as a state. The way how the computer player works is whenever a step is moved by the human player, my algorithm calculates the next possible moves which are represented as a tree. Each node in the tree represents a state achieved by making the next possible move. The tree consists of both player’s next possible moves. The nodes on the 2nd level (children of tree's root) of the tree are possible moves by the computer, 3rd level by human and so on. Different heuristic approaches are considered to calculate the total value (weightage) of each node. Based on the next best heuristic value (not necessarily the highest value), the computer chooses the next step to move and then the human player continues. 
 
HEURISTIC APPROACHES:

The different heuristic approaches considered are
    
	1. The number of pieces on the board.
	2. Higher values for KING pieces.
    3. Piece becoming a KING piece in the next step.
    4. The Closeness of the piece (giving importance to the defense). If a piece is diagonally next to each other, then the opponent’s piece cannot jump over since there will not be any space provided for the opponent piece to jump over.
    5. Piece value based on its current row number. Pieces that move forward towards the opponent are given greater value and pieces that are at the lower row no are probably silent pieces and hence given a lower value
 
ALGORITHM:

A tree will be constructed each time after the human player moves. 

	1. The root node of the tree consists of n children which represent the next n possible moves. 
	2. The tree is created to a depth of 5 to consider all the next 5 possible moves between the two players. 
	3. Once the tree is created, the value of each node is calculated using the above heuristic approaches.
	4. The algorithm chooses the next best move (not necessarily the move with the highest heuristic value). 
	5. In order to improve the run time efficiency, Alpha-Beta pruning is used to chop off sub-trees. 

These steps continue until the end of the game. 

