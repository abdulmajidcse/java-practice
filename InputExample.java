import java.util.Scanner;

public class InputExample {
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);

        System.out.print("What's your name? :) ");

        String name = userInput.nextLine();

        System.out.println("Welcome " + name + "!");

        // terminal always active magic!
        while (userInput.hasNextLine()) {
            System.out.println(userInput.nextLine());
        }

        userInput.close();
    }
}
