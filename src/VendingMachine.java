import java.util.*;

public class VendingMachine {
    private InventoryManager inventory;

    /**
     * creates the inventory.
     */
    public VendingMachine(){
        this.inventory = new InventoryManager();
        inventory.addProduct(new Product("Kit Kat", 1.99, null, .5), 100);
        inventory.addProduct(new Product("Twix", 1.99, null, .6), 100);
        inventory.addProduct(new Product("Milky Way", 1.99, null, .4), 100);
        inventory.addProduct(new Product("Snickers", 1.99, null, .7), 100);
        inventory.addProduct(new Product("Chips Ahoy!", 2.99, null, .4), 100);
        inventory.addProduct(new Product("Skittles", 1.99, null, .6), 100);
        inventory.addProduct(new Product("Almond Joy", 1.99, null, .2), 100);
        inventory.addProduct(new Product("Reeses Pieces", 2.99, null, .5), 100);
        inventory.addProduct(new Product("Milk Duds", 2.99, null, .3), 100);
    }

    /**
     * getter for inventory
     * @return
     */
    public Map<Product, Integer> getInventory(){
        return inventory.getInventory();
    }
}
