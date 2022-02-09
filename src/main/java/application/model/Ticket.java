package application.model;

public class Ticket {

    private String number;
    private String shortDescription;
    private String longDescription;
    private String openedBy;
    private String sysId;
    private String incidentState;


    public Ticket (String number, String shortDescription, String longDescription, String sysId, String incidentState){
        this.number = number;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.sysId = sysId;
        this.incidentState = incidentState;
    }

    public String getIncidentState() {
        return incidentState;
    }

    public void setIncidentState(String incidentState) {
        this.incidentState = incidentState;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getNumber() {
        return number;
    }

}
