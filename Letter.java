//Abby Jahns
//Assignment 1: Word Search Puzzle
//Puzzle.java
//object class
import java.util.Random;

public class Letter {
    private char letter;
    Random randomNumber = new Random();
    final int max = 122; //generate max ascii number 122 -> 'z'
    final int min = 97; //generates min ascii number 97 -> 'a'

    //constructor sets the letter to '_'
    public Letter() {
        this.letter = '_';
    }//end constructor Letter

    public void setLetter(char letter) {
        this.letter = letter;
    }//end setLetter method

    //generates random int, casts to ascii letter
    public void setRandom() {
        int randomLetter = randomNumber.nextInt(max - min + 1) + min;
        this.letter = (char) randomLetter;
    }//end setRandom method

    //sets letter to lowercase
    public void setLower() {
        int upper = (int) this.letter;
        int lowerLetter = upper + 32; //ascii uppercase to lowercase
        this.letter = (char) lowerLetter;
    }//end setLower method

    public char getLetter() {
        return letter;
    }//end getLetter method

    public String toString() {
        String s = "" + letter;
        return s;
    }//end toString method

}//end Letter class
