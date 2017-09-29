

/*
*   TP1: DichotomicSearch Java program.
*   Purpose = Find in a minimum of tries a random integer (between 1 and 100) that have been auto-generated.
*/


// import Random and Scanner class
import java.util.Random;
import java.util.Scanner;

public class DichotomicSearch {
    public static void main(String[] args) {
        // create instance of class for Scanner and Random
        Scanner sc = new Scanner(System.in);
        Random randomNumber = new Random();
        // initialize variables
        int tryValue;
        int computerValue;
        int numberOfTries = 0;
        boolean stopScript;
        // do, while loop which is browsed at least one time and ended on condition stopScript=false
        do {
            stopScript = false;
            // The program choose a random int between 1 and 1000
            computerValue = randomNumber.nextInt(1000);
            tryValue = 0;
            while (tryValue != computerValue) {
                System.out.println("Please enter an integer between 1 and 1000 : ");
                tryValue = sc.nextInt();
                // For each loop the number of tries is iterate
                numberOfTries++;
                // Loop for invalid input
                if (tryValue < 1 || tryValue > 1000) {
                    System.out.println("Wrong input, try again...");
                }
                // Browse if user int is too low
                else if (tryValue < computerValue) {
                    System.out.println("The number to find is higher...");
                }
                // Browse if user int is too high
                else if (tryValue > computerValue) {
                    System.out.println("The number to find is lower...");
                }
            }
            // Browse if user input is equal to computer random int
            System.out.println("You won !!!!");
            System.out.println("Numbers of tries: " + numberOfTries + " ; Number to guess: " + computerValue);
        }
        while (stopScript);
        System.out.println("Goodbye");
    }
}
