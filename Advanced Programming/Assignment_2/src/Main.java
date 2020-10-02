import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean restaurant_flag;
    static boolean customer_flag;
    static Zotato zotato;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        zotato=new Zotato();
        zotato.InitializeCustomers();
        zotato.InitializeRestaurant();

        while (true){
            System.out.println("Welcome to Zotato: ");
            System.out.println("1) Enter as Restaurant Owner");
            System.out.println("2) Enter as Customer");
            System.out.println("3) Check User Details");
            System.out.println("4) Company Account details");
            System.out.println("5) Exit");
            int choice=input.nextInt();
            if(choice==1){
                restaurant_flag=true;
                while (restaurant_flag) {
                    zotato.showAllRestaurant();
                    int q = input.nextInt(); // selecting restaurant
                    zotato.getRestaurants().get(q - 1).performOperations();

                }
            }
            if (choice==2){
                customer_flag=true;
                while (customer_flag){
                    zotato.showAllCustomer();
                    int q=input.nextInt();
                    zotato.customerActionPerformer(q);
                }

            }
            if(choice==3){
                System.out.println("1) Customer List\n" +
                        "2) Restaurant List");
                int q=input.nextInt();
                if(q==1)
                    zotato.showCustomerList();
                if(q==2)
                    zotato.showRestaurantList();
            }
            if(choice==4){
                zotato.myDetails();
            }
            if (choice==5)
                break;

        }
    }


}
