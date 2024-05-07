package daos;

import exceptions.EmailAlreadyExists;
import exceptions.EmailNotValid;
import exceptions.PasswordNotValid;
import exceptions.ProductDoesNotExists;
import exceptions.UserDoesNotExist;
import exceptions.UsernameAlreadyExists;
import exceptions.UsernameNotValid;

import java.math.BigDecimal;
import java.util.ArrayList;
import models.Product;
import models.User;

public class FachadaBaseDeDatos {
    private static FachadaBaseDeDatos fachadaBaseDeDatos;

    private UserDao userDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    private FachadaBaseDeDatos() {
        this.userDao = new UserDao();
        this.productDao = new ProductDao();
        this.orderDao = new OrderDao();
    }

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

    public User getUserByName(String name) throws UserDoesNotExist {
        return userDao.getUserByName(name);
    }

    public ArrayList<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductByName(String name) throws ProductDoesNotExists {
        return productDao.getProductByName(name);
    }

    public void createOrder(int userId, BigDecimal price) {
        orderDao.createOrder(userId, price);
    }
}
