package java16.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.persist(post);
        Post post1 = entityManager.find(Post.class, post.getId());
        post1.setOwner(user);
        if(user.getPost() == null){user.setPost(new ArrayList<>());}
        user.getPost().add(post);
        entityManager.getTransaction().commit();
        return false;
    }
    public List<Post> findPostById(Long postId) {
     return   entityManager.createQuery
                ("select p from Post p where p.owner = :postId", Post.class).
                setParameter("postId", postId).getResultList();

    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        List<Post> posts = new ArrayList<>();
        posts.addAll(findPostById(userId));

        return posts;
    }

    @Override
    public Post searchPost(String query) {
        String hql = "from Post post where post.description like :query";
        entityManager.getTransaction().begin();
        Query query1 = entityManager.createQuery(hql);
        query1.setParameter("query", "%" + query + "%");
        Post post = (Post) query1.getSingleResult();
        entityManager.getTransaction().commit();
        return post;
    }

    @Override
    public void deletePostById(Long id) {
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }
}
