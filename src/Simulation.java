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
import java.lang.Math;


public class Simulation {
    private CanvasWindow canvas;
    private VendingMachine machine;
    private Map<Product, Double> positions;
    private Map<Product, Color> colorAssign;
    private ArrayList<Color> colors;
    private Map<Product, Integer> inventory;

    
    /**
     * Runs all helper methods to create a simulation.
     * @param machine gets products.
     */
    public Simulation(VendingMachine machine){
        canvas = new CanvasWindow("Simulation", 1200, 800);
        this.machine = machine;
        positions = new HashMap<>();
        colorAssign = new HashMap<>();
        colors = new ArrayList<Color>();
        inventory = machine.getInventory();
        setupSim();
        simButton();
    }


    /**
     * fills the positions map. Each product has a position on the graph.
     */
    private void fillPositions(){
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product p = entry.getKey();
            positions.put(p, 20.0);
        }
    }

    /**
     * Sets up the color map. A color is assigned to each Product.
     */
    private void fillColors(){
        colors.add(new Color(227,52,47));
        colors.add(new Color(255,237,74));
        colors.add(new Color(246,153,63));
        colors.add(new Color(56,193,114));
        colors.add(new Color(77,192,181));
        colors.add(new Color(52,144,220));
        colors.add(new Color(101,116,205));
        colors.add(new Color(149,97,226));
        colors.add(new Color(246,109,155));

        int index = 0;
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product p = entry.getKey();
            colorAssign.put(p, colors.get(index));
            index ++;
        }
    }

    /**
     * This sets up the graph. On click, the graph and the legend will appear.
     */
    private void setupSim(){
        fillColors();
        fillPositions();
        Button sim = new Button("Click to Setup");
        sim.setPosition(500,100);
        canvas.add(sim);
        sim.onClick(() -> {
            Line yAxis = new Line(20,20,20,780);
            Line xAxis = new Line(20,780,1180,780);
            canvas.add(yAxis);
            canvas.add(xAxis);
            canvas.remove(sim);
            makeLegend();
        });
    }

    /**
     * When this button is clicked, the simulation will begin. The code creates lines for each product, and randomly determines
     * if the item will be bought. If it does, it decreases the quantity, and reflects this in the chart.
     */
    private void simButton(){
        Button sim = new Button("Click to Start");
        sim.setPosition(100,100);
        canvas.add(sim);
        double yDown = 760/100;
        sim.onClick(() -> {
            double x1 = 20;
            while (x1 < 1180){
                for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
                    Product p = entry.getKey();
                    double randNum = Math.random();
                    Double pos = getPositions().get(p);
                    if (pos > 780 - yDown){
                        break;
                    }
                    if (randNum <= p.getProb()){
                        Line line = new Line(x1, pos, x1+3, pos + yDown);
                        line.setStrokeColor(colorAssign.get(p));
                        canvas.add(line);
                        positions.put(p, pos + yDown);
                    }
                    else{
                        Line line = new Line(x1, pos, x1+3, pos);
                        line.setStrokeColor(colorAssign.get(p));
                        canvas.add(line);
                    }
                    
                    
                    canvas.draw();
                }
                x1 = x1 + 3;
            }
        });
    }

    /**
     * getter for positions, used to draw lines.
     * @return
     */
    private Map<Product, Double> getPositions() {
        return positions;
    }

    /**
     * Creates the legend.
     */
    private void makeLegend(){
        int index = 0;
        int ystart = 300;
        for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            Product p = entry.getKey();
            GraphicsText text = new GraphicsText(p.getName(), 40, ystart-2);
            Rectangle rect = new Rectangle(40, ystart, 80, 40);
            rect.setFillColor(colors.get(index));
            canvas.add(text);
            canvas.add(rect);
            ystart += 55;
            index ++;
        }
    }

    public static void main(String[] args) {
        new Simulation(new VendingMachine());
    }
}
