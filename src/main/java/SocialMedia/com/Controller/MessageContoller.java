package SocialMedia.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.MessageService;
import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Model.Message;
import SocialMedia.com.Model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
public class MessageContoller {

  @Autowired
  UserService userService;

  @Autowired
  MessageService messageService;

  @PostMapping("api/messages/chat/{chatId}")
  public Message postMethodName(@RequestBody Message message,@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws Exception {
     User user=userService.findUserByJwt(jwt);
     Message message2=messageService.createMessage(user, chatId, message);
      return message2;
  }

  @GetMapping("api/messages/chat/{chatId}")
  public List<Message> findChatMessage(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws Exception {
     User user=userService.findUserByJwt(jwt);
     List<Message> message2=messageService.findChatMessages(chatId);
      return message2;
  }
  
  

}
