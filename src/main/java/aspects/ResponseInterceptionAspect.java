package aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ResponseInterceptionAspect {

    @Pointcut("initialization(http.framework.response.JsonResponse.new(..))")
    void responseInitialization(){}

    @Before("responseInitialization()")
    public void beforeResponseInitialization(JoinPoint jp){
        System.out.println("Pozvan pre konstuktora");
        System.out.println(jp);
    }
}
