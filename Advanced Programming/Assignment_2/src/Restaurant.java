import java.util.ArrayList;
import java.util.Scanner;

class Restaurant extends  Zotato implements Details, Performer {
    private final String name;
    private final String category;
    private int no_of_order;
    private int reward;
    private ArrayList<Food> food;
    private boolean restaurant_menu_flag;
    private float discount;

    public Restaurant(String name, int no_of_order, int reward, String category) {
        this.name = name;
        this.no_of_order = no_of_order;
        this.reward = reward;
        this.food = new ArrayList<>();
        this.category = category;
    }

    public float getDiscount() {
        return discount;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    public int getNo_of_order() {
        return no_of_order;
    }

    public void setNo_of_order(int no_of_order) {
        this.no_of_order = no_of_order;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void addFood() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter Food Details: ");
        System.out.print("\nFood Name: ");
        String name = input.nextLine();

        System.out.print("\nItem price: ");
        int price = input.nextInt();

        System.out.print("\nItem Quantity: ");
        int quantity = input.nextInt();
        input.nextLine();
        System.out.print("\nItem Category: ");
        String foodcategory = input.nextLine();

        System.out.print("\nOffer:   ");
        float offer = input.nextFloat();
        Food fd = new Food(food.size() + 1, name, quantity, price, foodcategory, offer);
        food.add(fd);
        System.out.println(fd.getID() + " " + fd.getName() + " " + fd.getPrice() + " " + fd.getQuantity() + " " + fd.getOffer() + " % off " + fd.getCategory());
    }

    public void editFood() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoose item by code: ");
        showDetails();
        int choice = input.nextInt();
        editAttribute(choice);

    }

    public void editAttribute(int food_choice) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose an attribute to edit:\n" +
                "1) Name\n" +
                "2) Price\n" +
                "3) Quantity\n" +
                "4) Category\n" +
                "5) Offer");
        int task = input.nextInt();
        System.out.print("Enter the new ");
        if (task == 1) {
            System.out.print("Name ");
            String name = input.nextLine();
            food.get(food_choice - 1).setName(name);
        }

        if (task == 2) {
            System.out.print("Price ");
            float price = input.nextFloat();
            food.get(food_choice - 1).setPrice(price);

        }
        if (task == 3) {
            System.out.print("Quantity ");
            int quantity = input.nextInt();
            food.get(food_choice - 1).setQuantity(quantity);
        }
        if (task == 4) {
            System.out.print("Category ");
            String category = input.nextLine();
            food.get(food_choice - 1).setCategory(category);
        }
        if (task == 5) {
            System.out.print("Offer ");
            float offer = input.nextFloat();
            food.get(food_choice - 1).setOffer(offer);
        }
        Food fd = food.get(food_choice - 1);
        System.out.println(fd.getID() + " " + fd.getName() + " " + fd.getPrice() + " " + fd.getQuantity() + " " + fd.getOffer() + " % off " + fd.getCategory());
    }

    public void showAllFood() {
        for (Food fd : food) {
            System.out.println(fd.getID() + " " + fd.getName() + " " + fd.getQuantity() + " " + fd.getOffer() + " % off " + fd.getCategory());
        }
    }

    public void printReward() {
        System.out.println("Reward Points: " + getReward());
    }

    public void DiscountOnBills() {
        Scanner input = new Scanner(System.in);
        System.out.print("Discount on bill value: ");
        discount = input.nextFloat();
        if (getCategory().equals(""))
            discount = 0;
        System.out.println();
    }


    public void taskPerformer(int task) {
        if (task == 1)
            addFood();
        if (task == 2)
            editFood();
        if (task == 3)
            printReward();
        if (task == 4)
            DiscountOnBills();
        if (task == 5) {
            restaurant_menu_flag = false;
            Main.restaurant_flag = false;
        }
    }

    @Override
    public void showDetails() {
        for (Food fd : food) {
            System.out.println(fd.getID() + " " + fd.getName() + " " + fd.getPrice() + " " + fd.getQuantity() + " " + fd.getOffer() + " % off " + fd.getCategory());
        }
    }

    @Override
    public void performOperations() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome " + name);
        restaurant_menu_flag = true;
        while (restaurant_menu_flag) {
            System.out.println("1) Add item\n" +
                    "2) Edit item\n" +
                    "3) Print Rewards\n" +
                    "4) Discount on bill value\n" +
                    "5) Exit");
            int choice = input.nextInt();
            taskPerformer(choice);
        }
    }

    public void showRestaurantDetails() {
        System.out.println(name + "," +
                category + "," + no_of_order);
    }
}












