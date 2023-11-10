package errorhandling.tests;

public class CustomErrorHandling {
    public static void main(String[] args) {
        try{
            myMethod();
        } catch (MyError myError) {
            myError.printStackTrace();
        }
    }

    private static void myMethod() throws MyError {
        throw new MyError("from my Method");
    }
}
