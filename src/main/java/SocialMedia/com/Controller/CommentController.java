package SocialMedia.com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.ComementService;
import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Model.Comment;
import SocialMedia.com.Model.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class CommentController {
  @Autowired
  private ComementService comementService;
  @Autowired
   private UserService userService;
   @PostMapping("/api/comments/post/{postId}")
   public Comment createComment(@RequestBody Comment comment ,@RequestHeader("Authorization") String jwt,@PathVariable("postId") Integer postId) throws Exception {

    User reqUser=userService.findUserByJwt(jwt);
    Comment createdcomment=comementService.CreatedComments(comment, postId, reqUser.getId());

      
       
       return createdcomment;
   }
   

   @PutMapping("/api/comments/like/{commentId}")
   public Comment likecomment(@RequestHeader("Authorization") String jwt,@PathVariable("commentId") Integer commentId) throws Exception {

    User reqUser=userService.findUserByJwt(jwt);
    Comment likedComments=comementService.likeComment(commentId, reqUser.getId());

      
       
       return likedComments;
   }
   

}
