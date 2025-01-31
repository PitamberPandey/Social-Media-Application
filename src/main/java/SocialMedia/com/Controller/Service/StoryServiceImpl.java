package SocialMedia.com.Controller.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Model.Story;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.StroyRepo;

@Service
public class StoryServiceImpl implements StoryService {
@Autowired
  UserService userService;
  @Autowired
  StroyRepo stroyRepo;

  @Override
  public Story createStory(Story story, User user)  {

 Story createStory= new Story();
 createStory.setCaption(story.getCaption());
 createStory.setImage(story.getImage());
 createStory.setUser(user);
 createStory.setTimestamp(LocalDateTime.now());

    return stroyRepo.save(createStory);
  }

  @Override
  public List<Story> findStoryByUserId(Integer userid) throws Exception {
User user=userService.findUserById(userid);


    return stroyRepo.findByUserId(userid);
  }

}
