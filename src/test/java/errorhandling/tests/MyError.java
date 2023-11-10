package errorhandling.tests;

public class MyError extends Exception{
    public MyError(String error){
        super("MyError: " + error);
    }
}
