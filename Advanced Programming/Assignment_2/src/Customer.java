import java.util.ArrayList;
import java.util.Scanner;

class Customer extends Zotato implements Performer, Reward {
    private final ArrayList<Float> cart_amount;
    private final ArrayList<Food> food_buy;
    private final ArrayList<Float> food_price;
    private final String name;
    private final String address;
    private final String category;
    private boolean welcome_flag;
    private float wallet;
    private final ArrayList<Food> cart;
    private boolean cartvalue_flag = true;
    private int rest_index;
    private String restaurant_buy;
    private int my_reward;

    public Customer(String name, String address, float wallet, String category) {
        this.name = name;
        this.address = address;
        this.wallet = wallet;
        this.category = category;
        this.cart = new ArrayList<>();
        this.my_reward = 0;
        this.cart_amount = new ArrayList<>();
        this.food_buy = new ArrayList<>();
        this.food_price = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }


    public float getWallet() {
        return wallet;
    }


    public String getCategory() {
        return category;
    }


    public void showAllRestaurant() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Restaurant");
        for (int i = 0; i < Main.zotato.getRestaurants().size(); i++) {
            Restaurant obj = Main.zotato.getRestaurants().get(i);
            System.out.print((i + 1) + ") " + obj.getName());
            if (obj.getCategory() != "") {
                System.out.println("(" + obj.getCategory() + ")");
            } else {
                System.out.println();
            }
        }
        rest_index = input.nextInt() - 1;  //choosing restaurant
        restaurant_buy = Main.zotato.getRestaurants().get(rest_index).getName();
        System.out.println("Choose item by code");
        showDetails();

    }

    private void checkoutCart() {
        if (cart.size() > 0) {
            Scanner input = new Scanner(System.in);
            System.out.println("Items in the Cart - ");
            for (Food fd : cart) {
                System.out.println(fd.getID() + " " + restaurant_buy + " " + fd.getName() + " " + fd.getQuantity() + " " + fd.getOffer());
            }
            int total_quantity = 0;

            System.out.print("Delivery Charges: ");
            if (category.equals("")) {
                System.out.println(40);
            }
            if (category.equals("Elite")) {
                System.out.println(0);
            }
            if (category.equals("Special")) {
                System.out.println(20);
            }
            float bill = 0f;
            for (float a : cart_amount) {
                bill += a;
            }
            System.out.println("Total order value - " + bill);
            System.out.println("1) Proceed to checkout");
            int confirm = input.nextInt();
            if (confirm == 1) {
                while (bill > my_reward + wallet) {
                    System.out.println("Not enough amount in wallet....\npls remove some item to checkout");
                    showFoodinCart(cart);
                    System.out.print("Enter food id to remove: ");
                    int removeFoodindex = input.nextInt();
                    System.out.println();
                    cart.remove(removeFoodindex - 1);
                    if (cart.size() < 1) {
                        break;
                    }
                }
                if (my_reward + wallet >= bill) {   //if amount is low
                    for (Food fd : cart) {
                        System.out.println(fd.getID() + " " + restaurant_buy + " - " +
                                fd.getName() + " " + fd.getPrice() + " " +
                                fd.getQuantity() + " " + fd.getOffer() + "% Off ");
                        total_quantity += fd.getQuantity();
                    }
                    finalBill(bill, total_quantity);
                }

            } else {
                System.out.println("Wrong input is provided");
                Main.customer_flag = false;

            }
        } else
            System.out.println("No item is present in the cart");
    }

    private void finalBill(float bill, int total_quantity) {
        for (int i = 0; i < cart.size(); i++) {
            food_buy.add(cart.get(i));
            food_price.add(cart_amount.get(i));

        }
        cart.clear();
        cart_amount.clear();
        if (bill <= my_reward) {
            my_reward -= bill;
        } else {
            wallet -= bill;
            wallet += my_reward;
        }
        int reward = calcReward(bill);
        my_reward += reward;
        Main.zotato.getRestaurants().get(rest_index).setReward(reward);
        if (category.equals("")) {
            Main.zotato.setTotal_delivery_charge(Main.zotato.getTotal_delivery_charge() + 40);
        }
        if (category.equals("Special")) {
            Main.zotato.setTotal_delivery_charge(Main.zotato.getTotal_delivery_charge() + 20);
        }

        Main.zotato.setTotal_balance(bill*0.01f);
        food_price.add(bill);
        System.out.println(total_quantity + " items successfully bought for INR " + bill + "/-");
    }

    private void showFoodinCart(ArrayList<Food> cart) {
        for (Food fd : cart) {
            System.out.println(fd.getID() + " " + fd.getName() + " " + fd.getPrice() + " " + fd.getQuantity() + " " + fd.getTotal_food_price());
        }
    }

    //    @Override
    public void showDetails() {
        Scanner input = new Scanner(System.in);

        Restaurant rst = Main.zotato.getRestaurants().get(rest_index);
        for (Food fd : rst.getFood()) {
            System.out.println(fd.getID() + " " + rst.getName() + " - " + fd.getName() + " " + fd.getPrice() + " " + fd.getQuantity() + " " + fd.getOffer() + "% Off " + fd.getCategory());
        }
        int food_index = input.nextInt(); //choosing food
        OrderFood(food_index - 1);
    }

    private void OrderFood(int choice) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter item Quantity: ");
        int quantity = input.nextInt();
        Main.zotato.getRestaurants().get(rest_index).setNo_of_order(Main.zotato.getRestaurants().get(rest_index).getNo_of_order() + quantity); //inc no of order in restaurant
        Main.zotato.getRestaurants().get(rest_index).getFood().get(choice).setQuantity(Main.zotato.getRestaurants().get(rest_index).getFood().get(choice).getQuantity() - quantity); //decrease quantity of selected food
        Food fd = Main.zotato.getRestaurants().get(rest_index).getFood().get(choice);
        fd.setQuantity(quantity);
        restaurant_buy = Main.zotato.getRestaurants().get(rest_index).getName();
        fd.setTotal_food_price(quantity * fd.getPrice());
        cart.add(fd); //add food to the cart
        //Calculating Bill
        float bill = ((100 - Main.zotato.getRestaurants().get(rest_index).getFood().get(choice).getOffer()) * quantity * Main.zotato.getRestaurants().get(rest_index).getFood().get(choice).getPrice()) / 100;
        bill *= (100 - Main.zotato.getRestaurants().get(rest_index).getDiscount()) / 100; //additional discount given by restaurant
        if (category.equals("")) {
            bill += 40;
        } else if (category.equals("Elite")) {
            if (bill > 200)
                bill -= 50;
        } else if (category.equals("Special")) {
            if (bill > 200)
                bill -= 25;
            bill += 20;
        }
        cart_amount.add(bill);
        System.out.println("Items added to cart");
        welcome_flag = true;
        while (welcome_flag)
            welcomeOrder();

    }


    private void welcomeOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome " + getName());
        while (cartvalue_flag) {
            System.out.println("Customer Menu\n" +
                    "1) Select Restaurant\n" +
                    "2) checkout cart\n" +
                    "3) Reward won\n" +
                    "4) print the recent orders\n" +
                    "5) Exit");
            int choice = input.nextInt();
            OrderDetails(choice);
        }

    }

    private void OrderDetails(int choice) {
        if (choice == 1)
            showAllRestaurant();
        if (choice == 2)
            checkoutCart();
        if (choice == 3)
            System.out.println("Reward won " + my_reward);
        if (choice == 4)
            printrecentOrder();
        if (choice == 5) {
            cartvalue_flag = false;
            welcome_flag = false;
        }

    }

    private void printrecentOrder() {
        while (food_buy.size()>9){
            food_buy.remove(0);
            food_price.remove(0);
        }
        for (int i = 0; i < food_buy.size(); i++) {
            Food fd = food_buy.get(i);
            System.out.println(fd.getName() + " " + fd.getPrice() + " " + fd.getQuantity() + " " + food_price.get(i));
        }

    }

    public void showCustomerDetails() {
        System.out.println(getName() + " (" +
                getCategory() + "), " + getAddress() + "," +
                getWallet());
    }

    @Override
    public int calcReward(float bill) {
        int reward;
        String cat = Main.zotato.getRestaurants().get(rest_index).getCategory();
        if (cat.equals(""))
            reward = (int) (bill / 100) * 5;
        else if (cat.equals("Fast food"))
            reward = (int) (bill / 150) * 10;
        else
            reward = (int) (bill / 200) * 25;
        return reward;
    }

    @Override
    public void calcReward() {

    }

    @Override
    public void performOperations() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome " + name);
        System.out.println("1) Select Restaurant\n" +
                "2) checkout cart\n" +
                "3) Reward won\n" +
                "4) print the recent orders\n" +
                "5) Exit");
        int task = input.nextInt();
        if (task == 1)
            showAllRestaurant();
        if (task == 2)
            checkoutCart();
        if (task == 3) {
            System.out.println("Reward Won: " + my_reward);
        }
        if (task == 4) {
            printrecentOrder();
        } else {

            Main.customer_flag = false;
        }
    }
}


