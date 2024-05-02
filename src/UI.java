

import java.util.*;
import java.awt.*;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Line;
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
    


    public UI(){
        walletDisplay = new GraphicsText();
        vendingMachine = new VendingMachine();
        this.user=user;
        this.cart=cart;
        canvas = new CanvasWindow("Candy Shop", CANVAS_WIDTH, CANVAS_HEIGHT);
        createShop();
        addShopItems();
        userSignIn();
        canvas.draw();
        handleAddFunds();
        
    }

    private String formatAmount(double amount) {
        return String.format("%.2f", amount); // Formatting to two decimal places
    }

    

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
    public void addItemToCart(Product p) {
        cart.addToCart(p);
    }
    
    private void userSignIn(){
        TextField usernameField = new TextField();
        usernameField.setPosition(100, 750); // Ensure positioning is correct
        usernameField.setScale(150, 30); // Set size to make sure it's visible
        canvas.add(usernameField);

        Button signInButton = new Button("Sign In");
        signInButton.setPosition(260, 750);
        signInButton.onClick(() -> {
            String username = usernameField.getText();
            System.out.println("User signed in: " + username);
            if (!username.isEmpty()) { // Assuming non-empty username means successful sign-in
                user = new User(username.hashCode(), 100.00, new Cart());  // Create a new User instance
                updateWalletDisplay();  
                canvas.remove(usernameField);  // Remove username field
                canvas.remove(signInButton); 
                transactionButton();  // Add transaction button after sign-in
            }
        });
        canvas.add(signInButton);
    }

    public void updateWalletDisplay() {
        walletDisplay.setFont(FontStyle.BOLD, 20);
        if (user != null) {
            walletDisplay.setText("Wallet: $" + formatAmount(user.getWallet()) + " | Items in Cart: " + user.getCart().getItems().size());
            System.out.println("Updated Cart");
            System.out.println(user.getCart().getItems().size());
        } else {
            walletDisplay.setText("Wallet: $0.00");
        }
    
        walletDisplay.setPosition(820, 50);  // Adjusted position on the canvas
        canvas.add(walletDisplay);
    }
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

    private void handleAddFunds(){
        TextField addFunds = new TextField();
        addFunds.setPosition(550, 750); // Ensure positioning is correct
        addFunds.setScale(150, 30); // Set size to make sure it's visible
        canvas.add(addFunds);

        Button submitFunds = new Button("Add Funds");
        submitFunds.setPosition(650, 750);
        submitFunds.onClick(() -> {
            String num = addFunds.getText();
            // System.out.println("User signed in: " + username);
            if (!num.isEmpty()) { // Assuming non-empty username means successful sign-in
                user.addFunds(Double.parseDouble(num));
                addFunds.setText("");
                updateWalletDisplay();
            }
        });
        canvas.add(submitFunds);
    }
    
    public User getUser() {
        return user;
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Cart cart = new Cart(); 
        // User user = new User(1, 100.00, cart);
        new UI();
       
    }



}
