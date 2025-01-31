package SocialMedia.com.Controller;

import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.ReelsService;
import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Model.Reels;
import SocialMedia.com.Model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class ReelsContoller {

  @Autowired
  ReelsService reelsService;
  @Autowired
  UserService userService;

  @PostMapping("api/reels")
  public Reels CreateReels(@RequestBody Reels reels,
  @RequestHeader("Authorization") String jwt) {
User reqUser=userService.findUserByJwt(jwt);
    Reels createdReels=reelsService.CreateReels(reels, reqUser);

     return createdReels;
  }

  @GetMapping("api/reels")
  public List<Reels> findAllReels() {
    List<Reels> allReels=reelsService.findAllReels();
    return allReels;
  }

  @GetMapping("api/reels/user/{userid}")
  public List<Reels> findReelsByUser(@PathVariable("userid") Integer userId) throws Exception {
    List<Reels> allReels=reelsService.findByUserReels(userId);
    return allReels;
  }
}
