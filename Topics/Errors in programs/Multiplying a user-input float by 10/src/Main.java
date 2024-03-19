import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create a Scanner object for obtaining user input.
        Scanner scanner = new Scanner(System.in);

        // Use the Scanner object to read a float from the user.
        // You need to assign the user's input to a variable here.
        float userInput; 

        // Multiply the user's input by 10 and assign the result to a variable.
        // Your code goes here.
        userInput = scanner.nextFloat();

        userInput = userInput * 10;

        System.out.printf("%.1f", userInput);

        // Print out the result.
        // Your code goes here.

        // Close the Scanner object.
        scanner.close();
    }
}