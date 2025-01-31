package SocialMedia.com.Controller.Service;

import java.util.List;

import SocialMedia.com.Exception.UserException;
import SocialMedia.com.Model.User;

public interface UserService {

  public User registerUser(User user);
  public User findUserById(Integer id) throws UserException;
  public User findUserByEmail(String email);
  public User followUser(Integer userId1,Integer userId2) throws UserException;
  public User updataUser(User user,Integer userId)throws UserException;
  public List<User>searchUser(String query);
  public List<User>findAllUsers();
  public User findUserByJwt(String jwt);



  



}
