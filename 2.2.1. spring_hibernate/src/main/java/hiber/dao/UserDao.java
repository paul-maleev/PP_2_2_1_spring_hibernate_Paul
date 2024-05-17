package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {

    User findUserById(int id);

    void add(User user);

    void delete(User user);

    List<User> listUsers();

    User getUserByCarModelAndSerial(String model, int serial);
}
