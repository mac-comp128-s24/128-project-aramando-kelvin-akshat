import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Integer> inventory;

    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    /** Method to add a product to the inventory with initial quantity 
     * **/
    public void addProduct(String productName, int initialQuantity) {
        if (inventory.containsKey(productName)) {
            System.out.println("Product '" + productName + "' already exists in the inventory.");
        } else {
            inventory.put(productName, initialQuantity);
            System.out.println("Product '" + productName + "' added to the inventory with quantity: " + initialQuantity);
        }
    }

    /** Method to restock a product in the inventory **/
    public void restockProduct(String productName, int quantity) {
        if (inventory.containsKey(productName)) {
            int currentQuantity = inventory.get(productName);
            inventory.put(productName, currentQuantity + quantity);
            System.out.println("Product '" + productName + "' restocked. New quantity: " + (currentQuantity + quantity));
        } else {
            System.out.println("Product '" + productName + "' does not exist in the inventory.");
        }
    }

    /** Method to remove a product from the inventory **/
    public void removeProduct(String productName) {
        if (inventory.containsKey(productName)) {
            inventory.remove(productName);
            System.out.println("Product '" + productName + "' removed from the inventory.");
        } else {
            System.out.println("Product '" + productName + "' does not exist in the inventory.");
        }
    }

    /** Method to update the quantity of a product in the inventory **/
    public void updateProductQuantity(String productName, int newQuantity) {
        if (inventory.containsKey(productName)) {
            inventory.put(productName, newQuantity);
            System.out.println("Quantity of product '" + productName + "' updated to: " + newQuantity);
        } else {
            System.out.println("Product '" + productName + "' does not exist in the inventory.");
        }
    }

    /** Method to get the current quantity of a product in the inventory **/
    public int getProductQuantity(String productName) {
        return inventory.getOrDefault(productName, 0);
    }

    /** Method to display the entire inventory **/
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
