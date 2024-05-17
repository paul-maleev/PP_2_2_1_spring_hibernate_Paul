package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void delete(User user);

    List<User> listUsers();

    User findById(int id);

    User getUserByCarModelAndSerial(String model, int serial);
}
