import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class Booking {
    static int bookingCounter = 1;
    int bookingId;
    int customerId;
    char from;
    char to;
    int pickupTime;
    int dropTime;
    int amount;

    Booking(int customerId, char from, char to, int pickupTime, int dropTime, int amount) {
        this.bookingId = bookingCounter++;
        this.customerId = customerId;
        this.from = from;
        this.to = to;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }
}

class Taxi {
    int taxiId;
    char currentLocation = 'A';
    int freeTime = 0;
    int totalEarnings = 0;
    List<Booking> bookings = new ArrayList<>();

    Taxi(int id) {
        this.taxiId = id;
    }

    boolean isFree(int pickupTime) {
        return pickupTime >= freeTime;
    }

    int calculateFare(int distance) {
        if (distance <= 5)
            return 100;
        return 100 + (distance - 5) * 10;
    }

    void assignBooking(int customerId, char from, char to, int pickupTime) {

        int points = Math.abs(from - to);
        int distance = points * 15;
        int amount = calculateFare(distance);
        int dropTime = pickupTime + points;

        Booking booking = new Booking(customerId, from, to, pickupTime, dropTime, amount);
        bookings.add(booking);

        totalEarnings += amount;
        freeTime = dropTime;
        currentLocation = to;

        System.out.println("Taxi-" + taxiId + " is allotted\n");
    }

    void displayDetails() {

        if (bookings.isEmpty()) return;

        System.out.println("\nTaxi-" + taxiId + " Total Earnings: Rs. " + totalEarnings);
        System.out.println("BookingID  CustomerID  From  To  Pickup  Drop  Amount");

        for (Booking b : bookings) {
            System.out.println(b.bookingId + "          " +
                    b.customerId + "          " +
                    b.from + "     " +
                    b.to + "    " +
                    b.pickupTime + "      " +
                    b.dropTime + "    " +
                    b.amount);
        }
    }
}

public class CallTaxiBooking {

    static List<Taxi> taxis = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Create 4 taxis (change number if needed)
        for (int i = 1; i <= 4; i++) {
            taxis.add(new Taxi(i));
        }

        while (true) {
            System.out.println("\n1. Book Taxi");
            System.out.println("2. Display Taxi Details");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookTaxi();
                    break;
                case 2:
                    displayTaxiDetails();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void bookTaxi() {

        try {
            System.out.print("Enter Customer ID: ");
            int customerId = sc.nextInt();

            System.out.print("Enter Pickup Point (A-F): ");
            char from = sc.next().toUpperCase().charAt(0);

            System.out.print("Enter Drop Point (A-F): ");
            char to = sc.next().toUpperCase().charAt(0);

            System.out.print("Enter Pickup Time (in hours): ");
            int pickupTime = sc.nextInt();

            if (from < 'A' || from > 'F' || to < 'A' || to > 'F')
                throw new InvalidBookingException("Invalid location. Use A-F.");

            if (from == to)
                throw new InvalidBookingException("Pickup and Drop cannot be same.");

            if (pickupTime < 0)
                throw new InvalidBookingException("Invalid time.");

            Taxi allocatedTaxi = null;
            int minDistance = Integer.MAX_VALUE;

            for (Taxi taxi : taxis) {
                if (taxi.isFree(pickupTime)) {

                    int dist = Math.abs(taxi.currentLocation - from);

                    if (dist < minDistance) {
                        minDistance = dist;
                        allocatedTaxi = taxi;
                    }
                    else if (dist == minDistance &&
                             taxi.totalEarnings < allocatedTaxi.totalEarnings) {
                        allocatedTaxi = taxi;
                    }
                }
            }

            if (allocatedTaxi == null)
                throw new InvalidBookingException("No taxi available at this time.");

            System.out.println("\nTaxi can be allotted.");
            allocatedTaxi.assignBooking(customerId, from, to, pickupTime);

        } 
        catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Invalid Input! Please enter correct data.");
            sc.nextLine();
        }
    }

    static void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            taxi.displayDetails();
        }
    }
}
