import java.util.ArrayList;
import java.util.Scanner;

public class Zotato {

    private final ArrayList<Restaurant> restaurants;
    private final ArrayList<Customer> customers;
    private float total_balance;
    private int total_delivery_charge;

    public Zotato() {
        this.restaurants = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public float getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(float total_balance) {
        this.total_balance = total_balance;
    }

    public int getTotal_delivery_charge() {
        return total_delivery_charge;
    }

    public void setTotal_delivery_charge(int total_delivery_charge) {
        this.total_delivery_charge = total_delivery_charge;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void InitializeCustomers() {
        Customer obj1 = new Customer("Ram", "Delhi", 1000, "Elite");
        Customer obj2 = new Customer("Sam", "Mumbai", 1000, "Elite");
        Customer obj3 = new Customer("Tim", "Kolkata", 1000, "Special");
        Customer obj4 = new Customer("Kim", "Chennai", 1000, "");
        Customer obj5 = new Customer("Tim", "Bangalore", 1000, "");
        customers.add(obj1);
        customers.add(obj2);
        customers.add(obj3);
        customers.add(obj4);
        customers.add(obj5);
    }


    public void InitializeRestaurant() {
        Restaurant obj1 = new Restaurant("Shah", 0, 0, "Authentic");
        restaurants.add(obj1);
        Restaurant obj2 = new Restaurant("Ravi", 0, 0, "");
        restaurants.add(obj2);
        Restaurant obj3 = new Restaurant("The Chinese", 0, 0, "Authentic");
        restaurants.add(obj3);
        Restaurant obj4 = new Restaurant("Wang's", 0, 0, "Fast Food");
        restaurants.add(obj4);
        Restaurant obj5 = new Restaurant("Paradise", 0, 0, "");
        restaurants.add(obj5);

    }


    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }


    public void showAllRestaurant() {
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant obj = restaurants.get(i);
            System.out.print((i + 1) + ") " + obj.getName());
            if (!obj.getCategory().equals("")) {
                System.out.println("(" + obj.getCategory() + ")");
            } else {
                System.out.println();
            }

        }
    }


    public void showAllCustomer() {

        for (int i = 0; i < customers.size(); i++) {
            Customer obj = customers.get(i);
            System.out.print((i + 1) + ") " + obj.getName());
            if (!obj.getCategory().equals("")) {
                System.out.println("(" + obj.getCategory() + ")");
            } else {
                System.out.println();
            }

        }
    }

    public void showCustomerList() {
        Scanner input = new Scanner(System.in);
        int i = 1;
        for (Customer customer :
                customers) {
            System.out.println((i++) + ". " + customer.getName());
        }
        int choice = input.nextInt();
        customers.get(choice - 1).showCustomerDetails();
    }


    public void showRestaurantList() {
        Scanner input = new Scanner(System.in);
        int i = 1;
        for (Restaurant restaurant :
                restaurants) {
            System.out.println((i++) + ". " + restaurant.getName());
        }
        int choice = input.nextInt();
        restaurants.get(choice - 1).showRestaurantDetails();

    }

    public void myDetails() {
        System.out.println("Total Company balance - INR " + total_balance + "/-" +
                "\nTotal Delivery Charges Collected - INR" + total_delivery_charge + "/-");
    }

    public void customerActionPerformer(int cust_no) {
        customers.get(cust_no - 1).performOperations();

    }
}

