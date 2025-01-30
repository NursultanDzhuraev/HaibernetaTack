package java16.dao.impl;

import jakarta.persistence.EntityManager;
import java16.config.DBConfig;
import java16.dao.ProfileDao;
import java16.entity.Profile;
import java16.entity.User;

public class ProfileDaoImpl implements ProfileDao {
    private final EntityManager entityManager = DBConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void saveProfile(Long userId, Profile profile) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        user.setProfile(profile);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public Profile findByUserId(Long userId) {
        User user = entityManager.find(User.class, userId);
        return user.getProfile();
    }

    @Override
    public void deleteProfileByUserId(Long userId) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user.getProfile());
    }
}
