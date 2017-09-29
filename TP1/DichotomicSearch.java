// Purpose = Find in a minimum of tries a random integer (between 1 and 100) that have been auto-generated.

import java.util.Random;
import java.util.Scanner;

public class DichotomicSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random randomNumber = new Random();
        int tryValue;
        int computerValue;
        int numberOfTries = 0;
        boolean stopScript;
        do {
            stopScript = false;
            computerValue = randomNumber.nextInt(1000);
            tryValue = 0;
            while (tryValue != computerValue) {
                System.out.println("Please enter an integer between 1 and 1000 : ");
                tryValue = sc.nextInt();
                numberOfTries++;
                if (tryValue < 1 || tryValue > 1000) {
                    System.out.println("Wrong input, try again...");
                }
                else if (tryValue < computerValue) {
                    System.out.println("The number to find is higher...");
                }
                else if (tryValue > computerValue) {
                    System.out.println("The number to find is lower...");
                }
            }
            System.out.println("You won !!!!");
            System.out.println("Numbers of tries: " + numberOfTries + " ; Number to guess: " + computerValue);
        }
        while (stopScript);
        System.out.println("Goodbye");
    }
}