package java16.service.impl;

import java16.dao.UserDao;
import java16.dao.impl.UserDaoImpl;
import java16.entity.Profile;
import java16.entity.User;
import java16.service.UserService;

public class UserServiceImpl implements UserService {
    private  final UserDao userDao = new UserDaoImpl();
    @Override
    public void save(User user) {
        try {
            userDao.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserById(Long id) {
        try {
         return    userDao.findUserById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateUserProfile(Long userId, Profile profile) {
        try {
            userDao.updateUserProfile(userId, profile);
            return "Successfully updated";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(Long id) {
        try {
            userDao.delete(id);
            return "deleted user";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
