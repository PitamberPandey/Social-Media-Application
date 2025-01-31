package SocialMedia.com.Controller.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SocialMedia.com.Exception.UserException;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.UserRepository;
import SocialMedia.com.cofig.jwtProvider;

@Service
public class UserServiceImplementation  implements UserService{
  @Autowired
  UserRepository userRepository;

  @Override
  public User registerUser(User user) {
User newUser=new User();
newUser.setEmail(user.getEmail());
newUser.setFirstName(user.getFirstName());
newUser.setLastName(user.getLastName());
newUser.setPassword(user.getPassword());
newUser.setId(user.getId());
User save=userRepository.save(newUser);
    return  save;
  }

  @Override
  public User findUserById(Integer id) throws UserException {
    Optional <User> user=userRepository.findById(id);
    if(user.isPresent()){
      return user.get();
    }
  throw new UserException("User not found with userid"+id);
  }

  @Override
  public User findUserByEmail(String email) {

    User user=userRepository.findByEmail(email);
    return user;
  }

  @Override
  public User followUser(Integer reqUserId, Integer userId2)  throws UserException{
    User reqUser=findUserById(reqUserId);
    User user2=findUserById(userId2);
 user2.getFollower().add(reqUser.getId());
 reqUser.getFollowing().add(user2.getId());
         userRepository.save(reqUser);
     userRepository.save(user2);
     return reqUser;
  }

  @Override
  public User updataUser(User user,Integer userId)  throws UserException{
    Optional<User> user1=userRepository.findById(userId);
    if(user1.isEmpty()){
      throw new UserException("User not exit with id"+userId);
 }
User olduser=user1.get();
if(olduser.getFirstName()!=null){
  olduser.setFirstName(user.getFirstName());
}
if(olduser.getLastName()!=null){
  olduser.setLastName(user.getLastName());

}
if(olduser.getEmail()!=null){
  olduser.setEmail(user.getEmail());

}
if(olduser.getGender()!=null){
  olduser.setGender(user.getGender());
}
User updateduser=userRepository.save(olduser);

    return updateduser;
  }

  @Override
  public List<User> searchUser(String query) {
  return userRepository.searchUsers(query);
  }

  @Override
  public List<User> findAllUsers() {
return userRepository.findAll();
  }

  @Override
  public User findUserByJwt(String jwt) {
    String email=jwtProvider.getEmailFromJwtToken(jwt);
    User user=userRepository.findByEmail(email);
    return user;
    
  }

}
