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
        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, userId);
            profile.setOwnerUser(user);
            entityManager.persist(profile);
            if (user.getProfile() == null) {user.setProfile(profile);}
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Profile findByUserId(Long userId) {
        try {
            User user = entityManager.find(User.class, userId);
            return user.getProfile();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteProfileByUserId(Long userId) {

        try {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, userId);
            if (user != null && user.getProfile() != null) {
                entityManager.remove(user.getProfile());
                user.setProfile(null);
                entityManager.merge(user);
            }

            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}