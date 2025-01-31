package SocialMedia.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SocialMedia.com.Model.Chat;
import SocialMedia.com.Model.User;

public interface ChatRepo extends JpaRepository<Chat, Integer> {

    // Find all chats by a specific user's ID
    public List<Chat> findByUsersId(Integer userId);

    // Find a chat between two specific users
    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);
}
