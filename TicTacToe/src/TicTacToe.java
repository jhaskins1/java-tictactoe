import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {

	    char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
	    {'-', '+', '-', '+', '-'},
	    {' ', '|', ' ', '|', ' '},
	    {'-', '+', '-', '+', '-'},
	    {' ', '|', ' ', '|', ' '}};

	    printGameBoard(gameBoard);

	    while(true) {
	    	@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
	    	System.out.println("Enter your placement (1-9):");
		    int pposition = scan.nextInt();
		    while(playerPositions.contains(pposition) || cpuPositions.contains(pposition)) {
		    	System.out.println("Position taken! Enter a valid position.");
		    	pposition = scan.nextInt();
		    }
		    
		    placePiece(gameBoard, pposition, "player");
		    
		    String result = checkWinner();
		    
		    Random rand = new Random();
		    int cposition = rand.nextInt(9) + 1;
		    while(playerPositions.contains(cposition) || cpuPositions.contains(cposition)) {
			    cposition = rand.nextInt(9) + 1;
		    }
		    placePiece(gameBoard, cposition, "cpu");
		    
		    printGameBoard(gameBoard);
		    
		    result = checkWinner();
		    if(result.length() >0) {
		    	System.out.println(result);
		    	break;
		    }
	    }
	    

	  }

	 public static void printGameBoard(char[][] gameBoard) {
	    for(char[] row : gameBoard) {
	      for(char c : row) {
	        System.out.print(c);
	      }
	      System.out.println();
	    }
	  }
	  
	 public static void placePiece(char[][] gameBoard, int position, String user) {
		  
		  char symbol = ' ';
		  
		  if(user.equals("player")) {
			  symbol = 'X';
			  playerPositions.add(position);
		  } else if(user.equals("cpu")) {
			  symbol = 'O';
			  cpuPositions.add(position);
		  }
		  
		  switch(position) {
		    case 1: 
		    	gameBoard[0][0]=symbol;
		    	break;
		    case 2: 
		    	gameBoard[0][2]=symbol;
		    	break;
		    case 3: 
		    	gameBoard[0][4]=symbol;
		    	break;
		    case 4: 
		    	gameBoard[2][0]=symbol;
		    	break;
		    case 5: 
		    	gameBoard[2][2]=symbol;
		    	break;
		    case 6: 
		    	gameBoard[2][4]=symbol;
		    	break;
		    case 7: 
		    	gameBoard[4][0]=symbol;
		    	break;
		    case 8: 
		    	gameBoard[4][2]=symbol;
		    	break;
		    case 9: 
		    	gameBoard[4][4]=symbol;
		    	break;
		    default:
		    	break;
		    }
	  }
	  
	 public static String checkWinner() {
		  
		  List topRow = Arrays.asList(1, 2, 3);
		  List midRow = Arrays.asList(4, 5, 6);
		  List botRow = Arrays.asList(7, 8, 9);
		  List leftCol = Arrays.asList(1, 4, 7);
		  List midCol = Arrays.asList(2, 5, 8);
		  List rightCol = Arrays.asList(3, 6, 9);
		  List lrDiag = Arrays.asList(1, 5, 9);
		  List rlDiag = Arrays.asList(3, 5, 7);
		  
		  List<List> wincons = new ArrayList<List>();
		  wincons.add(topRow);
		  wincons.add(midRow);
		  wincons.add(botRow);
		  wincons.add(leftCol);
		  wincons.add(midCol);
		  wincons.add(rightCol);
		  wincons.add(lrDiag);
		  wincons.add(rlDiag);
		  
		  for(List l : wincons) {
			  if(playerPositions.containsAll(l)) {
				  return "Congratulations! You won!";
			  } else if(cpuPositions.containsAll(l)) {
				  return "CPU wins! Sorry :(";
			  } else if(playerPositions.size() + cpuPositions.size() == 9) {
				  return "Draw!";
			  }
		  }
		  
		  return "";
	  }
}
