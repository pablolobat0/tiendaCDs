package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
  private static final String URL = "jdbc:postgresql://localhost:5432/shop";
  private static final String USER = "postgres";
  private static final String PASSWORD = "test1234";
  private static Connection connection;

  public static Connection getConnection() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    return connection;
  }

  // Método para cerrar la conexión
  public static void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }
}
