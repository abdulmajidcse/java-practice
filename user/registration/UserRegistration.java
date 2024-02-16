package user.registration;

import java.util.Scanner;

public class UserRegistration {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("<---Registration Form--->");

        System.out.print("Enter Your Name: ");
        String name = input.nextLine();

        System.out.print("Enter Your Email Address: ");
        String email = input.nextLine();

        System.out.print("Enter Your Phone Nnumber: ");
        Long phone = input.nextLong();

        System.out.print("Enter Your Gender: ");
        char gender = input.next().charAt(0);

        System.out.println("\n<---Your Information--->");

        System.out.println("Your Name is : " + name);
        System.out.println("Your Email Address is : " + email);
        System.out.println("Your Phone Number is : " + phone);
        System.out.println("Your Gender is : " + gender);

        System.out.print("\nConfirm your information by pressing y/n: ");
        char confirm = input.next().charAt(0);

        if (confirm == 'y') {
            System.out.println("Registration Successful");
        } else {
            System.out.println("Registration Failed");
        }

        input.close();
    }
}