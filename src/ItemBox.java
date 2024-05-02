import edu.macalester.graphics.Line;
import edu.macalester.graphics.ui.Button;
import java.awt.*;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

public class ItemBox {
    private Button addButton;
    private int x;
    private int y;
    private Product p;
    private GraphicsGroup box; 
    private Color BORDER_COLOR = new Color(32,150,10);

    /**
     * Creates one square of the shopping grid. Helper class.
     * @param x
     * @param y
     * @param p
     * @param ui
     */
    public ItemBox(int x, int y, Product p,UI ui){
        this.x = x;
        this.y = y;
        this.p = p;
        this.box = new GraphicsGroup();
        assemble(ui);
        
    }

    /**
     * Assembles the square. Includes price, name, and a button to buy it.
     * @param ui
     */
    public void assemble(UI ui){
        Line topBorder = new Line(x,y,x + 300, y);
        topBorder.setStrokeColor(BORDER_COLOR);
        box.add(topBorder);
        Line leftBorder = new Line(x,y,x,y+200);
        leftBorder.setStrokeColor(BORDER_COLOR);
        box.add(leftBorder);
        Line bottomBorder = new Line(x,y+200,x+300,y+200);
        bottomBorder.setStrokeColor(BORDER_COLOR);
        box.add(bottomBorder);
        Line rightBorder = new Line(x+300,y,x+300,y+200);
        rightBorder.setStrokeColor(BORDER_COLOR);
        box.add(rightBorder);
        GraphicsText name = new GraphicsText(p.getName(), x +50, y +50);
        name.setFontSize(20);
        box.add(name);
        GraphicsText price = new GraphicsText(p.getPrice().toString(), x +50, y +100);
        price.setFontSize(20);
        box.add(price);
        addButton = new Button("Add to Cart");
        addButton.setPosition(x + 50, y + 150);
        box.add(addButton);
        addButton.onClick(() -> {
            ui.getUser().getCart().addToCart(p);
            ui.updateWalletDisplay();
        });
    }

    /**
     * getter for the box.
     * @return
     */
    public GraphicsGroup getBox() {
        return box;
    }
}
