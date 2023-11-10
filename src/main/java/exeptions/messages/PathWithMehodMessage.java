package exeptions.messages;

public class PathWithMehodMessage {
    private String controllerName;
    private String methodName;

    public PathWithMehodMessage(String controllerName, String methodName){
        this.controllerName = controllerName;
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "Missing @Path or @GET/POST on method: " + this.methodName + " , in controller: " + this.controllerName;
    }
}
