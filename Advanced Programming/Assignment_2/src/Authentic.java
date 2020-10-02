class Authentic extends Restaurant implements Discount{
    private int setDiscount;
    public Authentic(String name, int no_of_order, int reward, String category) {
        super(name, no_of_order, reward, category);
    }

    @Override
    public void calcDiscount() {
        for(int i=0;i<getFood().size();i++) {
            getRestaurants().get(i).setNo_of_order(getRestaurants().get(i).getNo_of_order()); //inc no of order in restaurant
            Main.zotato.getRestaurants().get(i).getFood().get(i).setQuantity(getRestaurants().get(i).getFood().get(i).getQuantity()); //decrease quantity of selected food
            Food fd = getRestaurants().get(i).getFood().get(i);
            fd.setQuantity(setDiscount);
            String restaurant_buy = getRestaurants().get(i).getName();
            fd.setTotal_food_price(setDiscount * fd.getPrice());

        }

    }
}                                                                                            
                                                                                             