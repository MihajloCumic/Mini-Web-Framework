package exeptions.messages;

public class InnterfaceAttributeMustHaveQualifier {
    private String interfaceName;
    private String controllerName;
    public InnterfaceAttributeMustHaveQualifier(String interfaceName, String controllerName){
        this.interfaceName = interfaceName;
        this.controllerName = controllerName;
    }

    @Override
    public String toString() {
        return "Attribute: " + this.interfaceName + "in controller " + this.controllerName + "does not have  @Qualifier annotation.";
    }
}
