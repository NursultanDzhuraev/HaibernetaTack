package java16.service;

import java16.entity.Profile;

public interface ProfileService {
    String saveProfile(Long userId, Profile profile);

    Profile findByUserId(Long userId);

    String deleteProfileByUserId(Long userId);
}
