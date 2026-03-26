importimport java.util.LinkedList;
import java.util.Queue;

/**
 * Book_My_Stay_App - Use Case 5: Booking Request Queue (FIFO)

 */

// Reservation Class
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Booking Queue Class
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    // Add request
    public void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request Added: " + r.guestName);
    }

    // Display queue
    public void displayQueue() {
        System.out.println("\n--- Booking Requests (FIFO Order) ---");
        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App - Version 5.0 ");
        System.out.println("=======================================");

        BookingQueue bookingQueue = new BookingQueue();

        // Booking Requests
        bookingQueue.addRequest(new Reservation("Fahim", "Single Room"));
        bookingQueue.addRequest(new Reservation("Arun", "Double Room"));
        bookingQueue.addRequest(new Reservation("Sakthi", "Suite Room"));

        // Display Queue
        bookingQueue.displayQueue();
    }
}
