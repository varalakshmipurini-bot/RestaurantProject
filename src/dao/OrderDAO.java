package dao;

import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDAO {

    //----------- PLACE ORDER --------------
    public void placeOrder(int id, String item, int qty, double price) {

        String sql = "INSERT INTO orders VALUES(?,?,?,?,0)";
        
        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, item);
            ps.setInt(3, qty);
            ps.setDouble(4, price);

            int i = ps.executeUpdate();
            System.out.println(i>0 ? "Order Placed Successfully!" : "Failed to place order!");

        } catch(Exception e) { e.printStackTrace(); }
    }

    //----------- VIEW ORDERS --------------
    public void viewOrders() {
        String sql = "SELECT * FROM orders";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            System.out.println("\n------ ORDERS LIST ------");
            while(rs.next()) {
                System.out.println("ID: "+rs.getInt(1)+", Item: "+rs.getString(2)+
                        ", Qty: "+rs.getInt(3)+", Price: "+rs.getDouble(4)+
                        ", Status: "+rs.getString(5)+", Refund: "+rs.getDouble(6));
            }

        } catch(Exception e) { e.printStackTrace(); }
    }

    //----------- UPDATE ORDER --------------
    public void updateOrder(int id, String item, int qty, double price) {
        String sql = "UPDATE orders SET item_name=?, quantity=?, price=? WHERE order_id=?";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1,item);
            ps.setInt(2,qty);
            ps.setDouble(3,price);
            ps.setInt(4,id);

            System.out.println(ps.executeUpdate()>0 ? "Order Updated!" : "Order Not Found!");

        } catch(Exception e) { e.printStackTrace();}
    }

    //----------- UPDATE STATUS --------------
    public void updateOrderStatus(int id, String status) {
        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1,status);
            ps.setInt(2,id);

            System.out.println(ps.executeUpdate()>0 ? "Status Updated!" : "Order Not Found!");

        } catch(Exception e) { e.printStackTrace(); }
    }

    //----------- DELETE ORDER --------------
    public void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE order_id=?";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,id);
            System.out.println(ps.executeUpdate()>0 ? "Order Deleted!" : "Order Not Found!");

        } catch(Exception e) { e.printStackTrace(); }
    }

    //----------- CANCEL ORDER --------------
    public void cancelOrder(int id) {
        String sql = "UPDATE orders SET status='CANCELLED' WHERE order_id=?";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,id);
            System.out.println(ps.executeUpdate()>0 ? "Order Cancelled!" : "Order Not Found!");

        } catch(Exception e) { e.printStackTrace(); }
    }

    //----------- PARTIAL REFUND --------------
    public void partialRefund(int id, double amount) {
        String sql = "UPDATE orders SET refund=? WHERE order_id=?";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1,amount);
            ps.setInt(2,id);

            System.out.println(ps.executeUpdate()>0 ? "Partial Refund Processed!" : "Order Not Found!");

        } catch(Exception e) { e.printStackTrace(); }
    }

    //----------- VIEW REFUND ORDERS --------------
    public void viewRefundOrders() {
        String sql = "SELECT * FROM orders WHERE refund>0";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- REFUND ORDERS -----");
            while(rs.next()){
                System.out.println("ID: "+rs.getInt(1)+" | Item: "+rs.getString(2)+
                        " | Refund: "+rs.getDouble(6));
            }

        } catch(Exception e) { e.printStackTrace(); }
    }
}
