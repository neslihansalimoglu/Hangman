
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This program is a word guessing game called Hangman. The words are coming from a file called dictionary.txt. A random word is selected from the file. 
 * The world changes whenever the player loses a point. The player has 6 points in total.
 *
 * @author Neslihan Salimoglu
 * @since 12.05.2020
 */

public class Game {

    //Lets set the variabales and strings
    private static final int counter = 6;
    int guesses_left = 0;

    String thoughtWord;
    StringBuilder guessedLetter;                            // stringbuilder is to change characters inside the string
    ArrayList<Character> lettersUsed = new ArrayList<>();   // Array List is to keep track of the words that has been gueesed and to not guess the same letter again. 
                                                          
    ArrayList<String> dictionary = new ArrayList<>();       //This is the list of words that will be picked from 
    private static FileReader fileReader;                   //To get the file
    private static BufferedReader bFile;                    //To iterate through the file and take the word
    
    //This is our constructor
    public Hangman() throws IOException {
        loadWords();
        thoughtWord = newWord();
        guessedLetter = initializeGuessedLetter();
    }
  
    //This method is to read the file and load the words
    public void loadWords(){
        try {
            File inFile = new File("dictionary.txt");
            inFileReader = new FileReader(inFile);
            bFile = new BufferedReader(inFileReader);
            String currentLine = bFile.readLine();
            
            while (currentLine != null) {
                dictionary.add(currentLine);
                currentLine = bFile.readLine();
            }
            bFile.close();
            inFileReader.close();
        } catch(IOException e) {
          throw new RuntimeException("Error reading the file",e);
        }
    }

    //This method is to pick new words randomly
    public String newWord() {
        Random rnd = new Random();
        int word = Math.abs(rnd.nextInt()) % dictionary.size();
        return dictionary.get(word);
    }
    
    //This method is to draw the lines " _ _ " 
    public StringBuilder guessedLetter() {
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < thoughtWord.length() * 2; i++) {  
            if (i % 2 == 0) {
                current.append("_"); //add to the builder
            } else {
                current.append(" "); 
            }
        }
        return current;
    }
    
    public String getGuessedLetter() {
        return "You gueesed " + guessedLetter.toString();
    }
    
    public String getCondensedguessedLetter() {
        String letter = guessedLetter.toString();
        return letter.replace(" ", "");
    }
    
    
    public boolean isGuessedAlready(char letter) {
       return lettersUsed.contains(letter);
    }
    
    //This method is to check if any of the letters that has been gueesed is true
    public boolean status(char letter) {
        boolean correctGuess = false;
        lettersUsed.add(letter);

        for (int i = 0; i < thoughtWord.length(); i++) { 
            if (thoughtWord.charAt(i) == letter) {
                guessedLetter.setCharAt(i * 2, letter);
                correctGuess = true;
        }
        
        if (!correctGuess) {
            guesses_left++;
        }
        
        return correctGuess;
    }

      /*This method allows to draw our handsome boy whenever the player loses. 
        There are 6 cases since the player has 6 tries.*/
       public String bodyMan(int guesses_left) {
        switch(guesses_left) {
        
        case 1:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("__");
        break;
        
      case 2:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("|  |");
        System.out.println("|");
        System.out.println("|");
        System.out.println("__");
        break;
        
      case 3:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("__");
        break;
        
      case 4:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|\\");
        System.out.println("|");
        System.out.println("|");
        System.out.println("__");
        System.out.println("Oups, my upper body is already done. Be careful! ");
        break;
        
      case 5:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|\\");
        System.out.println("| /");
        System.out.println("|");
        System.out.println("__");
        System.out.println(" you just have 1 more try. Be carefull.");
        break;
        
      case 6:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|\\");
        System.out.println("| / \\");
        System.out.println("|");
        System.out.println("__");
        break;
        
      default:
        System.out.println(" ");
    }
  }

    public boolean lostWorld() {
        return guesses_left >= counter;
    }
    
    public boolean winWorld() {
        String letter = getCondensedCurrentGuess();
        return letter.equals(thoughtWord);
    }
     public boolean isOver() {
        if (winWorld()) {
            System.out.println("------------------- Wow you saved our man. That's perfect -----------------");
            return true;

        } else if (lostWorld()) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!! Sorry, you lost. You killed him :( !!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("                    The word that caused the death was " + thoughtWord            );
            return true;
        }
        return false;
    }
    
    
}