package diengine;

import annotations.Autowired;
import annotations.Compoenent;
import annotations.Service;
import engine.controller.Controller;
import engine.controller.ControllerContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DIEngine {

    private static DIEngine instance;
    private ControllerContainer controllerContainer;
    private List<Object> singletonDependencyList;

    private DIEngine(){
        this.controllerContainer = ControllerContainer.getInstance();
        this.singletonDependencyList = new ArrayList<>();
    }

    public static synchronized DIEngine getInstance(){
        if(instance == null){
            instance = new DIEngine();
        }
        return instance;
    }

    public void injectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for(Controller controller: this.controllerContainer.getControllers()){
            Field[] controllerFields = controller.getControllerType().getDeclaredFields();
            System.out.println(controller.getControllerType().getSimpleName());
            System.out.println(this.singletonDependencyList);

            traverseThroughDependencies(controllerFields, controller.getController());
        }
    }

    private void traverseThroughDependencies(Field[] fields, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        for(Field field: fields){
            Annotation annotation = field.getAnnotation(Autowired.class);
            if(annotation == null) continue;
            Field[] fieldFields = field.getType().getDeclaredFields();
            //Object fieldObject = field.getType().getDeclaredConstructor().newInstance();
            Object existingService = serviceIsAlreadyInitialized(field);
            Object fieldObject;

            if(existingService == null){
                fieldObject = initializeDependency(field);
                traverseThroughDependencies(fieldFields, fieldObject);
            }else{
                fieldObject = existingService;
            }
            //Object fieldObject = initializeDependency(field);



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
        Annotation service = fieldClass.getDeclaredAnnotation(Service.class);
        if(service != null){
            return initalizeSingletonDependency(field);
        }
        Annotation component = fieldClass.getDeclaredAnnotation(Compoenent.class);
        if(component != null){
            Object componentObject = field.getType().getDeclaredConstructor().newInstance();
            return componentObject;
        }
        return null;
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
