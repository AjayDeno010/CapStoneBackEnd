package OnlineBusTicket.exception;

import OnlineBusTicket.exception.exc.InCorrectPasswordException;
import OnlineBusTicket.exception.exc.SeatNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InCorrectPasswordException.class)
    public ResponseEntity<ErrorDetails> inCorrectPassword(InCorrectPasswordException exception, WebRequest webRequest){
        ErrorDetails errorDetails= new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Password_Not_Matched_TryAgain"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ErrorDetails> SeatNotAvailableException(SeatNotAvailableException exception, WebRequest webRequest){
        ErrorDetails errorDetails= new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Seat_Not_Available_Exception"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
