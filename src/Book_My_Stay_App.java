import java.util.*;

// Reservation Class
class Reservation {
    String reservationId;
    String guestName;

    Reservation(String reservationId, String guestName) {
        this.reservationId = reservationId;
        this.guestName = guestName;
    }
}

// Service Class
class Service {
    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Service Added: " + service.name + " for " + reservationId);
    }

    // Display services
    public void displayServices(String reservationId) {
        System.out.println("\nServices for Reservation: " + reservationId);

        List<Service> services = serviceMap.get(reservationId);

        if (services == null) {
            System.out.println("No services selected");
            return;
        }

        for (Service s : services) {
            System.out.println(s.name + " - ₹" + s.price);
        }
    }

    // Calculate total cost
    public double calculateTotal(String reservationId) {
        double total = 0;

        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.price;
            }
        }

        return total;
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App ");
        System.out.println("=======================================");

        // Sample Reservation
        Reservation r1 = new Reservation("RES101", "Fahim");

        // Service Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService("RES101", new Service("WiFi", 200));
        manager.addService("RES101", new Service("Breakfast", 300));
        manager.addService("RES101", new Service("Airport Pickup", 500));

        // Display services
        manager.displayServices("RES101");

        // Total cost
        double total = manager.calculateTotal("RES101");

        System.out.println("\nTotal Add-On Cost: ₹" + total);
    }
}