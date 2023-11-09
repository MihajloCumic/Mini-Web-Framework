package ditests.dependencies;

import annotations.Autowired;
import annotations.Service;
import ditests.dependencies.components.ComponentDependency1;

@Service
public class ServiceDependency1 {
    private String name = "ServiceDependency1";
    @Autowired
    private ComponentDependency1 componentDependency1;

    public ServiceDependency1(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }

    public void ispisi(){
        System.out.println("Ispis iz ServiceDependency1.");
        System.out.println("ServiceDependency1 poziva ComponentDependency1:");
        this.componentDependency1.ispisi();
    }
}
