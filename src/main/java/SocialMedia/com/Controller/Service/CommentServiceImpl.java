package SocialMedia.com.Controller.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Model.Comment;
import SocialMedia.com.Model.Post;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.CommentRepo;
import SocialMedia.com.Repository.PostRepository;

@Service
public class CommentServiceImpl implements ComementService {
@Autowired
 private PostService postService;
 @Autowired
 private UserService userService;
@Autowired
 private CommentRepo commentRepo;

 @Autowired
 private PostRepository postRepository;


  @Override
  public Comment CreatedComments(Comment comment, Integer postId, Integer userid)throws Exception {
    User user=userService.findUserById(userid);
    Post post=postService.FindPostById(postId);
    comment.setUser(user);
    comment.setContent(comment.getContent());
    comment.setCreatedAt(LocalDateTime.now());
    Comment saveComment=commentRepo.save(comment);
    post.getComments().add(saveComment);
    postRepository.save(post);
    return saveComment;
  }

  @Override
  public Comment findByCommentID(Integer commentId)throws Exception {

   Optional<Comment> opt=commentRepo.findById(commentId);
    if(opt.isEmpty()){
      throw new Exception("Comment not found by this id");
}

    return opt.get();
  }

  @Override
  public Comment likeComment(Integer CommentId, Integer userId)throws Exception {
     Comment comment= findByCommentID(CommentId);
     User user=userService.findUserById(userId);
  if(!comment.getLiked().contains(user)){
    comment.getLiked().add(user);
  }else{
    comment.getLiked().remove(user);
  }
    return commentRepo.save(comment);
  }

}
