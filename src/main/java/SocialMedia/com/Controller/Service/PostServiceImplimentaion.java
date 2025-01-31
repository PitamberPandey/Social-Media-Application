package SocialMedia.com.Controller.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Model.Post;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.PostRepository;
import SocialMedia.com.Repository.UserRepository;


@Service
public class PostServiceImplimentaion  implements PostService{
@Autowired
  PostRepository postRepository;
  @Autowired
 UserService userService;
 @Autowired
 UserRepository userRepository;

  @Override
  public Post createNewPost(Post post, Integer userId) throws Exception {
    User user=userService.findUserById(userId);
Post newPost=new Post();
newPost.setCaption(post.getCaption());
newPost.setImage(post.getImage());
newPost.setCreatedAt(LocalDateTime.now());
newPost.setVideo(post.getVideo());
newPost.setUser(user);
return postRepository.save(newPost);
  }

  @Override
  public String DeletePost(Integer postId, Integer userId) throws Exception {
User user=userService.findUserById(userId);
Post post=FindPostById(postId);
if(post.getUser().getId()!=user.getId()){
throw new Exception("this is not your post you cannot delete this post");
  }
  postRepository.delete(post);
    return "Post deleted successfully";
  }

  @Override
  public List<Post> findByUserId(Integer userId) throws Exception {
    

    return postRepository.findByUserId(userId);
  }

  @Override
  public Post FindPostById(Integer postId) throws Exception {
Optional<Post> findPost=postRepository.findById(postId);
if(findPost.isPresent()){
  return findPost.get();
}
throw  new Exception("post not found from this id"+postId);

   

  }

  @Override
  public List<Post> findAllPost() {
    return postRepository.findAll();
  }

  @Override
  public Post savePost(Integer postId, Integer userId) throws Exception {
    Post post=FindPostById(postId);
    User user=userService.findUserById(userId);
    if(user.getSavedPost().contains(post)){
      user.getSavedPost().remove(post);
    }else user.getSavedPost().add(post);
    userRepository.save(user);
    return post;
  }

  @Override
  public Post likePost(Integer postId, Integer userId) throws Exception {
    Post post=FindPostById(postId);
    User user=userService.findUserById(userId);
    if(post.getLiked().contains(user)){
      post.getLiked().remove(user);
    }else{
    post.getLiked().add(user);
    }
    return postRepository.save(post);
  }

  @Override
  public Post updatepost(Integer postId) throws Exception {
    Post post=FindPostById(postId);
    

    return post;
  }

}
