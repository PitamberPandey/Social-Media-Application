package SocialMedia.com.Controller.Service;

import java.util.List;

import SocialMedia.com.Model.Post;

public interface PostService {

  public Post createNewPost(Post post,Integer userId)throws Exception;
  public  String DeletePost(Integer postId,Integer userId) throws Exception;
  public List<Post> findByUserId(Integer userId) throws Exception;
  public Post FindPostById(Integer postId) throws Exception;
  public List<Post> findAllPost();
  public Post savePost(Integer postId,Integer userId) throws Exception;
  public Post likePost(Integer postId,Integer userId) throws Exception;
  public Post updatepost(Integer postId)throws Exception;




}
