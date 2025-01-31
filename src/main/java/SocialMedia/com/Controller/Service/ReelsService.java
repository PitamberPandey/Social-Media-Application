package SocialMedia.com.Controller.Service;

import java.util.List;

import SocialMedia.com.Model.Reels;
import SocialMedia.com.Model.User;

public interface ReelsService {

  public Reels CreateReels(Reels reels,User user);
  public List<Reels> findAllReels();
  public List<Reels> findByUserReels(Integer userId) throws Exception;

}
