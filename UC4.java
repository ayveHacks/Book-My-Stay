import java.util.HashMap;
import java.util.Map;

class Room {
    String type;
    int beds;
    int size;
    double price;

    Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    void display(int available) {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

public class UC4 {
    public static void main(String[] args) {
        Room single = new Room("Single Room", 1, 250, 1500.0);
        Room dbl = new Room("Double Room", 2, 400, 2500.0);
        Room suite = new Room("Suite Room", 3, 750, 5000.0);

        RoomInventory inventory = new RoomInventory();

        System.out.println("Room Search\n");

        int singleAvail = inventory.getAvailability("Single Room");
        int doubleAvail = inventory.getAvailability("Double Room");
        int suiteAvail = inventory.getAvailability("Suite Room");

        if (singleAvail > 0) {
            single.display(singleAvail);
        }

        if (doubleAvail > 0) {
            dbl.display(doubleAvail);
        }

        if (suiteAvail > 0) {
            suite.display(suiteAvail);
        }
    }
}