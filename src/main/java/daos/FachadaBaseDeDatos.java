package daos;

import exceptions.EmailAlreadyExists;
import exceptions.EmailNotValid;
import exceptions.PasswordNotValid;
import exceptions.UserDoesNotExist;
import exceptions.UsernameAlreadyExists;
import exceptions.UsernameNotValid;

public class FachadaBaseDeDatos {
  private static FachadaBaseDeDatos fachadaBaseDeDatos;

  private UserDao userDao;

  private FachadaBaseDeDatos() { this.userDao = new UserDao(); }

  public static synchronized FachadaBaseDeDatos getFachadaBaseDeDatos() {
    if (fachadaBaseDeDatos == null) {
      fachadaBaseDeDatos = new FachadaBaseDeDatos();
    }
    return fachadaBaseDeDatos;
  }

  public void register(String username, String email, String password)
      throws EmailNotValid, EmailAlreadyExists, UsernameNotValid,
             UsernameAlreadyExists, PasswordNotValid {
    userDao.register(username, email, password);
  }

  public void authenticate(String username, String password)
      throws UserDoesNotExist {
    userDao.authenticate(username, password);
  }
}
