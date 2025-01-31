package SocialMedia.com.Controller;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
  private String email;
  private String password;

}
