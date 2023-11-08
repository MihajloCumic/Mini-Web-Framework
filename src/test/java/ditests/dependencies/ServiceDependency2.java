package ditests.dependencies;

import annotations.Service;

@Service
public class ServiceDependency2 {
    private String name = "ServiceDependency2";

    public ServiceDependency2(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }
}
