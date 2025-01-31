package SocialMedia.com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SocialMedia.com.Controller.Service.CustomUserDetailsService;

import SocialMedia.com.Model.User;
import SocialMedia.com.Repository.UserRepository;
import SocialMedia.com.cofig.jwtProvider;

@RestController
@RequestMapping("/auth")
public class AuthContoller {

@Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
@Autowired
   private CustomUserDetailsService customUserDetailsService;
  @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user)  throws Exception{
User isExit=userRepository.findByEmail(user.getEmail());
if(isExit!=null){
  throw new Exception("User already register in this email id");
}

      User newUser=new User();
      newUser.setEmail(user.getEmail());
      newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
      newUser.setPassword(passwordEncoder.encode(user.getPassword()));
      
      User saveUser=userRepository.save(newUser);
      Authentication authentication=new UsernamePasswordAuthenticationToken(saveUser.getEmail(),saveUser.getPassword());
      String token=jwtProvider.generateToken(authentication);
     
      AuthResponse res=new AuthResponse(token, "user register successfully");
      return res;
    }

    @PostMapping("/signin")
  public AuthResponse signIn(@RequestBody LoginRequest loginRequest){
    Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword()); 
    String token=jwtProvider.generateToken(authentication);
     
    AuthResponse res=new AuthResponse(token, "user sign in Successfully");
    return res;
        }
    
      private Authentication authenticate(String email, String password) {
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
        if(userDetails==null){
          throw new BadCredentialsException("Invalid username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
          throw new BadCredentialsException("Invalid username and password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
      }

    

  }


