public class PrimeCheck {
    public static void main(String[] args) {
        
        int num1 = 19;
        int num2 = 49;

        checkPrime(num1);
        checkPrime(num2);
    }

    static void checkPrime(int number) {
        boolean isPrime = true;

        if (number <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(number + " is a Prime Number.");
        } else {
            System.out.println(number + " is NOT a Prime Number.");
        }
    }
}
