package OnlineBusTicket.exception.exc;

public class InCorrectPasswordException extends RuntimeException{
    private String message;

    public InCorrectPasswordException(String message){
        super(message);

    }
}
