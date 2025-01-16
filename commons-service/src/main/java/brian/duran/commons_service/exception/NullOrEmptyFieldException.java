package brian.duran.commons_service.exception;

public class NullOrEmptyFieldException extends RuntimeException{
    public NullOrEmptyFieldException(String message){
        super(message);
    }
}
