import edu.macalester.graphics.Line;
import java.util.*;
import java.awt.*;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

public class ItemBox {
    private int x;
    private int y;
    private Product p;
    private GraphicsGroup box; 
    private Color BORDER_COLOR = new Color(32,150,10);


    public ItemBox(int x, int y, Product p){
        this.x = x;
        this.y = y;
        this.p = p;
        this.box = new GraphicsGroup();
        assemble();
    }

    public void assemble(){
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
    }

    public GraphicsGroup getBox() {
        return box;
    }
}
