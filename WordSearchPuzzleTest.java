//Abby Jahns
//Assignment 1: Word search puzzle
//WordSearchPuzzleTest.java
//main method
//extra credit: try/catch on line 48-54 and line 75-83 of WordSearchPuzzle.java 
                //to catch user entering non integers
import java.util.Scanner;

public class WordSearchPuzzleTest {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Abby's Word Search Puzzle!");
        boolean gameloop = true;
        //creates word search puzzle 
        WordSearchPuzzle myPuzzle = new WordSearchPuzzle(20,20);
        do {
            int menuSelected = myPuzzle.openingMenu(input);
            switch (menuSelected){
                case 1:
                    myPuzzle.createPuzzle(input);
                    myPuzzle.printPuzzleWords();
                    break;
                case 2:
                    myPuzzle.addWords();
                    myPuzzle.viewSolutions();
                    myPuzzle.printPuzzleWords();
                    break;
                case 3:
                    myPuzzle.addWords();
                    myPuzzle.viewWithoutSolutions();
                    break;
                case 4:
                    gameloop = false;
                    System.out.println("Have a nice day.");
                    break;
            }//end switch case
        } while(gameloop);
    }//end main method
}//end WordSearchPuzzleTest class