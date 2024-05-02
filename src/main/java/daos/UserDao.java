package daos;

import at.favre.lib.crypto.bcrypt.BCrypt;
import exceptions.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;
import utils.DBUtils;
import utils.Validators;

public class UserDao {

  public void register(String username, String email, String password)
      throws UsernameAlreadyExists, EmailAlreadyExists, UsernameNotValid,
             PasswordNotValid, EmailNotValid {

    if (!Validators.isValidUsername(username)) {
      throw new UsernameNotValid(
          "El nombre de usuario puede estar compuesto por letras, números y "
          + "'_' y debe tener entre 3 y 20 carácteres");
    }
    if (!Validators.isValidPassword(password)) {
      throw new PasswordNotValid(
          "La contraseña debe contener al menos una letra minúscula, una "
          + "mayúscula y un número y debe tener un longitud de entre 3 y 30 "
          + "carácteres");
    }
    if (!Validators.isValidEmail(email)) {
      throw new EmailNotValid("El email no tiene un formato válido");
    }

    if (existUsername(username)) {
      throw new UsernameAlreadyExists("El nombre de usuario ya ha sido usado");
    }
    if (existEmail(email)) {
      throw new EmailAlreadyExists("El email ya ha sido usado");
    }

    PreparedStatement stmUsuario = null;
    try {
      String salt = generateSalt(password);
      String hashedPassword = hashPassword(password, salt);
      stmUsuario = DBUtils.getConnection().prepareStatement(
          "INSERT INTO users (name,password,email, salt) values(?,?,?,?)");
      stmUsuario.setString(1, username);
      stmUsuario.setString(2, hashedPassword);
      stmUsuario.setString(3, email);
      stmUsuario.setString(4, salt);
      stmUsuario.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Error al crear el usuario: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (stmUsuario != null) {
          stmUsuario.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean existUsername(String name) {
    PreparedStatement stmUsuario = null;
    ResultSet rsUsuario = null;
    try {
      stmUsuario = DBUtils.getConnection().prepareStatement(
          "SELECT COUNT(*) FROM usuarios WHERE nombre = ?");
      stmUsuario.setString(1, name);
      rsUsuario = stmUsuario.executeQuery();
      rsUsuario.next();
      return rsUsuario.getInt(1) > 0;
    } catch (SQLException e) {
      System.err.println(
          "Error al verificar la existencia del nombre de usuario: " +
          e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (stmUsuario != null) {
          stmUsuario.close();
        }
        if (rsUsuario != null) {
          rsUsuario.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return false;
  }

  private boolean existEmail(String email) {
    PreparedStatement stmUsuario = null;
    ResultSet rsUsuario = null;

    try {
      stmUsuario = DBUtils.getConnection().prepareStatement(
          "SELECT COUNT(*) FROM usuarios WHERE email = ?");
      stmUsuario.setString(1, email);
      rsUsuario = stmUsuario.executeQuery();
      rsUsuario.next();
      return rsUsuario.getInt(1) > 0;
    } catch (SQLException e) {
      System.err.println(
          "Error al verificar la existencia del email de usuario: " +
          e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (stmUsuario != null) {
          stmUsuario.close();
        }
        if (rsUsuario != null) {
          rsUsuario.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return false;
  }

  private String hashPassword(String password, String salt) {
    return BCrypt.withDefaults().hashToString(12,
                                              (salt + password).toCharArray());
  }

  private String generateSalt(String password) {
    return BCrypt.withDefaults().hashToString(12, password.toCharArray());
  }

  public void authenticate(String username, String password)
      throws UserDoesNotExist {
    PreparedStatement stmUsuario = null;
    ResultSet rsUsuario = null;

    try {
      stmUsuario = DBUtils.getConnection().prepareStatement(
          "SELECT password, salt FROM users WHERE name = ?");
      stmUsuario.setString(1, username);
      rsUsuario = stmUsuario.executeQuery();

      if (rsUsuario.next()) {
        String storedPassword = rsUsuario.getString("password");
        String storedSalt = rsUsuario.getString("salt");
        if (!verifyPassword(password, storedPassword, storedSalt)) {
          throw new UserDoesNotExist(
              "La contraseña o el nombre de usuario no son correctos");
        }
      }
    } catch (SQLException e) {
      System.err.println("Error al verificar el usuario: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (stmUsuario != null) {
          stmUsuario.close();
        }
        if (rsUsuario != null) {
          rsUsuario.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean verifyPassword(String password, String storedPassword,
                                 String storedSalt) {
    String hashedPassword = hashPassword(password, storedSalt);
    return hashedPassword.equals(storedPassword);
  }
}
