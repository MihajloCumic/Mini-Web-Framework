package ditests.dependencies.components;

import annotations.Compoenent;

@Compoenent
public class ComponentDependency2 {
    private String name = "ComponentDependency2";

    public ComponentDependency2(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }
}
