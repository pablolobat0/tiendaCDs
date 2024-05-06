package daos;

import java.sql.*;
import java.util.ArrayList;
import models.CD;
import models.Product;
import utils.DBUtils;

public class ProductDao {
  public ArrayList<Product> getAllProducts() {
    PreparedStatement stmProducts = null;
    ResultSet rsProducts = null;
    ArrayList<Product> products = new ArrayList<>();
    try {
      stmProducts =
          DBUtils.getConnection().prepareStatement("SELECT * FROM cds");
      rsProducts = stmProducts.executeQuery();
      while (rsProducts.next()) {
        CD cd = new CD(rsProducts.getString("name"),
                       rsProducts.getBigDecimal("price"));
        products.add(cd);
        System.out.println(cd);
        System.out.println("Hola");
      }
    } catch (SQLException e) {
      System.err.println("Error al obtener los productos: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (stmProducts != null) {
          stmProducts.close();
        }
        if (rsProducts != null) {
          rsProducts.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return products;
  }
}
