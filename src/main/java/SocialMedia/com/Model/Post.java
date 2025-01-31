package SocialMedia.com.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name="post")
public class Post {
  
@Id
@GeneratedValue(strategy = GenerationType.AUTO)

  private Integer id;
  private String caption;
  @Column(length = 10000)
  private String image;
  private String video;

 
@ManyToOne
  private User user;
  @OneToMany
  private List<User> liked=new ArrayList<>();
  private LocalDateTime createdAt;
@OneToMany
  private List<Comment> comments=new ArrayList<>();
}
