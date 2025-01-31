package SocialMedia.com.Model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {
@Id
  @GeneratedValue(strategy =GenerationType.AUTO)
  private Integer id;

 
  private String image;
  private String caption;
  private LocalDateTime timestamp;
  @ManyToOne
  private User user;

}
