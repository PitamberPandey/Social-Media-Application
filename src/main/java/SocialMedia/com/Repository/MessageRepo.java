package SocialMedia.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SocialMedia.com.Model.Message;

public interface MessageRepo extends JpaRepository<Message,Integer> {

  public List<Message> findByChatId(Integer chartId);

}
