//Abby Jahns
//Assignment 1: Word Search Puzzle
//WordSearchPuzzle.java
//manager method
//extra credit: try/catch on line 48-54 and line 75-83 

import java.util.Scanner;
import java.util.InputMismatchException;

public class WordSearchPuzzle {
    private final int ROWSIZE;
    private final int COLUMNSIZE;
    //make puzzleArray a 2 dimensional array of object Letter
    Letter[][] puzzleArray;

    private static String theme;
    private String[] words; 

    //constructor WordSearchPuzzle with input dimensions
    public WordSearchPuzzle(int setRow, int setColumn) {
        ROWSIZE = setRow;
        COLUMNSIZE = setColumn;
        puzzleArray = new Letter[setRow][setColumn];
        //set words array to length 8
        this.words = new String[] {"_", "_", "_", "_", "_", "_", "_", "_"};
        
        //populates array with '_' letters
        for (int row = 0; row < puzzleArray.length; row++) {
            for (int column = 0; column < puzzleArray[row].length; column++) {
                puzzleArray[row][column] = new Letter();
            }//end column for loop
        }//end row for loop
    }//end WordSearchPuzzle constructor


    //prints game menu 
    public int openingMenu(Scanner input) {
        int answer = 0;
        boolean success = true;
        do {
            System.out.println();
            System.out.println("Do you want to: ");
            System.out.println("1) Create a crossword puzzle");
            System.out.println("2) View a crossword puzzle with solutions");
            System.out.println("3) View a crossword puzzle without solutions");
            System.out.println("4) Quit "); 
            //catch any entry thats not a integer
            try {
                answer = input.nextInt();
                success = false;
            } catch (InputMismatchException ex) {
                System.out.println("This not an integer.");
                success = true; //loop again
            }
            input.nextLine(); //consumming new line character
            switch (answer) {
                case 1: return 1;
                case 2: return 2;
                case 3: return 3;
                case 4: return 4;
                default:
                    System.out.println("That is not a valid entery. "
                        + "Please enter a number 1 - 4.");
                    answer = 0;
            }
        } while (success);
        return 5;
    }// end openingMenu method

    //method for if menu 1 is selected, creates your own puzzle
    //asks for using input theme, number of words and words
    public void createPuzzle(Scanner input) {
        System.out.println("Option 1 selected: "
            + "This will create a 20x20 word search puzzle.");
        System.out.print("Please enter a theme for your word search: ");
        theme = input.nextLine();
        theme = theme.toUpperCase();

        //ask for a number of words to enter into puzzle
        int numberWords = 0;
        boolean notAnInt = true;
        do {
            System.out.print("How many words would you like in the word search: ");
            try {
                numberWords = input.nextInt();
                input.nextLine(); //consume the newline character
                notAnInt = false;
            } catch (InputMismatchException ex) {
                input.nextLine(); //consume the newline character
                System.out.println("This is not a integer.");
                notAnInt = true;
            }
            if (numberWords > 8) {
                System.out.println("This is too many words to fit, " 
                    + "enter a number under 9.");
                notAnInt = true; //loop again
            }
        } while (notAnInt);

        //enter words and set them in words array
        System.out.println("Please enter " + numberWords + " words:");
        for (int i = 0; i < numberWords; i++) {
            this.words[i] = input.next();
            //checks that word isn't too long, over 10 letters
            if (this.words[i].length() > 10) {
                System.out.println("This word is too long, keep it "
                    + "under 10 letters. Enter another:");
                i--;
            }
            this.words[i] = this.words[i].toUpperCase();
        }//end for loop to enter words
    }//end createPuzzle method

    //add words into puzzle array
    public void addWords() {
        this.addWordHorizontal(words[0], 0, 6);
        this.addWordDiagBackward(words[1], 8, 19);
        this.addWordVertical(words[2], 2, 13);
        this.addWordDiagonal(words[3], 1, 0);
        this.addWordHorizBackward(words[4], 19, 11);
        this.addWordVertBackward(words[5], 16, 0);
        this.addWordVertical(words[6], 10, 19);
        this.addWordHorizontal(words[7], 13, 2);
    }//end addWords method

