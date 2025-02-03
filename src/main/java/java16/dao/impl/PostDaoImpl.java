package java16.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java16.config.DBConfig;
import java16.dao.PostDao;
import java16.entity.Post;
import java16.entity.User;

import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private final EntityManager entityManager = DBConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public boolean savePost(Long userId, Post post) {

        try {
            entityManager.getTransaction().begin();

            User user = entityManager.find(User.class, userId);
            post.setOwner(user);
            entityManager.persist(post);

            if (user.getPost() == null) {
                user.setPost(new ArrayList<>());
            }
            user.getPost().add(post);
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<Post> findPostById(Long postId) {
     return   entityManager.createQuery
                ("select p from Post p where p.owner = :postId", Post.class).
                setParameter("postId", postId).getResultList();

    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
      return entityManager.createQuery("select p from Post p where p.id = :userId", Post.class)
               .setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Post> searchPost(String query) {
//        String hql = "select p from Post p where p.description like "+query;

        TypedQuery<Post> query1 = entityManager.createQuery("select p from Post p where p.description ilike :query", Post.class);
        query1.setParameter("query", "%" + query + "%");
       return query1.getResultList();
    }

    @Override
    public void deletePostById(Long id) {
       try {
           entityManager.getTransaction().begin();

           Post post = entityManager.find(Post.class, id);
           if (post != null) {
               entityManager.remove(post);
           }
           entityManager.getTransaction().commit();
       }catch (Exception e) {
           entityManager.getTransaction().rollback();
           System.out.println(e.getMessage());
       }
    }
}
