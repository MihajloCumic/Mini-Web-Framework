package diengine.aspects;

import diengine.DIEngine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ControllerInitializationAspect {

    @Pointcut("@annotation(annotations.Controller)")
    void initializationOfController(){}

    @Before("initializationOfController()")
    public void AfterInitalization(JoinPoint jp){
        System.out.println(jp);
    }
}
