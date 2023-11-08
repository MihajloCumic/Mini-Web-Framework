package ditests.dependencies.components;

import annotations.Compoenent;

@Compoenent
public class ComponentDependency1 {
    private String name = "ComponentDependency1";

    public ComponentDependency1(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }
}
