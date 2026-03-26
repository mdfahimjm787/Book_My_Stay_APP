import java.util.*;

// Reservation Class
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Class
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void display() {
        System.out.println("\n--- Updated Inventory ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void processBookings() {

        System.out.println("\n--- Processing Bookings ---");

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            if (inventory.getAvailability(r.roomType) > 0) {

                String roomId = r.roomType.substring(0, 2).toUpperCase()
                        + new Random().nextInt(1000);

                allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

                while (allocatedRooms.get(r.roomType).contains(roomId)) {
                    roomId = r.roomType.substring(0, 2).toUpperCase()
                            + new Random().nextInt(1000);
                }

                allocatedRooms.get(r.roomType).add(roomId);

                inventory.reduceAvailability(r.roomType);

                System.out.println("Booking Confirmed: " + r.guestName +
                        " | Room Type: " + r.roomType +
                        " | Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed (No Availability): " + r.guestName);
            }
        }
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App ");
        System.out.println("=======================================");

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Fahim", "Single Room"));
        queue.add(new Reservation("Arun", "Single Room"));
        queue.add(new Reservation("Sakthi", "Single Room"));
        queue.add(new Reservation("John", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService service = new BookingService(queue, inventory);

        service.processBookings();

        inventory.display();
    }
}