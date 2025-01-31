package SocialMedia.com.Controller.Service;

import java.util.List;

import SocialMedia.com.Model.Story;
import SocialMedia.com.Model.User;

public interface StoryService {

  public Story createStory(Story story,User user);
  public List<Story> findStoryByUserId(Integer userid) throws Exception;

}
