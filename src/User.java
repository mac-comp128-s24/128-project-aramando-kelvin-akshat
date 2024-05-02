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
        this.isLoggedIn=isLoggedIn;
    }

    /**
     * getter for cart.
     * @return
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * getter for userId
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * getter for wallet.
     * @return
     */
    public double getWallet() {
        return wallet;
    }

    /**
     * Updates wallet with amount of funds user wants to add.
     * @param funds
     */
    public void addFunds(double funds){
        wallet += funds;
    }

    /**
     * Removes funds if there are enough to be removed.
     * @param funds
     * @return
     */
    public boolean removeFunds(double funds){
        if (getWallet() - funds >= 0){
            wallet -= funds;
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Getter for the vending machine.
     * @return
     */
    public VendingMachine getVendingMachine() {  
        return vendingMachine;
    }
   
    /**
     * User is default not logged in.
     */
    public User() {
        this.isLoggedIn = false;
    }

    /**
     * updates isloggedin once signed in.
     */
    public void signIn() {
        this.isLoggedIn = true;
    }

    /**
     * getter for isLoggedIn.
     * @return
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    /**
     * Signs out the user.
     */
    public void signOut() {
        // Reset user details and logout
        this.isLoggedIn = false;
        this.wallet = 100; 
        this.cart.clearCart();  // Assuming the Cart class has a method to clear the cart
        System.out.println("User has been signed out.");
    }
}

