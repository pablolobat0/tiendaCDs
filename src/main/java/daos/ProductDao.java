package daos;

import java.sql.*;
import java.util.ArrayList;

import exceptions.ProductDoesNotExists;
import models.CD;
import models.Product;
import utils.DBUtils;

public class ProductDao {
    public ArrayList<Product> getAllProducts() {
        PreparedStatement stmProducts = null;
        ResultSet rsProducts = null;
        ArrayList<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            stmProducts = connection.prepareStatement("SELECT * FROM cds");
            rsProducts = stmProducts.executeQuery();
            while (rsProducts.next()) {
                CD cd = new CD(rsProducts.getString("name"),
                        rsProducts.getBigDecimal("price"));
                products.add(cd);
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
                DBUtils.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public Product getProductByName(String name) throws ProductDoesNotExists {
        PreparedStatement stmProducts = null;
        ResultSet rsProducts = null;
        Product product = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            stmProducts = connection.prepareStatement("SELECT * FROM cds WHERE name = ?");
            stmProducts.setString(1, name);

            rsProducts = stmProducts.executeQuery();
            if (rsProducts.next()) {
                product = new CD(rsProducts.getString("name"),
                        rsProducts.getBigDecimal("price"));
            } else {
                throw new ProductDoesNotExists("El producto no existe");
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
                DBUtils.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }
}
