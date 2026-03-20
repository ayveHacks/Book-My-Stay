import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
    }

    boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }
}

class ReservationValidator {
    void validate(String guestName, String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (!inventory.isAvailable(roomType)) {
            throw new InvalidBookingException("Selected room is not available.");
        }
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }
}

public class UC9 {
    public static void main(String[] args) {
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String type = scanner.nextLine();

            validator.validate(name, type, inventory);

            Reservation r = new Reservation(name, type);
            bookingQueue.addRequest(r);

            System.out.println("Booking request accepted.");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
