import java.util.HashMap;
import java.util.Map;

/**
 * Book_My_Stay_App - Use Case 3: Centralized Inventory
 *
 * @author Fahim
 * @version 3.0
 */

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("---- Room Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}


public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App - Version 3.0 ");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nAfter Booking Update:");

        inventory.displayInventory();
    }
}