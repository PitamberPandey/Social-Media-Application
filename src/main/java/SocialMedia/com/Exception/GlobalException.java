package SocialMedia.com.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> otherExceptionHandeler(Exception ue,WebRequest req){
ErrorDetails error=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());


    return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
  }



  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorDetails> UserExcptionhandel(UserException ue,WebRequest req){
ErrorDetails error=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());


    return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
  }
}
