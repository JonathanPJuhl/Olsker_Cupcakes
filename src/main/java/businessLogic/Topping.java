package businessLogic;

public class Topping extends Cupcake {
    private final String top;

    public Topping(int id, double price, String top) {
        super(id, price);
        this.top = top;
    }

    public String getTop() {
        return top;
    }
}
