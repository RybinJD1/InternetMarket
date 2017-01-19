package projectMarket.entities;


public class UsersReviews extends Entity {

    private Product product;
    private Buyer buyer;
    private String comment;
    private int mark;

    public UsersReviews(long id, Product product, Buyer buyer, String comment, int mark) {
        super(id);
        this.product = product;
        this.buyer = buyer;
        this.comment = comment;
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
