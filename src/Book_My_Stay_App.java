import java.util.*;

// -------------------- INVENTORY --------------------
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increaseAvailability(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public void display() {
        System.out.println("\n--- Inventory ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }
}

// -------------------- CANCELLATION SERVICE --------------------
class CancellationService {

    private Stack<String> releasedRoomIds = new Stack<>();
    private Map<String, String> reservationRoomMap = new HashMap<>();

    // Register booking (simulate confirmed booking)
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomMap.put(reservationId, roomType);
        System.out.println("Booking Registered: " + reservationId);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomMap.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID");
            return;
        }

        String roomType = reservationRoomMap.get(reservationId);

        // Push to stack (rollback tracking)
        releasedRoomIds.push(reservationId);

        // Restore inventory
        inventory.increaseAvailability(roomType);

        // Remove booking
        reservationRoomMap.remove(reservationId);

        System.out.println("Cancelled: " + reservationId + " | " + roomType);
    }

    // Show rollback history (LIFO)
    public void showRollbackHistory() {
        System.out.println("\n--- Rollback History (LIFO) ---");
        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println(releasedRoomIds.get(i));
        }
    }
}

// -------------------- MAIN CLASS --------------------
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=== Booking Cancellation ===");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate confirmed bookings
        service.registerBooking("R1", "Single Room");
        service.registerBooking("R2", "Double Room");

        // Cancel bookings
        service.cancelBooking("R2", inventory);
        service.cancelBooking("R1", inventory);
        service.cancelBooking("R3", inventory); // invalid

        // Show rollback history
        service.showRollbackHistory();

        // Show updated inventory
        inventory.display();
    }
}