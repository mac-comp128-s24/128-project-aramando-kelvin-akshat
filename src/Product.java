import edu.macalester.graphics.Image;

public class Product {
    
    private String name;
    private Double price;
    //Eventually each product will have an image associated with it, which we will display in UI.
    private Image image;
    

    public Product(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
}
