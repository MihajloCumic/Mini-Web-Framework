package ditests.dependencies;

import annotations.Service;

@Service
public class ServiceDependency1 {
    private String name = "ServiceDependency1";

    public ServiceDependency1(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }
}
