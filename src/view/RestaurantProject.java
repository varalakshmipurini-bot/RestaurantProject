package view;

import java.util.Scanner;
import dao.AdminDAO;
import dao.OrderDAO;

public class RestaurantProject {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderDAO dao = new OrderDAO();   // create DAO object once
        int choice;

        while (true) {
            System.out.println("\n====== Restaurant Management System ======");
            System.out.println("1. Admin Login");
            System.out.println("2. View Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Delete Order");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

            case 1:
                System.out.print("Enter admin username: ");
                String user = sc.next();

                System.out.print("Enter admin password: ");
                String pass = sc.next();

                AdminDAO adminDao = new AdminDAO();

                if (adminDao.login(user, pass)) {
                    System.out.println("✅ Admin login successful!\n");
                    
                    showAdminMenu(sc, dao);   // FIXED
                } else {
                    System.out.println("❌ Invalid admin credentials");
                }
                break;

            case 2:
                dao.viewOrders();   // Correct call
                break;

            case 3:
                System.out.print("Enter Order ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter new status (PLACED/COOKING/SERVED/CANCELLED): ");
                String status = sc.nextLine().toUpperCase();
                dao.updateOrderStatus(id, status);
                break;

            case 4:
                System.out.print("Enter Order ID to delete: ");
                id = sc.nextInt();
                dao.deleteOrder(id);
                break;

            case 5:
                System.out.println("Thank you! Exiting...");
                sc.close();
                System.exit(0);

            default:
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ============= ADMIN MENU =============
    public static void showAdminMenu(Scanner sc, OrderDAO dao) {

        System.out.println("\n------- Admin Menu -------");
        System.out.println("1. View Orders");
        System.out.println("2. Delete Order");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
        
        int ch = sc.nextInt();

        switch (ch) {

            case 1:
                dao.viewOrders();
                break;

            case 2:
                System.out.print("Enter Order ID to delete: ");
                int id = sc.nextInt();
                dao.deleteOrder(id);
                break;

            case 3:
                System.out.println("Logged out successfully!");
                return;

            default:
                System.out.println("Invalid option");
        }
    }
}
