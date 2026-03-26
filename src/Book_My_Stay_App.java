import java.util.HashMap;
import java.util.Map;

/**
 * Book_My_Stay_App - Use Case 4: Room Search & Availability
 *
 * @author Fahim
 * @version 4.0
 */

// Room Class
class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void display() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Inventory Class
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App - Version 4.0 ");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();

        // Room Objects
        Room single = new Room("Single Room", 1, 1000);
        Room doubleRoom = new Room("Double Room", 2, 2000);
        Room suite = new Room("Suite Room", 3, 5000);

        System.out.println("\nAvailable Rooms:\n");

        // Search Logic (READ-ONLY)
        if (inventory.getAvailability("Single Room") > 0) {
            single.display();
            System.out.println("Available: " + inventory.getAvailability("Single Room"));
        }

        if (inventory.getAvailability("Double Room") > 0) {
            doubleRoom.display();
            System.out.println("Available: " + inventory.getAvailability("Double Room"));
        }

        if (inventory.getAvailability("Suite Room") > 0) {
            suite.display();
            System.out.println("Available: " + inventory.getAvailability("Suite Room"));
        }
    }
}