import java.util.*;

public class Cart {
    private List<Product> items;

    public Cart(){
        this.items = new ArrayList<Product>();
    }

    public void addToCart(Product item){
        items.add(item);
    }

    public void removeFromCart(Product item){
        if (!items.remove(item)){
            System.out.println("Item not in cart.");
        }
    }
}
