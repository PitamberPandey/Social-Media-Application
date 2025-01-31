package SocialMedia.com.Controller.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Model.Reels;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.RealsRepo;
@Service
public class ReelsServiceimpl implements ReelsService {

   @Autowired
   private RealsRepo realsRepo;

   @Autowired
    private UserService userService;


  @Override
  public Reels CreateReels(Reels reels, User user) {
    Reels createdReels=new Reels();
    createdReels.setTitle(reels.getTitle());
    createdReels.setUser(user);
    createdReels.setVideo(reels.getVideo());
   
    
    return realsRepo.save(createdReels);
  }

  @Override
  public List<Reels> findAllReels() {
  return realsRepo.findAll();
  }

  @Override
  public List<Reels> findByUserReels(Integer userId) throws Exception {
    userService.findUserById(userId);

    return realsRepo.findByUserId(userId);
  }

}
