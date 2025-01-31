package SocialMedia.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.ChatService;
import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Model.Chat;
import SocialMedia.com.Model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class ChatContoller {

  @Autowired
  ChatService chatService;

  @Autowired
  private UserService UserService;

  @PostMapping("/api/chat")
  public Chat CreateChat(@RequestBody CreateChatRequest chatRequest,@RequestHeader("Authorization") String jwt) throws Exception {
    User reqUser=UserService.findUserByJwt(jwt);
    User user2=UserService.findUserById(chatRequest.getUserId());
      Chat chat =chatService.createCaChat(reqUser, user2);
      
      return chat;
  }
  
  @GetMapping("/api/chat")
  public List<Chat> findUserChat(@RequestHeader("Authorization") String jwt) {

    User user=UserService.findUserByJwt(jwt);
      List<Chat> chat =chatService.findUsersChat(user.getId());
      
      return chat;
  }

}
