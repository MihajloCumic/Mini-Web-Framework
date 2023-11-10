package exeptions.messages;

public class NoImplementationFound {
    private String qualifier;
    private String interfaceName;

    public NoImplementationFound(String qualifier, String interfaceName){
        this.qualifier = qualifier;
        this.interfaceName = interfaceName;
    }

    @Override
    public String toString() {
        return "Implementation of interface: " + this.interfaceName + "was not found using quialifier: " + this.qualifier;
    }
}
