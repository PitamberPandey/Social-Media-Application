package SocialMedia.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialMedia.com.Model.Reels;

public interface RealsRepo extends JpaRepository<Reels,Integer> {

  public List<Reels>findByUserId(Integer userid);
}

