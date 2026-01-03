package controller;

import dao.AdminDAO;
import dao.OrderDAO;
import java.util.Scanner;

public class OrderController {

    // Track admin login session
    static boolean isAdminLoggedIn = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderDAO dao = new OrderDAO();
        AdminDAO adminDao = new AdminDAO();

        while (true) {
            System.out.println("\n====== Restaurant Management System ======");
            System.out.println("1. Admin Login");
            System.out.println("2. Place Order");
            System.out.println("3. View Orders");
            System.out.println("4. Update Order");
            System.out.println("5. Update Order Status");
            System.out.println("6. Delete Order");
            System.out.println("7. Cancel Order");
            System.out.println("8. Partial Refund");
            System.out.println("9. View Refund Orders");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {

                // -------------------------------- ADMIN LOGIN
                case 1:
                    System.out.print("Enter admin username: ");
                    String user = sc.next();

                    System.out.print("Enter admin password: ");
                    String pass = sc.next();

                    if (adminDao.login(user, pass)) {
                        isAdminLoggedIn = true;
                        System.out.println("✅ Admin login successful\n");
                        showAdminMenu(sc, dao);
                    } else {
                        System.out.println("❌ Invalid admin credentials\n");
                    }
                    break;

                // -------------------------------- PLACE ORDER
                case 2:
                    System.out.print("Order ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Item Name: ");
                    String item = sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    dao.placeOrder(id, item, qty, price);
                    break;

                // -------------------------------- VIEW ORDERS
                case 3:
                    dao.viewOrders();
                    break;

                // -------------------------------- UPDATE ORDER
                case 4:
                    System.out.print("Order ID to update: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Item Name: ");
                    item = sc.nextLine();

                    System.out.print("New Quantity: ");
                    qty = sc.nextInt();

                    System.out.print("New Price: ");
                    price = sc.nextDouble();

                    dao.updateOrder(id, item, qty, price);
                    break;

                // -------------------------------- UPDATE STATUS
                case 5:
                    System.out.print("Order ID: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Status (PLACED/COOKING/SERVED): ");
                    String status = sc.nextLine().toUpperCase();

                    dao.updateOrderStatus(id, status);
                    break;

                // -------------------------------- DELETE ORDER
                case 6:
                    System.out.print("Order ID to delete: ");
                    id = sc.nextInt();
                    dao.deleteOrder(id);
                    break;

                // -------------------------------- CANCEL ORDER
                case 7:
                    System.out.print("Enter Order ID to cancel: ");
                    int cancelId = sc.nextInt();
                    dao.cancelOrder(cancelId);
                    break;

                // -------------------------------- PARTIAL REFUND
                case 8:
                    if (!isAdminLoggedIn) {
                        System.out.println("❌ Admin login required");
                        break;
                    }
                    System.out.print("Enter Order ID: ");
                    int oid = sc.nextInt();
                    System.out.print("Enter refund amount: ");
                    double amt = sc.nextDouble();
                    dao.partialRefund(oid, amt);
                    break;

                // -------------------------------- VIEW REFUND ORDERS
                case 9:
                    dao.viewRefundOrders();
                    break;

                // -------------------------------- EXIT
                case 10:
                    System.out.println("Thank You 😊");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try Again.");
            }
        }
    }

    // ===== ADMIN SUB MENU =====
    public static void showAdminMenu(Scanner sc, OrderDAO dao) {
        System.out.println("\n------- Admin Menu -------");
        System.out.println("1. View Orders");
        System.out.println("2. Delete Order");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
        
        int choice = sc.nextInt();

        switch (choice) {
            case 1: dao.viewOrders(); break;
            case 2:
                System.out.print("Enter Order ID to delete: ");
                int id = sc.nextInt();
                dao.deleteOrder(id);
                break;
            case 3: 
                isAdminLoggedIn = false;
                System.out.println("Logged out successfully");
                return;
            default:
                System.out.println("Invalid option");
        }
    }
}
