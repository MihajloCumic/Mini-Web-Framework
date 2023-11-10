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

import java.lang.reflect.Method;
import java.util.HashMap;

@Aspect
public class ResponseInterceptionAspect {

    @Pointcut("call(private * http.server.ServerThread.makeResponseMap(..))")
    void responseMapInterception(){}

    @Around("responseMapInterception()")
    public HashMap<String, Object> beforeResponseMapInterception(ProceedingJoinPoint joinPoint) throws Throwable {
        Request request = (Request) joinPoint.getArgs()[0];
        String path = request.getMethod().toString() + ":" + request.getLocation().split("\\?")[0];
        ControllerContainer controllerContainer = ControllerContainer.getInstance();
        HashMap<String, Object> map = controllerContainer.findAndCallMethodWithPath(path, request);

        if(map == null) return (HashMap<String, Object>) joinPoint.proceed();

        return map;

    }
}
