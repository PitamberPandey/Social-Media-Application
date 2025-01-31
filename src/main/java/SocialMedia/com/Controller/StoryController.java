package SocialMedia.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.StoryService;
import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Model.Story;
import SocialMedia.com.Model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class StoryController {
  @Autowired
   private StoryService storyService;

   @Autowired 
   UserService userService;

   @PostMapping("/api/story")
   public Story CreateStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt) {
      User requestUser = userService.findUserByJwt(jwt);
     
      
  
       Story creaetd=storyService.createStory(story, requestUser);
   
      return creaetd;
   }

   @GetMapping("/api/story/user/{userid}")
   public List<Story> findUserStory(@RequestHeader("Authorization") String jwt,@PathVariable Integer userid) throws Exception{

    User requser=userService.findUserByJwt(jwt);
       List<Story> getStory=storyService.findStoryByUserId(userid);
       
       return getStory;
   }
   


}
