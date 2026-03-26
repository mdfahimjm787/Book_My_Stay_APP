import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

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
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }

    public boolean isValidRoom(String type) {
        return inventory.containsKey(type);
    }

    public void reduceAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// Validator Class
class ReservationValidator {

    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isValidRoom(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No availability for: " + roomType);
        }
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=== Booking Validation ===");

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        List<Reservation> requests = Arrays.asList(
                new Reservation("Fahim", "Single Room"),
                new Reservation("Arun", "Suite Room"),      // no availability
                new Reservation("", "Double Room"),          // invalid name
                new Reservation("Sakthi", "Luxury Room")     // invalid type
        );

        for (Reservation r : requests) {
            try {
                validator.validate(r.guestName, r.roomType, inventory);

                inventory.reduceAvailability(r.roomType);

                System.out.println("Booking Successful: " + r.guestName +
                        " | " + r.roomType);

            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }
}