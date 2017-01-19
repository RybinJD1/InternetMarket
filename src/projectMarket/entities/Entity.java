package projectMarket.entities;

/**
 * Created by AlexFisher on 11.01.2017.
 */
public class Entity {

    private long id;

    public Entity(long id) {
        this.id = id;
    }

    public Entity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
