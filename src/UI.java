
import java.util.*;
import java.util.List;
import java.awt.*;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.TextField;

public class UI {
    private User user;  
    private Cart cart;  
    private VendingMachine vendingMachine;
    private CanvasWindow canvas;
    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 800;
    private Color BORDER_COLOR = new Color(32,150,10);
    private GraphicsText walletDisplay;
    private InventoryManager im = new InventoryManager();
    private Button signOutButton;
    private Map<String, List<Product>> userPurchases;
    
    /**
     * A shopping app, where users can purchase items. The app also has a simulation of different items, showing how fast they get bought. 
     */
    public UI(){
        userPurchases = new HashMap<>();
        walletDisplay = new GraphicsText();
        vendingMachine = new VendingMachine();
        canvas = new CanvasWindow("Candy Shop", CANVAS_WIDTH, CANVAS_HEIGHT);
        createShop();
        addShopItems();
        userSignIn();  
        canvas.draw();  
        initializeUI();
        showSim();
    }

    /**
     * formatting helper class. 
     * @param amount
     * @return
     */
    private String formatAmount(double amount) {
        return String.format("%.2f", amount); // Formatting to two decimal places
    }

    /**
     * Creates the shop border.
     */
    private void createShop(){
        Rectangle topBorder = new Rectangle(0, 0,CANVAS_WIDTH , 50);
        topBorder.setFillColor(BORDER_COLOR);
        canvas.add(topBorder);
        Rectangle leftBorder = new Rectangle(0,0,50,CANVAS_HEIGHT);
        leftBorder.setFillColor(BORDER_COLOR);
        canvas.add(leftBorder);
        Rectangle bottomBorder = new Rectangle(0,CANVAS_HEIGHT - 50,CANVAS_WIDTH,50);
        bottomBorder.setFillColor(BORDER_COLOR);
        canvas.add(bottomBorder);
        Rectangle rightBorder = new Rectangle(CANVAS_WIDTH - 50,0,50,CANVAS_HEIGHT);
        rightBorder.setFillColor(BORDER_COLOR);
        canvas.add(rightBorder);

    }

    /**
     * Creates the grid like display for the shop. Adds the items.
     */
    private void addShopItems(){
        Map<Product, Integer> inventory = vendingMachine.getInventory();
        int beginX = 100;
        int beginY = 100;
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product p = entry.getKey();
            ItemBox box = new ItemBox(beginX,beginY,p,this);
            canvas.add(box.getBox());
            System.out.println(box.getBox().getX());
            beginX += 300;
            if (beginX + 300 > CANVAS_WIDTH){
                beginX = 100;
                beginY += 200;
            }
        }
    }

    /**
     * Add the product to the users cart.
     * @param p
     */
    public void addItemToCart(Product p) {
        cart.addToCart(p);
    }
    
    /**
     * Handles UI updates and user sign in when this happens.
     */
    private void userSignIn(){
        TextField usernameField = new TextField();
        usernameField.setPosition(100, 750); 
        usernameField.setScale(150, 30); 
        canvas.add(usernameField);

        Button signInButton = new Button("Sign In");
        signInButton.setPosition(260, 750);
        signInButton.onClick(() -> {
            String username = usernameField.getText();
            System.out.println("User signed in: " + username);
            if (!username.isEmpty()) { // Assuming non-empty username means successful sign-in
                user = new User(username.hashCode(), 100.00, new Cart(),true);  // Create a new User instance
                updateWalletDisplay();  
                canvas.remove(usernameField);  // Remove username field
                canvas.remove(signInButton); 
                transactionButton();  // Add transaction button after sign-in
                handleAddFunds();
                userPurchases.put(username, new ArrayList<>());
            }
        });
        canvas.add(signInButton);
    }

    /**
     * Updates the UI if the user is logged in.
     */
    private void updateUIForUser() {
        if (user != null && user.isLoggedIn()) {
            // Only show the sign out button if the user is logged in
            signOutButton = new Button("Sign Out");
            signOutButton.onClick(() -> signOutUser());
            canvas.add(signOutButton, 10, 10);
        } else {
            canvas.remove(signOutButton); // Remove the sign out button if the user is not logged in
        }
    }

    /**
     * Signs out the user.
     */
    private void signOutUser() {
        userPurchases.get(getUser()).addAll(cart.getItems());
        if (user != null) {
            user.signOut(); // Assuming User class handles resetting the wallet and clearing the cart
            canvas.remove(signOutButton); // Optionally remove the button immediately on sign out
            System.out.println("User signed out. Wallet and cart are reset.");
            updateUIForUser(); // Refresh UI elements to reflect the logged-out state
        }
    }

    // Initialization or re-initialization of the UI
    private void initializeUI() {
        signOutButton = new Button("Sign Out");
        signOutButton.onClick(() -> signOutUser());
        canvas.add(signOutButton,10,10);  
    }

    /**
     * Updates the wallet display in the top left, showing the amount of money and the amount in the cart. 
     */
    public void updateWalletDisplay() {
        walletDisplay.setFont(FontStyle.BOLD, 20);
        if (user != null) {
            walletDisplay.setText("Wallet: $" + formatAmount(user.getWallet()) + " | Items in Cart: " + user.getCart().getItems().size());
        } else {
            walletDisplay.setText("Wallet: $0.00");
        }
        walletDisplay.setPosition(820, 50);  // Adjusted position on the canvas
        canvas.add(walletDisplay);
    }

    /**
     * Button that initiates a transaction.
     */
    private void transactionButton(){
        Button transactionBtn = new Button("Make Transaction");
        transactionBtn.setPosition(400, 750);
        transactionBtn.onClick(() -> {
            Transaction transaction = new Transaction(user, user.getCart(), im);
            transaction.processTransaction();
            updateWalletDisplay();
            System.out.println("Transaction initiated");
        });
        canvas.add(transactionBtn);
    }

    /**
     * Button and Textfields that ensures the user can add funds, and updates the wallet when done.
     */
    private void handleAddFunds(){
        TextField addFunds = new TextField();
        addFunds.setPosition(550, 750); // Ensure positioning is correct
        addFunds.setScale(150, 30); // Set size to make sure it's visible
        canvas.add(addFunds);
        Button submitFunds = new Button("Add Funds");
        submitFunds.setPosition(650, 750);
        submitFunds.onClick(() -> {
            String num = addFunds.getText();
            if (!num.isEmpty()) { 
                user.addFunds(Double.parseDouble(num));
                addFunds.setText("");
                updateWalletDisplay();
            }
        });
        canvas.add(submitFunds);
    }
    
    /**
     * Getter for user
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /*
     * Shows the simulation window when the button is clicked.
     */
    private void showSim(){
        Button simButton = new Button("Simulate");
        canvas.add(simButton);
        simButton.setPosition(1100, 770);
        simButton.onClick(() -> {
            new Simulation(vendingMachine);
        });
    }

    public static void main(String[] args) {
        // VendingMachine vendingMachine = new VendingMachine();
        // Cart cart = new Cart(); 
        // User user = new User(1, 100.00, cart);
        new UI();
       
    }



}
