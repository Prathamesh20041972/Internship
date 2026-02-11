import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        
        int reverse = 0;

        while(number != 0) {
            int digit = number % 10;      // Get last digit
            reverse = reverse * 10 + digit; // Add digit to reverse
            number = number / 10;        // Remove last digit
        }

        System.out.println("Reversed number: " + reverse);
    }
}
