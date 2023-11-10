package dependencycontainer;

import annotations.Qualifier;
import exeptions.FrameWorkExeptions;
import exeptions.messages.QualifierAlreadyExistsMessage;
import scanner.PackageScanner;
import scanner.implementations.QualifierScanner;

import java.util.*;

public class DependencyContainer {
    private static DependencyContainer instance;
    private Map<String, Class<?>> qualifierToImplementationClass;
    private PackageScanner qualifierScanner;

    private DependencyContainer(String packageName) throws FrameWorkExeptions {
        this.qualifierScanner = new QualifierScanner(packageName);
        this.qualifierToImplementationClass = new HashMap<>();
        this.findAllImplementations();

    }

    public static synchronized DependencyContainer getInstance(String packageName) throws FrameWorkExeptions {
        if(instance == null){
            instance = new DependencyContainer(packageName);
        }
        return instance;
    }

    private void findAllImplementations() throws FrameWorkExeptions {
        Set<Class<?>> qualifiers = this.qualifierScanner.findAnnotatedClassesInPackage();
        for(Class<?> qualifier: qualifiers){
            String qualifierValue = qualifier.getDeclaredAnnotation(Qualifier.class).value();

            if(this.qualifierToImplementationClass.containsKey(qualifierValue)){
                String qualifier2 = this.qualifierToImplementationClass.get(qualifierValue).getSimpleName();
                String qualifier1 = qualifier.getSimpleName();
                QualifierAlreadyExistsMessage message = new QualifierAlreadyExistsMessage(qualifier1, qualifier2, qualifierValue);
                throw new FrameWorkExeptions(message.toString());
            }
            this.qualifierToImplementationClass.put(qualifierValue, qualifier);
        }
    }


    public Class<?> getImplementationClassByQualifier(String qualifier, String interfaceName) {
        Class<?> implementationClass = this.qualifierToImplementationClass.get(qualifier);
        if(implementationClass == null) return null;

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
