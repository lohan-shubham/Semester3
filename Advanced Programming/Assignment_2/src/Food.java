public class Food {
    private int ID;
    private String name;
    private int quantity;
    private float price;
    private  String category;
    private float offer;
    private float total_food_price;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getOffer() {
        return offer;
    }

    public void setOffer(float offer) {
        this.offer = offer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Food(int ID, String name, int quantity, float price, String category, float offer) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.offer = offer;
    }
    public float getTotal_food_price() {
        return total_food_price;
    }

    public void setTotal_food_price(float total_food_price) {
        this.total_food_price = total_food_price;
    }
}
