package errorhandling.tests;

import annotations.enums.BeanScope;

public class CustomErrorHandling {
    public static void main(String[] args) {
        if(null == BeanScope.SINGLETON){
            System.out.println("nije");
        }
        if(null != BeanScope.SINGLETON){
            System.out.println("jeste");
        }
    }

    private static void myMethod() throws MyError {
        throw new MyError("from my Method");
    }
}
