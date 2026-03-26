import java.util.*;

// Reservation Class
class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

// Booking History Class
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Report Service
class ReportService {

    public void generateReport(List<Reservation> history) {

        System.out.println("\n--- Booking Report ---");

        if (history.isEmpty()) {
            System.out.println("No bookings found");
            return;
        }

        int total = 0;

        for (Reservation r : history) {
            r.display();
            total++;
        }

        System.out.println("\nTotal Bookings: " + total);
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App ");
        System.out.println("=======================================");

        // Booking History
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addReservation(new Reservation("RES101", "Fahim", "Single Room"));
        history.addReservation(new Reservation("RES102", "Arun", "Double Room"));
        history.addReservation(new Reservation("RES103", "Sakthi", "Suite Room"));

        // Generate report
        ReportService report = new ReportService();
        report.generateReport(history.getAllReservations());
    }
}