package diengine;

import annotations.Autowired;
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

            //traverseThroughDependencies(controllerFields, controller.getControllerType());
            traverseThroughDependencies(controllerFields, controller.getController());
        }
    }

    private Object traverseThroughDependencies(Field[] fields, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //Object dependency = clazz.getDeclaredConstructor().newInstance();
        for(Field field: fields){
            Annotation annotation = field.getAnnotation(Autowired.class);
            if(annotation == null) continue;
            Field[] fieldFields = field.getType().getDeclaredFields();
            Object fieldObject = field.getType().getDeclaredConstructor().newInstance();
            Object fieldDependency = traverseThroughDependencies(fieldFields, fieldObject);
            if(fieldDependency == null) continue;
            //System.out.println(field.isAccessible());

            field.setAccessible(true);
            field.set(object, fieldDependency);


        }
        return object;
    }


}
