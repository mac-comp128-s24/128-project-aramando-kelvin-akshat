import java.util.*;
import java.awt.*;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Line;

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
        int beginX = 200;
        int beginY = 200;
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product p = entry.getKey();
            ItemBox box = new ItemBox(beginX,beginY,p);
            canvas.add(box.getBox());
            System.out.println(box.getBox().getX());
            beginX += 20;
        }
    }

    private void userSignIn(){

    }

    private void transactionButton(){

    }

    public static void main(String[] args) {
        UI shop = new UI();
    }



}
