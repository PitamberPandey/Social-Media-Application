package SocialMedia.com.Controller.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Model.Chat;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.ChatRepo;

@Service
public class ChatServiceImpl implements ChatService {

  @Autowired
  private ChatRepo chatRepo;

  @Override
  public Chat createCaChat(User reqUser, User user2) {
Chat isExit=chatRepo.findChatByUserId(user2,reqUser);
if(isExit!=null){
  return isExit;

}
Chat chat=new Chat();
chat.getUsers().add(user2);
chat.getUsers().add(reqUser);
chat.setTimestap(LocalDateTime.now());


    return chatRepo.save(chat);
  }

  @Override
  public Chat findChatByUserId(Integer chatId) throws Exception {
Optional<Chat> chat=chatRepo.findById(chatId);
if(chat.isEmpty()){
  throw  new Exception("chat not found with id"+ chatId);
}

    return chat.get();
  }

  @Override
  public List<Chat> findUsersChat(Integer userid) {

    
    return chatRepo.findByUsersId(userid);
  }

}
