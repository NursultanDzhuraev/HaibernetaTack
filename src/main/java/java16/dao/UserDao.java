package java16.dao;

import java16.entity.Profile;
import java16.entity.User;

public interface UserDao {
    void save(User user);
    User findUserById(Long id);
    void updateUserProfile(Long userId, Profile profile);
    void delete(Long id);

}
