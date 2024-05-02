public class User {
    private int id;
    private double wallet=1000.00;
    private Cart cart;
    private VendingMachine vendingMachine; 
    private boolean isLoggedIn;

    public User(int id, double wallet, Cart cart, boolean isLoggedIn){
        this.id = id;
        this.wallet = wallet;
        this.cart = cart;
        this.vendingMachine = vendingMachine; 
        this.isLoggedIn=isLoggedIn;
    }

    public Cart getCart() {
        return cart;
    }

    public int getId() {
        return id;
    }

 
    public double getWallet() {
        return wallet;
    }

    public void addFunds(double funds){
        wallet += funds;
    }

    public boolean removeFunds(double funds){
        if (getWallet() - funds >= 0){
            wallet -= funds;
            return true;
        }
        else{
            return false;
        }
    }
    public VendingMachine getVendingMachine() {  
        return vendingMachine;
    }
   
    public User() {
        this.isLoggedIn = false;
    }

    public void signIn() {
        this.isLoggedIn = true;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    public void signOut() {
        // Reset user details and logout
        this.isLoggedIn = false;
        this.wallet = 100; 
        this.cart.clearCart();  // Assuming the Cart class has a method to clear the cart
        System.out.println("User has been signed out.");
    }
}

