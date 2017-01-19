package projectMarket.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Orders extends Entity{

    private int quantityOfProducts;
    private String productsInBooking;
    private Status status;
    private LocalDate registrationDate;
    private LocalDate closingDate;
    private Buyer buyer;
    private Map<Product, Integer> quantityOfProduct = new HashMap<>();

    public Orders(int id, int quantityOfProducts, String productsInBooking, Status status, LocalDate registrationDate,
                  LocalDate closingDate, Buyer buyer) {
        super(id);
        this.quantityOfProducts = quantityOfProducts;
        this.productsInBooking = productsInBooking;
        this.status = status;
        this.registrationDate = registrationDate;
        this.closingDate = closingDate;
        this.buyer = buyer;
    }

    public Orders(LocalDate registrationDate, Status status) {
        this.registrationDate = registrationDate;
        this.status = status;
    }


    public void addProduct(Product p, Integer i) {
        quantityOfProduct.put(p, i);
    }


    public int getQuantityOfProducts() {
        return quantityOfProducts;
    }

    public void setQuantityOfProducts(int quantityOfProducts) {
        this.quantityOfProducts = quantityOfProducts;
    }

    public String getProductsInBooking() {
        return productsInBooking;
    }

    public void setProductsInBooking(String productsInBooking) {
        this.productsInBooking = productsInBooking;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }


}
