package java16.service.impl;

import java16.dao.ProfileDao;
import java16.dao.impl.ProfileDaoImpl;
import java16.entity.Profile;
import java16.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao = new ProfileDaoImpl();

    @Override
    public String saveProfile(Long userId, Profile profile) {
        try {
            profileDao.saveProfile(userId, profile);
            return "Successfully saved profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Profile findByUserId(Long userId) {
        try {
            return profileDao.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteProfileByUserId(Long userId) {
        try {
            profileDao.deleteProfileByUserId(userId);
            return "Successfully deleted profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
