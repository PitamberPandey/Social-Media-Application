package SocialMedia.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SocialMedia.com.Model.Post;


public interface PostRepository  extends JpaRepository<Post,Integer>{

  @Query("select p from Post p where p.user.id=:userId")
  public List<Post> findByUserId(Integer userId);

}
