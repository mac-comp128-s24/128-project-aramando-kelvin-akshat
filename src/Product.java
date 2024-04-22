public class Product {
    
    private String name;
    private Double price;

    public Product(String name, Double price){
        name = this.name;
        price = this.price;
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
