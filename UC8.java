import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {
    void generateReport(List<Reservation> history) {
        System.out.println("Booking History Report");
        for (Reservation r : history) {
            System.out.println("Guest: " + r.guestName + ", Room Type: " + r.roomType);
        }
    }
}

public class UC8 {
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService report = new BookingReportService();
        report.generateReport(history.getHistory());
    }
}