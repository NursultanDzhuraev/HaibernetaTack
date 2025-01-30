package java16.dao;

import java16.entity.Profile;

public interface ProfileDao {
    void saveProfile(Long userId, Profile profile);
    Profile findByUserId(Long userId);
    void deleteProfileByUserId(Long userId);
}
