package view;

import java.sql.Connection;
import java.sql.ResultSet;

import util.DBUtil;

public class OrderView {

public void viewOrders() {
    try (Connection con = DBUtil.getConnection()) {

        String sql = "SELECT * FROM orders";
        ResultSet rs = con.prepareStatement(sql).executeQuery();

        System.out.println("\nID  ITEM    QTY  PRICE  FINAL  STATUS");
        System.out.println("------------------------------------------");

        while (rs.next()) {
            System.out.println(
                rs.getInt("order_id") + "  " +
                rs.getString("item_name") + "  " +
                rs.getInt("quantity") + "  " +
                rs.getDouble("price") + "  " +
                rs.getDouble("final_total") + "  " +
                rs.getString("status")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
