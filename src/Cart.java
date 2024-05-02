import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;
    private InventoryManager inventoryManager;

    public Cart(){
        this.items = new ArrayList<Product>();
    }

    public void addToCart(Product item){
        items.add(item);
        System.out.println("item added: " + item.getName());
    }

    public void removeFromCart(Product item){
        if (!items.remove(item)){
            // If the item is not in the cart, return it to the inventory
            // InventoryManager inventoryManager = getInventoryManager();
            // if (inventoryManager != null) {
            //     int quantity = inventoryManager.getProductQuantity(item.getName());
            //     inventoryManager.updateProductQuantity(item.getName(), quantity + 1);
            // }
            System.out.println("Item not in cart.");
        }
    }

    // private InventoryManager getInventoryManager() {
    //     if (inventoryManager == null) {
    //         inventoryManager = new InventoryManager(); // Initialize it when needed
    //     }
    //     return inventoryManager;
    // }

    public double cartCost(){
        double total = 0;
        for (Product item : items){
            total += item.getPrice();
        }
        System.out.println("Total cost: " + total);
        return total;
    }

    /* Added after the last push by Kyle
     * Method to display the items in the cart
     
     */
    public List<Product> getItems() {
        return items;
    }  

    /*  
     * Method to check if the cart is empty
     */
    public void isEmpty(){
        if (items.isEmpty()){
            System.out.println("Cart is empty.");
        }
    } 
}
