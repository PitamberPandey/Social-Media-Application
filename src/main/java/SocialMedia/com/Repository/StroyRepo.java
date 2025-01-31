package SocialMedia.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialMedia.com.Model.Story;

public interface StroyRepo  extends JpaRepository<Story,Integer >{
public List<Story> findByUserId(Integer id);
}
