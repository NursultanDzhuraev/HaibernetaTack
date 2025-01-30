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
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User findUserById(Long id) {
        try {
          return   entityManager.find(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserProfile(Long userId, Profile profile) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        user.setProfile(profile);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(
                entityManager.createQuery("select u from User u where u.id = :id", User.class)
                        .setParameter("id", id)
                        .getSingleResult()
        );
        entityManager.getTransaction().commit();
    }
}
