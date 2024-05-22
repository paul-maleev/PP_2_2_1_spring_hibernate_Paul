package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User findById(Long id) {
        return userDao.findUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByCarModelAndSerial(String model, int serial) {
        return userDao.getUserByCarModelAndSerial(model, serial);
    }

}
