import java.util.*;

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single", 5);
    }

    void increment(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

public class UC10 {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");

        String reservationId = "Single-1";
        String roomType = "Single";

        RoomInventory inventory = new RoomInventory();

        Stack<String> rollbackStack = new Stack<>();
        rollbackStack.push(reservationId);

        inventory.increment(roomType);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);

        System.out.println("\nRollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }

        System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailability(roomType));
    }
}
