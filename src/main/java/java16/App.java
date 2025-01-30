package java16;

import java16.config.DBConfig;
import java16.enam.Gender;
import java16.entity.Comment;
import java16.entity.Post;
import java16.entity.Profile;
import java16.entity.User;
import java16.service.CommentService;
import java16.service.PostService;
import java16.service.ProfileService;
import java16.service.UserService;
import java16.service.impl.CommentServiceImpl;
import java16.service.impl.PostServiceImpl;
import java16.service.impl.ProfileServiceImpl;
import java16.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//
//        DBConfig.getEntityManagerFactory();
        UserService userService = new UserServiceImpl();
        ProfileService profileService = new ProfileServiceImpl();
        PostService postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1  save User;
                    2  findUserById;
                    3  updateUserProfile;
                    4  delete User;
                    5  saveProfile;
                    6  Profile findByUserId;
                    7  deleteProfileByUserId
                    8  savePost;
                    9  getPostByUserId;
                    10 searchPost query;
                    11 deletePostById
                    12 saveComment;
                    13 findCommentByPostId;
                    14 updateComment newText;
                    15 deleteComment
                    16 Exit
                    """);
            System.out.print("Enter command: ");
            switch (scanner.nextInt()) {
                case 1 -> {
                    userService.save(new User("Aibek", "aibek@gmail.com", "aibek123"));
                    userService.save(new User("Baiel", "Baiel@gmail.com", "Baiel123"));
                    userService.save(new User("Almaz", "Almaz@gmail.com", "Almaz123"));
                    userService.save(new User("Akylay", "Akylay@gmail.com", "Akylay123"));
                }
                case 2 -> {
                    User userById = userService.findUserById(2L);
                    System.out.println(userById);
                }
                case 3 -> {
                    userService.updateUserProfile(1L, new Profile("Asan",
                            LocalDate.of(2024, 10, 13), Gender.MALE, "aaa"));
                    userService.updateUserProfile(2L, new Profile("Ysan",
                            LocalDate.of(2024, 9, 12), Gender.MALE, "bbb"));
                    userService.updateUserProfile(3L, new Profile("Batma",
                            LocalDate.of(2024, 8, 11), Gender.FEMALE, "ccc"));
                    userService.updateUserProfile(4L, new Profile("Zyyra",
                            LocalDate.of(2024, 7, 10), Gender.FEMALE, "ddd"));
                }
                case 4 -> {
                    String delete = userService.delete(4L);
                    System.out.println(delete);
                }
                case 5 -> {
                    profileService.saveProfile(1L, new Profile("Asan1", LocalDate.of(2024, 10, 13), Gender.MALE, "aaa1"));
                    profileService.saveProfile(2L, new Profile("Asan2", LocalDate.of(2024, 10, 13), Gender.MALE, "aaa2"));
                    profileService.saveProfile(3L, new Profile("Asan3", LocalDate.of(2024, 10, 13), Gender.MALE, "aaa3"));
                }
                case 6 -> {
                    Profile byUserId = profileService.findByUserId(1L);
                    System.out.println(byUserId);
                }
                case 7 -> {
                    String s = profileService.deleteProfileByUserId(3L);
                    System.out.println(s);
                }
                case 8 -> {
                    postService.savePost(1L, new Post("foto1", "zzzz"));
                    postService.savePost(1L, new Post("foto2", "aaaa"));
                    postService.savePost(2L, new Post("foto3", "zxcv"));
                    postService.savePost(2L, new Post("foto4", "asdf"));
                    postService.savePost(3L, new Post("foto5", "qwer"));
                }
                case 9 -> {
                    List<Post> postByUserId = postService.getPostByUserId(1L);
                    for (Post post : postByUserId) {
                        System.out.println(post);
                    }
                }
                case 10 -> {
                    Post post = postService.searchPost("zzz");
                    System.out.println(post);
                }
                case 11 -> {
                    String s = postService.deletePostById(3L);
                    System.out.println(s);
                }
                case 12 -> {
                    commentService.saveComment(1L, 1L, new Comment("alma"));
                    commentService.saveComment(1L, 1L, new Comment("alma2"));
                    commentService.saveComment(1L, 1L, new Comment("alma3"));
                }
                case 13 -> {
                    List<Comment> commentByPostId = commentService.findCommentByPostId(1L);
                    for (Comment comment : commentByPostId) {
                        System.out.println(comment);
                    }
                }
                case 14 -> {
                    commentService.updateComment(1L, "alma11");
                }
                case 15 -> {
                    commentService.deleteComment(new Comment("alma3"));
                }
                case 16 -> {
                    return;
                }
            }
        }
    }
}
