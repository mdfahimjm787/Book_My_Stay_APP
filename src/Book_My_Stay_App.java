/**
 * UseCase2RoomInitialization - Demonstrates Room Types & Static Availability
 *
 * @author Fahim
 * @version 2.0
 */

// Abstract Class
abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    abstract void displayRoomDetails();
}

// Single Room
class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 1000);
    }

    void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Double Room
class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 2000);
    }

    void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Suite Room
class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 5000);
    }

    void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Main Class
public class Book_My_Stay_App {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Book My Stay App - Version 2.0 ");
        System.out.println("=======================================");

        // Room Objects
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Static Availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display Details
        r1.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);

        r2.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);

        r3.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}