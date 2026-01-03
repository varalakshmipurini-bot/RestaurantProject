package model;

public class Order {

    private int orderId;
    private String itemName;
    private int quantity;
    private double price;
    private double total;
    private String status;

    public Order(int orderId, String itemName, int quantity, double price) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;   // bill calculation
        this.status = "PLACED";          // default status
    }

    public int getOrderId() { return orderId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
}
