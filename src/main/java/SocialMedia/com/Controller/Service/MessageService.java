package SocialMedia.com.Controller.Service;

import java.util.List;

import SocialMedia.com.Model.Chat;
import SocialMedia.com.Model.Message;
import SocialMedia.com.Model.User;

public interface MessageService {

  public Message createMessage(User user,Integer chatId,Message message) throws Exception;

  public List<Message> findChatMessages(Integer chatId) throws Exception;

}
