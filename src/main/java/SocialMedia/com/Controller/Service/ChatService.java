package SocialMedia.com.Controller.Service;

import java.util.List;

import SocialMedia.com.Model.Chat;
import SocialMedia.com.Model.User;

public interface ChatService {
 public Chat createCaChat(User reqUser,User user2);
 public Chat findChatByUserId(Integer chatId) throws Exception;
 public List<Chat> findUsersChat(Integer userid);


}
