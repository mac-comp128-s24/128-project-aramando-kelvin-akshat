import java.util.*;

public class VendingMachine {
    private InventoryManager inventory;
    private User currentUser;

    public VendingMachine(){
        this.inventory = new InventoryManager();
        currentUser = null;
        inventory.addProduct(new Product("Kit Kat", 1.99), 100);
        inventory.addProduct(new Product("Twix", 1.99), 100);
        inventory.addProduct(new Product("Milky Way", 1.99), 100);
        inventory.addProduct(new Product("Snickers", 1.99), 100);
        inventory.addProduct(new Product("Chips Ahoy!", 2.99), 100);
        inventory.addProduct(new Product("Skittles", 1.99), 100);
        inventory.addProduct(new Product("Almond Joy", 1.99), 100);
        inventory.addProduct(new Product("Reeses Pieces", 2.99), 100);
        inventory.addProduct(new Product("Milk Duds", 2.99), 100);
    }

    public Map<Product, Integer> getInventory(){
        return inventory.getInventory();
    }
    public User getUser() {
        return currentUser;
    }
    public void setUser(User user) {
        this.currentUser = user;
    }
}
