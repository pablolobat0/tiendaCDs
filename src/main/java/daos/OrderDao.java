package daos;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DBUtils;

public class OrderDao {

    public void createOrder(int userId, BigDecimal price) {
        PreparedStatement stmOrder = null;
        try {
            stmOrder = DBUtils.getConnection().prepareStatement(
                    "INSERT INTO orders (user_id,price) values(?,?)");
            stmOrder.setInt(1, userId);
            stmOrder.setBigDecimal(2, price);
            stmOrder.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear el pedido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmOrder != null) {
                    stmOrder.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
