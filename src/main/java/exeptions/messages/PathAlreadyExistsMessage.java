package exeptions.messages;

public class PathAlreadyExistsMessage {
    private String path;
    private String controllerName;
    private String methodName;
    public PathAlreadyExistsMessage(String path, String controllerName, String methodName){
        this.path = path;
        this.controllerName = controllerName;
        this.methodName = methodName;

    }

    @Override
    public String toString() {
        return "Path: " + this.path + ", already exists in controller " + this.controllerName + "in method " + this.methodName;
    }
}
