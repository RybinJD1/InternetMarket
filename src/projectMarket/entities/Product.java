package projectMarket.entities;


public class Product extends Entity {

    private String name;
    private String description;
    private int cost;
    private int remainder;

    public Product(long id, String name, String description, int cost, int remainder) {
        super(id);
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.remainder = remainder;
    }

    public Product(long id, String name, int cost) {
        super(id);
        this.name = name;
        this.cost = cost;
    }

    public Product(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (cost != product.cost) return false;
        if (remainder != product.remainder) return false;
        if (!name.equals(product.name)) return false;
        return description.equals(product.description);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + cost;
        result = 31 * result + remainder;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }
}