    //prints out a puzzle with only puzzle words shown
    //all other Letters are '_'
    public void viewSolutions() {
        System.out.println("Option 2 selected: view puzzle with solutions");
        for (int row = 0; row < this.ROWSIZE; row++) {
            for (int column = 0; column < this.COLUMNSIZE; column++) {
                //if letter is random lowercase letter, change to '_'
                //casts Letter to int, checks if its greater then ascii lowercase a
                if ((int) this.puzzleArray[row][column].getLetter() > 96) {
                    this.puzzleArray[row][column].setLetter('_');
                }
                System.out.print(" " + this.puzzleArray[row][column].toString());
            }//end column for loop
            System.out.println();
        }//end row for loop
    }//end viewSolutions method

    //method prints out puzzle with random filler words added
    //also changes puzzle words to lowercase
    public void viewWithoutSolutions() {
        System.out.println("Option 3 selected: view puzzle without a solution");
        //goes through 2 dimensional puzzle array and changes '_' to random letter
        //if its not a '_' then its a puzzle word, then changes it to lower case
        for (int row = 0; row < this.ROWSIZE; row++) {
            for (int column = 0; column < this.COLUMNSIZE; column++) {
                //if letter '_' then set to random letter
                if (this.puzzleArray[row][column].getLetter() == '_') {
                    this.puzzleArray[row][column].setRandom();
                } else { //else make letter lower case
                    this.puzzleArray[row][column].setLower();
                }
                //print out puzzle array
                System.out.print(" " + this.puzzleArray[row][column].toString());
            }//end column for loop
            System.out.println();
        }//end row for loop
    }//end vieWithoutSolutions()

    //print words at bottom of puzzle
    //or to test words string array
    public void printPuzzleWords() {
        System.out.println("---------------Theme: " + theme + "---------------");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i] + "  ");
            //indents after 4 words are printed
            if (i == 3) {
                System.out.println();
            }
        }//end print search words for loop
        System.out.println();
    }//end printPuzzle method


    //goes through string word, adds each char to puzzle array
    //takes in String word and coordinated for were to start word placement
    public void addWordHorizontal(String word, int startRow, int startCol) {
        //increment this to move right in array
        int horizontal = startCol;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            this.puzzleArray[startRow][horizontal].setLetter(letter);
            horizontal++;
        }//end for loop
    }//end addWordHOrizontal method

    public void addWordVertical(String word, int startRow, int startCol) {
        //increment this to move vertically down
        int vertical = startRow;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            this.puzzleArray[vertical][startCol].setLetter(letter);
            vertical++;
        }//end for loop
    }//end addWordVertical method

    public void addWordDiagonal(String word, int startRow, int startCol) {
        //increment this to move diagonal down
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            puzzleArray[row][col].setLetter(letter);
            row++;
            col++;
        }//end for loop
    }//end addWordDiagonal method

    public void addWordHorizBackward(String word, int startRow, int startCol) {
        //decrement this to move back horizontal
        int hori = startCol;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            puzzleArray[startRow][hori].setLetter(letter);
            hori--;
        }//end for loop
    }//end addWordHorizBackward method

    public void addWordVertBackward(String word, int startRow, int startCol) {
        //decrement this to move backward vertically
        int vert = startRow;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            puzzleArray[vert][startCol].setLetter(letter);
            vert--;
        }//end for loop
    }//end addWordVertBackward method

    public void addWordDiagBackward(String word, int startRow, int startCol) {
        //decrement this to move diagonally up
        int row = startRow;
        int col = startCol;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            puzzleArray[row][col].setLetter(letter);
            row++;
            col--;
        }//end for loop
    }//end addWordDiagBackward method

}//end WordSearchPuzzle class
