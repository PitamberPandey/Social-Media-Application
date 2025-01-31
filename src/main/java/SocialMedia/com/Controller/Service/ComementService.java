package SocialMedia.com.Controller.Service;

import SocialMedia.com.Model.Comment;

public interface ComementService {


  public Comment CreatedComments(Comment comment,Integer postId,Integer userid) throws Exception;
  public Comment findByCommentID(Integer commentId) throws Exception;
  public Comment likeComment(Integer CommentId,Integer userId) throws Exception;
}
