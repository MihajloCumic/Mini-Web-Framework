package dependencycontainer;

import annotations.Qualifier;
import engine.controller.ControllerContainer;
import org.eclipse.core.internal.registry.osgi.OSGIUtils;
import scanner.PackageScanner;
import scanner.implementations.QualifierScanner;

import java.lang.annotation.Annotation;
import java.util.*;

public class DependencyContainer {
    private static DependencyContainer instance;
    //private List<Class<?>> implementations;
    private Map<String, Class<?>> qualifierToImplementationClass;
    private PackageScanner qualifierScanner;

    private DependencyContainer(String packageName){
        //this.implementations = new ArrayList<>();
        this.qualifierScanner = new QualifierScanner(packageName);
        this.qualifierToImplementationClass = new HashMap<>();
        this.findAllImplementations();
    }

    public static synchronized DependencyContainer getInstance(String packageName){
        if(instance == null){
            instance = new DependencyContainer(packageName);
        }
        return instance;
    }

    private void findAllImplementations(){
        Set<Class<?>> qualifiers = this.qualifierScanner.findAnnotatedClassesInPackage();
        for(Class<?> qualifier: qualifiers){
            String qualifierValue = qualifier.getDeclaredAnnotation(Qualifier.class).value();
            if(this.qualifierToImplementationClass.containsKey(qualifierValue)){
                System.out.println("Alraedy contains implemenntation with that qualifier.");
                continue;
            }
            this.qualifierToImplementationClass.put(qualifierValue, qualifier);
        }
        System.out.println(qualifiers);
        System.out.println(this.qualifierToImplementationClass);
    }


    public Class<?> getImplementationClassByQualifier(String qualifier, String interfaceName) {
        Class<?> implementationClass = this.qualifierToImplementationClass.get(qualifier);
        if(implementationClass == null) return null;

        String implementationName = implementationClass.getName();

        Class<?>[] interfaces = implementationClass.getInterfaces();
        for(Class<?> iface: interfaces){
            if(iface.getName().equals(interfaceName)){
                return implementationClass;
            }
        }

        System.out.println("Implementation matched by qualifier doesnt implement correct interface.");
        return null;
    }




}
