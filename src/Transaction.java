public class Transaction {
    private User user;
    private Cart cart;
    private InventoryManager inventoryManager;

    public Transaction(User user, Cart cart, InventoryManager inventoryManager) {
        this.user = user;
        this.cart = cart;
        this.inventoryManager = inventoryManager;
    }
    
    

    public void processTransaction() {
        // Check if the user's cart is empty
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add items before proceeding with the transaction.");
            return;
        }
        // Calculate the total cost of items in the cart
        double totalCost = (double) cart.cartCost();
        // Check if the user has enough funds to complete the transaction
        if (user.getWallet() < totalCost) {
            System.out.println("Insufficient funds. Please add funds to your wallet.");
            return;
        }
        // Deduct the total cost from the user's wallet
        user.removeFunds(totalCost);
        // Clear the cart
        cart.getItems().clear();
        System.out.println("Transaction completed successfully.");
    }
}
