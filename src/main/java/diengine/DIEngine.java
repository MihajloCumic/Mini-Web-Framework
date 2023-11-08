package diengine;

import annotations.Autowired;
import annotations.Compoenent;
import annotations.Qualifier;
import annotations.Service;
import dependencycontainer.DependencyContainer;
import engine.controller.Controller;
import engine.controller.ControllerContainer;
import scanner.PackageScanner;
import scanner.implementations.QualifierScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
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

    public void injectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for(Controller controller: this.controllerContainer.getControllers()){
            Field[] controllerFields = controller.getControllerType().getDeclaredFields();
            traverseThroughDependencies(controllerFields, controller.getController());
        }
    }

    private void traverseThroughDependencies(Field[] fields, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        for(Field field: fields){
            Annotation annotation = field.getAnnotation(Autowired.class);
            if(annotation == null) continue;
            //Field[] fieldFields = field.getType().getDeclaredFields();

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

    private Object initializeDependency(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> fieldClass = field.getType();
        //|| Modifier.isAbstract(fieldClass.getModifiers()) dodati za abstract klase
        if(fieldClass.isInterface()) return initalizeInterface(field);


        Annotation service = fieldClass.getDeclaredAnnotation(Service.class);
        if(service != null) return initalizeSingletonDependency(field);

        Annotation component = fieldClass.getDeclaredAnnotation(Compoenent.class);
        if(component != null){
            Object componentObject = field.getType().getDeclaredConstructor().newInstance();
            return componentObject;
        }
        return null;
    }

    private Object initalizeInterface(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Qualifier qualifierAnnotation = field.getDeclaredAnnotation(Qualifier.class);
        if(qualifierAnnotation == null){
            System.out.println("Mora da ima qualifier uz sebe.");
            return null;
        }
        String qualifier = qualifierAnnotation.value();
        String interfaceName = field.getType().getName();
        Class<?> implementationClass = this.dependencyContainer.getImplementationClassByQualifier(qualifier, interfaceName);
        if(implementationClass == null) return null;
        Object implementation = implementationClass.getDeclaredConstructor().newInstance();
        return implementation;
    }

    private Object initalizeSingletonDependency(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(this.singletonDependencyList.isEmpty()){
            Object singletonObject = field.getType().getDeclaredConstructor().newInstance();
            this.singletonDependencyList.add(singletonObject);
            return singletonObject;
        }
        for(Object object: this.singletonDependencyList){
            if(object.getClass().getName().equals(field.getType().getName())){
                return object;
            }
        }
        Object singletonObject = field.getType().getDeclaredConstructor().newInstance();
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
