abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void display() {
        System.out.println("Room Type : " + type);
        System.out.println("Beds : " + beds);
        System.out.println("Price : " + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 100.0);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 180.0);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 300.0);
    }
}

public class UC2 {
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        single.display();
        System.out.println("Available : " + singleAvailable);
        System.out.println();

        dbl.display();
        System.out.println("Available : " + doubleAvailable);
        System.out.println();

        suite.display();
        System.out.println("Available : " + suiteAvailable);
    }
}