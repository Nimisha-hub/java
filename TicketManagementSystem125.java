import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Booking extends Thread {
    int seatsnumber;
    String passengername;
    int age;
    
    public static HashMap<Integer, String> seatsavailability = new HashMap<>();
    public static ArrayList<String> bookinghistory = new ArrayList<>();

    static {
        seatsavailability.put(1, null);
        seatsavailability.put(2, null);
        seatsavailability.put(3, null);
        seatsavailability.put(4, null);
        seatsavailability.put(5, null);
        seatsavailability.put(9, null);
    }

    public Booking(int seatsnumber, String passengername, int age) {
        this.seatsnumber = seatsnumber;
        this.passengername = passengername;
        this.age = age;
    }

    @Override
    public void run() {
        try {
            Bookseat();
        } catch (InvalidBookingException e) {
            System.out.println("Error Caught: " + e.getMessage());
        }
    }

    public synchronized void Bookseat() throws InvalidBookingException {
        if (seatsnumber <= 0 || seatsnumber > 15) {
            throw new InvalidBookingException("Seat number should be positive and within 15.");
        }
        
        if (!seatsavailability.containsKey(seatsnumber)) {
            throw new InvalidBookingException("Seat " + seatsnumber + " is not available for booking.");
        }
        
        if (seatsavailability.get(seatsnumber) != null) {
            throw new InvalidBookingException("Seat " + seatsnumber + " is already booked.");
        }

        seatsavailability.put(seatsnumber, passengername);
        bookinghistory.add("Seat " + seatsnumber + " - " + passengername + " (Age: " + age + ")");
        System.out.println("✅ Booking successful! Seat: " + seatsnumber + ", Passenger: " + passengername);
    }

    public static synchronized void showDetails() {
        System.out.println("\nBooking History:");
        for (int i = 0; i < bookinghistory.size(); i++) {
            System.out.println(bookinghistory.get(i));
        }

        System.out.println("\nSeat Availability:");
        ArrayList<Integer> seats = new ArrayList<>(seatsavailability.keySet());
        for (int i = 0; i < seats.size(); i++) {
            int seat = seats.get(i);
            String status = (seatsavailability.get(seat) == null) ? "Available" : "Booked by " + seatsavailability.get(seat);
            System.out.println("Seat " + seat + ": " + status);
        }
    }
}

public class TicketManagementSystem125 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many passengers do you want to enter? ");
        int noOfPassengers = sc.nextInt();
        sc.nextLine();

        ArrayList<Booking> bookings = new ArrayList<>();

        for (int i = 0; i < noOfPassengers; i++) {
            System.out.println("\nEnter details for Passenger " + (i + 1));
            System.out.print("Enter passenger name: ");
            String passengername = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();

            System.out.print("Enter seat number to book: ");
            int seatsnumber = sc.nextInt();
            sc.nextLine(); // Consume the newline

            Booking booking = new Booking(seatsnumber, passengername, age);
            bookings.add(booking);
            booking.start();
        }

        try {
            Thread.sleep(1000); // Small delay to let all threads finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Booking.showDetails(); // Calling static method correctly

        sc.close();
    }
}
