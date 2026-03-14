import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    boolean hasPendingRequests() {
        return !queue.isEmpty();
    }

    Reservation nextRequest() {
        return queue.poll();
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Suite", 1);
    }

    boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

public class UC6 {
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");

        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        Map<String, Set<String>> allocated = new HashMap<>();

        while (queue.hasPendingRequests()) {
            Reservation r = queue.nextRequest();

            if (inventory.isAvailable(r.roomType)) {
                Set<String> rooms = allocated.getOrDefault(r.roomType, new HashSet<>());
                String roomId = r.roomType + "-" + (rooms.size() + 1);

                if (!rooms.contains(roomId)) {
                    rooms.add(roomId);
                    allocated.put(r.roomType, rooms);
                    inventory.decrement(r.roomType);

                    System.out.println("Booking confirmed for Guest: " + r.guestName + ", Room ID: " + roomId);
                }
            }
        }
    }
}