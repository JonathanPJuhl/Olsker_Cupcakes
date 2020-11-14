package businessLogic;

public class Bottom extends Cupcake {
    private final String bottom;

    public Bottom(int id, double price, String bottom) {
        super(id, price);
        this.bottom = bottom;
    }

    public String getBottom() {
        return bottom;
    }
}
