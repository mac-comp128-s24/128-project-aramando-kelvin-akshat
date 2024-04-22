import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<Product, Integer> inventory;

    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    /** Method to add a product to the inventory with initial quantity 
     * **/
    public void addProduct(Product product, int initialQuantity) {
        if (inventory.containsKey(product)) {
            System.out.println("Product '" + product.getName() + "' already exists in the inventory.");
        } else {
            inventory.put(product, initialQuantity);
            System.out.println("Product '" + product.getName() + "' added to the inventory with quantity: " + initialQuantity);
        }
    }

    /** Method to restock a product in the inventory **/
    public void restockProduct(Product product, int quantity) {
        if (inventory.containsKey(product)) {
            int currentQuantity = inventory.get(product);
            inventory.put(product, currentQuantity + quantity);
            System.out.println("Product '" + product.getName() + "' restocked. New quantity: " + (currentQuantity + quantity));
        } else {
            System.out.println("Product '" + product.getName() + "' does not exist in the inventory.");
        }
    }

    /** Method to remove a product from the inventory **/
    public void removeProduct(Product product) {
        if (inventory.containsKey(product)) {
            inventory.remove(product);
            System.out.println("Product '" + product.getName() + "' removed from the inventory.");
        } else {
            System.out.println("Product '" + product.getName() + "' does not exist in the inventory.");
        }
    }

    /** Method to update the quantity of a product in the inventory **/
    public void updateProductQuantity(Product product, int newQuantity) {
        if (inventory.containsKey(product)) {
            inventory.put(product, newQuantity);
            System.out.println("Quantity of product '" + product.getName() + "' updated to: " + newQuantity);
        } else {
            System.out.println("Product '" + product.getName() + "' does not exist in the inventory.");
        }
    }

    /** Method to get the current quantity of a product in the inventory **/
    public int getProductQuantity(String productName) {
        return inventory.getOrDefault(productName, 0);
    }

    /** Method to display the entire inventory **/
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }
}
