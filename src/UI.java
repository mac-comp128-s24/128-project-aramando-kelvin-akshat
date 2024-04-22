import java.util.*;
import java.awt.*;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.ui.TextField;

public class UI {
    private VendingMachine vendingMachine;
    private CanvasWindow canvas;
    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 800;
    private Color BORDER_COLOR = new Color(32,150,10);


    public UI(){
        vendingMachine = new VendingMachine();
        canvas = new CanvasWindow("Candy Shop", CANVAS_WIDTH, CANVAS_HEIGHT);
        createShop();
        addShopItems();
        transactionButton();
        canvas.draw();
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
            ItemBox box = new ItemBox(beginX,beginY,p);
            canvas.add(box.getBox());
            System.out.println(box.getBox().getX());
            beginX += 300;
            if (beginX + 300 > CANVAS_WIDTH){
                beginX = 100;
                beginY += 200;
            }
        }
    }

    private void userSignIn(){
        TextField usernameField = new TextField();
        canvas.add(usernameField, 100, 750); 
        Button signInButton = new Button("Sign In");
        signInButton.onClick(() -> {
            String username = usernameField.getText();
            System.out.println("User signed in: " + username);
            
        });
        canvas.add(signInButton, 250, 750);
    }
        
    private void transactionButton(){
        Button transactionBtn = new Button("Make Transaction");
        transactionBtn.onClick(() -> {
            // Assuming there is a method in VendingMachine to process transactions
            // if(vendingMachine.processTransaction()){
            //     System.out.println("Transaction successful!");
            // } else {
            //     System.out.println("Transaction failed. Please try again.");
            // }
        });
        canvas.add(transactionBtn, 500, 750);
    }

    public static void main(String[] args) {
        UI shop = new UI();
    }



}
