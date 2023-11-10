package aspects;


import engine.controller.Controller;
import engine.controller.ControllerContainer;
import http.framework.request.Request;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;

@Aspect
public class ResponseInterceptionAspect {

    @Pointcut("call(private * http.server.ServerThread.makeResponseMap(..))")
    void responseMapInterception(){}

    @Around("responseMapInterception()")
    public HashMap<String, Object> beforeResponseMapInterception(ProceedingJoinPoint joinPoint) throws Throwable {
        Request request = (Request) joinPoint.getArgs()[0];
        System.out.println(request.getLocation());
        return (HashMap<String, Object>) joinPoint.proceed();

    }

//    @Pointcut("initialization(http.framework.response.JsonResponse.new(..))")
//    void responseInitialization(){}
//
//    @Before("responseInitialization()")
//    public void beforeResponseInitialization(JoinPoint jp){
//        System.out.println("Pozvan pre konstuktora");
//        System.out.println(jp);
//        ControllerContainer controllerContainer = ControllerContainer.getInstance();
//        for(Controller controller: controllerContainer.getControllers()){
//            System.out.println(controller.getControllerType().getSimpleName());
//        }
//    }
}
