public class User {
    private int id;
    private double wallet;
    private Cart cart;

    public User(int id, double wallet, Cart cart){
        this.id = id;
        this.wallet = wallet;
        this.cart = cart;
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
}
