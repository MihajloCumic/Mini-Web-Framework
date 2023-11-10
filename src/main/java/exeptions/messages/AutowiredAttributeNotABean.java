package exeptions.messages;

public class AutowiredAttributeNotABean {
    private String attributeClassName;
    private String controllerName;

    public AutowiredAttributeNotABean(String attributeClassName, String controllerName){
        this.attributeClassName =attributeClassName;
        this.controllerName = controllerName;
    }

    @Override
    public String toString() {
        return "Attribute of type: " + this.attributeClassName + " in controller: " + this.controllerName + " is not a Bean.";
    }
}
