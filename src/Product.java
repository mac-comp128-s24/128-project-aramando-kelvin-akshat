import edu.macalester.graphics.Image;

public class Product {
    
    private String name;
    private Double price;
    //Eventually each product will have an image associated with it, which we will display in UI.
    private Image image;
    private Double prob;
    

    public Product(String name, Double price, Image image, Double prob){
        this.name = name;
        this.price = price;
        this.image = image;
        this.prob = prob;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Double getProb() {
        return prob;
    }
}
