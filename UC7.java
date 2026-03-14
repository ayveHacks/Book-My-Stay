import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> services = new HashMap<>();

    void addService(String reservationId, Service service) {
        List<Service> list = services.getOrDefault(reservationId, new ArrayList<>());
        list.add(service);
        services.put(reservationId, list);
    }

    double getTotalCost(String reservationId) {
        double total = 0;
        List<Service> list = services.getOrDefault(reservationId, new ArrayList<>());
        for (Service s : list) {
            total += s.cost;
        }
        return total;
    }
}

public class UC7 {
    public static void main(String[] args) {
        System.out.println("Add-On Service Selection");

        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId, new Service("Breakfast", 500.0));
        manager.addService(reservationId, new Service("Airport Pickup", 1000.0));

        double totalCost = manager.getTotalCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}