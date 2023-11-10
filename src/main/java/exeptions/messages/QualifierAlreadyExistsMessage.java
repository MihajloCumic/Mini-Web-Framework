package exeptions.messages;

public class QualifierAlreadyExistsMessage {
    private String qualifier1;
    private String qualifier2;
    private String qualifierValue;
    public QualifierAlreadyExistsMessage(String qualifier1, String qualifier2, String qualifierValue){
        this.qualifier1 = qualifier1;
        this.qualifier2 = qualifier2;
        this.qualifierValue = qualifierValue;
    }

    @Override
    public String toString() {
        return "Class: " + this.qualifier1 + " and Class: " + this.qualifier2 + ", cannot have the same qualifier value : " + this.qualifierValue;
    }
}
