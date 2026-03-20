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

    synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    synchronized Reservation nextRequest() {
        return queue.poll();
    }

    synchronized boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    synchronized boolean allocate(String type) {
        int count = inventory.getOrDefault(type, 0);
        if (count > 0) {
            inventory.put(type, count - 1);
            return true;
        }
        return false;
    }

    void display() {
        System.out.println("Remaining Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}

class AllocationService {
    private Map<String, Integer> counters = new HashMap<>();

    synchronized String generateRoomId(String type) {
        int count = counters.getOrDefault(type, 0) + 1;
        counters.put(type, count);
        return type + "-" + count;
    }
}

class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private AllocationService allocationService;

    ConcurrentBookingProcessor(BookingRequestQueue queue, RoomInventory inventory, AllocationService allocationService) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    public void run() {
        while (true) {
            Reservation r;
            synchronized (queue) {
                if (!queue.hasRequests()) break;
                r = queue.nextRequest();
            }

            if (r != null) {
                synchronized (inventory) {
                    if (inventory.allocate(r.roomType)) {
                        String roomId = allocationService.generateRoomId(r.roomType);
                        System.out.println("Booking confirmed for Guest: " + r.guestName + ", Room ID: " + roomId);
                    }
                }
            }
        }
    }
}

public class UC11 {
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        RoomInventory inventory = new RoomInventory();
        AllocationService allocationService = new AllocationService();

        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        System.out.println();
        inventory.display();
    }
}