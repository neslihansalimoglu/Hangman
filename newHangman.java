
import java.io.IOException;
import java.util.Scanner;

/**
 * This program is a word guessing game called Hangman. The words are coming from a file called dictionary.txt. A random word is selected from the file. 
 * The world changes whenever the player loses a point. The player has 6 points in total.
 *
 * @author Neslihan Salimoglu
 * @since 12.05.2020
 *
 */

public class newHangman {
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************************** ");
        System.out.println("*               Welcome to my hangman game.            * ");
        System.out.println("*               I promise you It's so fun.             * ");
        System.out.println("*             You will have 6 tries in total.          * ");
        System.out.println("*                       Good luck :)                   * ");
        System.out.println("******************************************************** ");
        boolean play = true;

        while (play) {
            System.out.println(" Okay I thought of a word. Let's see if you can find it ");
            Hangman2 game = new Hangman2();
            
             // Check if the character is guessed already
                while (game.isGuessedAlready(letter)) {
                    System.out.println("Try again! You've already guessed that character.");
                    letter = (sc.next().toLowerCase()).charAt(0);

                // Lets draw our handsome boy
                System.out.println(game.bodyMan());
                System.out.println(game.getGuessedLetter());
                System.out.println("\n");
                
                System.out.println(" Try to find a letter.");
                char letter = (sc.next().toLowerCase()).charAt(0);
                System.out.println();
        }
               
                // Play the guess
                if (game.playGuess(letter)) {
                    System.out.println("You are going great.");
                } else {
                    System.out.println("oups lets try agin.");
                }
            
        }
    }
    
}