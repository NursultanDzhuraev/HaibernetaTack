package java16.service;

import java16.entity.Profile;
import java16.entity.User;

public interface UserService {
    void save(User user);

    User findUserById(Long id);

    String updateUserProfile(Long userId, Profile profile);

    String delete(Long id);
}
