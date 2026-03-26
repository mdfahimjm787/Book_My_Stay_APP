import java.util.*;

// -------------------- RESERVATION --------------------
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// -------------------- INVENTORY --------------------
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void display() {
        System.out.println("\n--- Final Inventory ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// -------------------- QUEUE --------------------
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void add(Reservation r) {
        queue.add(r);
    }

    public Reservation poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// -------------------- ALLOCATION --------------------
class RoomAllocationService {

    public void allocateRoom(Reservation r, RoomInventory inventory) {

        if (inventory.getAvailability(r.roomType) > 0) {
            inventory.reduceAvailability(r.roomType);

            System.out.println(Thread.currentThread().getName() +
                    " booked for " + r.guestName +
                    " (" + r.roomType + ")");
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed for " + r.guestName);
        }
    }
}

// -------------------- THREAD PROCESSOR --------------------
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private RoomAllocationService service;

    public ConcurrentBookingProcessor(
            BookingRequestQueue queue,
            RoomInventory inventory,
            RoomAllocationService service) {

        this.queue = queue;
        this.inventory = inventory;
        this.service = service;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // Critical section 1 (queue access)
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.poll();
            }

            // Critical section 2 (inventory update)
            synchronized (inventory) {
                service.allocateRoom(r, inventory);
            }
        }
    }
}

// -------------------- MAIN CLASS --------------------
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=== Concurrent Booking Simulation ===");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Add booking requests
        queue.add(new Reservation("Fahim", "Single Room"));
        queue.add(new Reservation("Arun", "Single Room"));
        queue.add(new Reservation("Sakthi", "Single Room"));
        queue.add(new Reservation("John", "Double Room"));

        // Create threads
        Thread t1 = new Thread(new ConcurrentBookingProcessor(queue, inventory, service));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(queue, inventory, service));

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        inventory.display();
    }
}