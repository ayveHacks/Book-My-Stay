import java.io.*;
import java.util.*;

public class UC12{
    public static void main(String[] args) {
        System.out.println("System Recovery");

        String fileName = "inventory.dat";
        Map<String, Integer> inventory = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            inventory = (Map<String, Integer>) ois.readObject();
            System.out.println("Inventory loaded successfully.");
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }
}