package diengine;

import annotations.*;
import annotations.enums.BeanScope;
import dependencycontainer.DependencyContainer;
import engine.controller.Controller;
import engine.controller.ControllerContainer;
import exeptions.FrameWorkExeptions;
import exeptions.messages.AutowiredAttributeNotABean;
import exeptions.messages.InnterfaceAttributeMustHaveQualifier;
import exeptions.messages.NoImplementationFound;
import scanner.PackageScanner;
import scanner.implementations.QualifierScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DIEngine {

    private static DIEngine instance;
    private ControllerContainer controllerContainer;
    private List<Object> singletonDependencyList;
    private DependencyContainer dependencyContainer;


    private DIEngine(DependencyContainer dependencyContainer){
        this.controllerContainer = ControllerContainer.getInstance();
        this.singletonDependencyList = new ArrayList<>();
        this.dependencyContainer = dependencyContainer;


    }

    public static synchronized DIEngine getInstance(DependencyContainer dependencyContainer){
        if(instance == null){
            instance = new DIEngine(dependencyContainer);
        }
        return instance;
    }

    public void injectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, FrameWorkExeptions {
        for(Controller controller: this.controllerContainer.getControllers()){
            Field[] controllerFields = controller.getControllerType().getDeclaredFields();
            traverseThroughDependencies(controllerFields, controller.getController());
        }
    }

    private void traverseThroughDependencies(Field[] fields, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, FrameWorkExeptions {

        for(Field field: fields){
            Annotation annotation = field.getAnnotation(Autowired.class);
            if(annotation == null) continue;

            Object existingService = serviceIsAlreadyInitialized(field);
            Object fieldObject;
            if(existingService == null){
                fieldObject = initializeDependency(field);

                if(fieldObject == null) continue;
                Field[] fieldFields = fieldObject.getClass().getDeclaredFields();

                traverseThroughDependencies(fieldFields, fieldObject);
            }else{
                fieldObject = existingService;
            }
            printVerbose(field, fieldObject);

            injectDependency(field, object, fieldObject);
        }
        return;
    }

    private void injectDependency(Field field, Object instance, Object dependecy) throws IllegalAccessException {
        boolean fieldsAccessability = field.isAccessible();

        field.setAccessible(true);
        field.set(instance, dependecy);
        field.setAccessible(fieldsAccessability);
    }

    private Object initializeDependency(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, FrameWorkExeptions {
        Class<?> fieldClass = field.getType();

        if(fieldClass.isInterface()) {
            fieldClass = initalizeInterface(field);
        }
        Annotation bean = fieldClass.getDeclaredAnnotation(Bean.class);
        BeanScope scope = null;
        if(bean != null){
            scope = ((Bean)bean).scope();
        }

        Annotation service = fieldClass.getDeclaredAnnotation(Service.class);
        if(service != null || scope == BeanScope.SINGLETON) return initalizeSingletonDependency(fieldClass);

        Annotation component = fieldClass.getDeclaredAnnotation(Compoenent.class);
        if(component != null || scope == BeanScope.PROTOTYPE){
            Object componentObject = fieldClass.getDeclaredConstructor().newInstance();
            return componentObject;
        }
        AutowiredAttributeNotABean message = new AutowiredAttributeNotABean(fieldClass.getSimpleName(), field.getDeclaringClass().getSimpleName());
        throw new FrameWorkExeptions(message.toString());
    }

    private Class<?> initalizeInterface(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, FrameWorkExeptions {
        Qualifier qualifierAnnotation = field.getDeclaredAnnotation(Qualifier.class);
        if(qualifierAnnotation == null){
            InnterfaceAttributeMustHaveQualifier message = new InnterfaceAttributeMustHaveQualifier(field.getType().getSimpleName(), field.getDeclaringClass().getSimpleName());
            throw new FrameWorkExeptions(message.toString());
        }
        String qualifier = qualifierAnnotation.value();
        String interfaceName = field.getType().getName();
        Class<?> implementationClass = this.dependencyContainer.getImplementationClassByQualifier(qualifier, interfaceName);
        if(implementationClass == null) {
            NoImplementationFound message = new NoImplementationFound(qualifier, interfaceName);
            throw new FrameWorkExeptions(message.toString());
        }
        //Stara implementacija:
//        Object implementation = implementationClass.getDeclaredConstructor().newInstance();
//        return implementation;
        //Nova implementacija:
        return implementationClass;


    }

    private void printVerbose(Field field,  Object fieldInstance){
        boolean verbose = field.getDeclaredAnnotation(Autowired.class).verbose();
        if(!verbose) return;
        String type = fieldInstance.getClass().getSimpleName();
        String name = field.getName();
        String parentClass = field.getDeclaringClass().getSimpleName();
        String timeDate = LocalDateTime.now().toString();
        String hashCode = ((Integer)fieldInstance.hashCode()).toString();
        System.out.println("Initialized <" + type + ">  <" + name + "> in <" + parentClass + "> on <" + timeDate + "> with <" + hashCode + ">");
    }

    private Object initalizeSingletonDependency(Class<?> fieldClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(this.singletonDependencyList.isEmpty()){
            Object singletonObject = fieldClass.getDeclaredConstructor().newInstance();
            this.singletonDependencyList.add(singletonObject);
            return singletonObject;
        }
        for(Object object: this.singletonDependencyList){
            if(object.getClass().getName().equals(fieldClass.getName())){
                return object;
            }
        }
        Object singletonObject = fieldClass.getDeclaredConstructor().newInstance();
        this.singletonDependencyList.add(singletonObject);
        return singletonObject;
    }

    private Object serviceIsAlreadyInitialized(Field field){
        for(Object object: this.singletonDependencyList){
            if(object.getClass().getName().equals(field.getType().getName())){
                return object;
            }
        }
        return null;

    }




}
