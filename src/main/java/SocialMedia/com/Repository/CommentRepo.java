package SocialMedia.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialMedia.com.Model.Comment;

public interface CommentRepo   extends   JpaRepository<Comment,Integer>{

}
