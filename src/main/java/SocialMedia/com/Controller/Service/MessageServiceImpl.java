package SocialMedia.com.Controller.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Model.Chat;
import SocialMedia.com.Model.Message;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.ChatRepo;
import SocialMedia.com.Repository.MessageRepo;


@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageRepo messageRepo;

  @Autowired
  private ChatService chatService;

  @Autowired
  private ChatRepo chatRepo;

  @Override
  public Message createMessage(User user, Integer chatId, Message message)  throws Exception{
 Message message2=new Message();

Chat chat=chatService.findChatByUserId(chatId);
 message2.setChat(chat);
 message2.setContent(message.getContent());
 message2.setUser(user);
 message2.setImage(message.getImage());
 message2.setTimestamp(LocalDateTime.now());
  Message savemessage=messageRepo.save(message2);
chat.getMessage().add(savemessage);
chatRepo.save(chat);
   return savemessage;
  }

  @Override
  public List<Message> findChatMessages(Integer chatId) throws Exception
  
  {

    Chat chat =chatService.findChatByUserId(chatId);
    
    return messageRepo.findByChatId(chatId);
  }


}
