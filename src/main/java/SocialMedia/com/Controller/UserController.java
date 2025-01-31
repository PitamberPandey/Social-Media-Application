package SocialMedia.com.Controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.UserService;
import SocialMedia.com.Exception.UserException;
import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class UserController {
@Autowired
 UserRepository userRepository;
 @Autowired
 UserService userService;


   @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User save=userService.registerUser(user);
        return save;
    }

@GetMapping("/api/users/{id}")
public User FindByUserId(@PathVariable ("id") Integer id) throws Exception {

   User users=userService.findUserById(id);
   return users;
    
  

}

@PutMapping("/api/users")
public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
 User reqUser=userService.findUserByJwt(jwt);
 User updatedUser=userService.updataUser(user,reqUser.getId());

 return updatedUser;
    
   
}

@PutMapping("/api/users/follow/{userId2}")
public User followUserhander(  @RequestHeader("Authorization") String jwt,@PathVariable Integer userId2) throws UserException{

    User reqUser=userService.findUserByJwt(jwt);
    User user=userService.followUser(reqUser.getId(), userId2);
return user;
}

  @GetMapping("/api/user/search")
  public List<User> Search(@RequestParam ("query") String query) {
      List<User> users=userService.searchUser(query);
      return users;

  }
  @GetMapping("/api/users")
  public ResponseEntity<List<User>> findAll() {
      List<User> users = userService.findAllUsers(); // Assuming findAllUsers() does not require any parameter
      return ResponseEntity.ok(users);
  }

  @GetMapping("/api/users/profile")
public User getUserToken(@RequestHeader("Authorization") String jwt){
System.out.println("jwt token is"+jwt);

   User user=userService.findUserByJwt(jwt);
   user.setPassword(null);

    return user;

}

  
}
