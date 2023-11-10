package exeptions;

public class FrameWorkExeptions extends Exception{
    public FrameWorkExeptions(String message){
        super("Framework exeption: " + message);
    }
}
