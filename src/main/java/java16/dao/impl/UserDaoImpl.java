package java16.dao.impl;


import jakarta.persistence.EntityManager;

import java16.config.DBConfig;
import java16.dao.UserDao;
import java16.entity.Profile;
import java16.entity.User;

public class UserDaoImpl implements UserDao {
    public final EntityManager entityManager = DBConfig.getEntityManagerFactory().createEntityManager();


    @Override
    public void save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User findUserById(Long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateUserProfile(Long userId, Profile profile) {
        try {
            entityManager.getTransaction().begin();

            User user = entityManager.find(User.class, userId);
            if (user != null) {
                Profile profile1 = user.getProfile();
                if (profile1 != null) {
                    profile1.setBirthday(profile.getBirthday());
                    profile1.setFirstName(profile.getFirstName());
                    profile1.setBiography(profile.getBiography());
                    profile1.setGender(profile.getGender());
                }else {
                    user.setProfile(profile);
                }
                entityManager.getTransaction().commit();
            } else {
                System.out.println("User not found");
            }

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
       try {
           entityManager.getTransaction().begin();
           User user = entityManager.find(User.class, id);
           if (user != null) {
               entityManager.remove(user);
               entityManager.getTransaction().commit()


           } else {
               System.out.println("User not found");
           }
       }catch(Exception e) {
           entityManager.getTransaction().rollback();
           System.out.println(e.getMessage());
       }
    }
}
