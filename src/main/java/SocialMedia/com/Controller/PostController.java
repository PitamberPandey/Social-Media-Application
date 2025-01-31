package SocialMedia.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.PostService;
import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Model.Post;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.PostRepository;
import SocialMedia.com.reponse.Api_response;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PostController {
  @Autowired
  PostService postService;
  @Autowired
  PostRepository postRepository;

  @Autowired
  UserService userService;

  @PostMapping("api/posts")
  public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post)
      throws Exception {

    User reqUser = userService.findUserByJwt(jwt);
    Post createingPost = postService.createNewPost(post, reqUser.getId());

    return new ResponseEntity<>(createingPost, HttpStatus.CREATED);
  }

  @DeleteMapping("/posts/{postId}")
  public ResponseEntity<Api_response> deletePost(@RequestHeader("Authorization") String jwt,
      @PathVariable Integer postId) throws Exception {

    User reqUser = userService.findUserByJwt(jwt);
    String message = postService.DeletePost(postId, reqUser.getId());
    Api_response res = new Api_response(message, true);
    return new ResponseEntity<Api_response>(res, HttpStatus.OK);

  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<Post> findPostByHandler(@PathVariable Integer postId) throws Exception {

    Post post = postService.FindPostById(postId);
    return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);

  }

  @GetMapping("/posts/user/{userid}")
  public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userid) throws Exception {
    List<Post> posts = postService.findByUserId(userid);

    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts")
  public ResponseEntity<List<Post>> findAllPost() {
    List<Post> posts = postService.findAllPost();
    return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
  }

  @PutMapping("/posts/save/{postId}")
  public ResponseEntity<Post> savedPost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId)
      throws Exception {
    User reqUser = userService.findUserByJwt(jwt);

    Post post = postService.savePost(postId, reqUser.getId());
    return new ResponseEntity<Post>(post, HttpStatus.OK);

  }

  @PutMapping("/posts/like/{postId}")
  public ResponseEntity<Post> likePost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId)
      throws Exception {
    User reqUser = userService.findUserByJwt(jwt);
    Post post = postService.likePost(postId, reqUser.getId());
    return new ResponseEntity<Post>(post, HttpStatus.OK);

  }

}
