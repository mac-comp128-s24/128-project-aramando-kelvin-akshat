import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart(){
        this.items = new ArrayList<Product>();
    }

    /**
     * Adds item to cart.
     * @param item
     */
    public void addToCart(Product item){
        items.add(item);
        System.out.println("item added: " + item.getName());
    }

    /**
     * removes item from cart.
     * @param item
     */
    public void removeFromCart(Product item){
        if (!items.remove(item)){
            System.out.println("Item not in cart.");
        }
    }
    /**
     * clears cart.
     */
    public void clearCart() {
        items.clear();  // Clear all items from the cart
        System.out.println("All items have been removed from the cart.");
    }

    /**
     * calculates total cart cost.
     * @return
     */
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
