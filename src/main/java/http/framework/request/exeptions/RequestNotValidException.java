package http.framework.request.exeptions;

public class RequestNotValidException extends Exception{
    public RequestNotValidException(String command){
        super("Client request is invalid. Requested command: " + command);
    }
}
