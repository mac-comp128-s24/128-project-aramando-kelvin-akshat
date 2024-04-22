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
        Line topBorder = new Line(x,y,x + 30, y);
        topBorder.setStrokeColor(BORDER_COLOR);
        box.add(topBorder);
        Line leftBorder = new Line(x,y,x,y+30);
        leftBorder.setStrokeColor(BORDER_COLOR);
        box.add(leftBorder);
        Line bottomBorder = new Line(x,y+30,x+30,y+30);
        bottomBorder.setStrokeColor(BORDER_COLOR);
        box.add(bottomBorder);
        Line rightBorder = new Line(x+30,y,x+30,y+30);
        rightBorder.setStrokeColor(BORDER_COLOR);
        GraphicsText name = new GraphicsText(p.getName(), x +5, y +5);
        name.setFontSize(5);
        box.add(name);
        GraphicsText price = new GraphicsText(p.getPrice().toString(), x +5, y +10);
        price.setFontSize(5);
        box.add(price);
    }

    public GraphicsGroup getBox() {
        return box;
    }
}
