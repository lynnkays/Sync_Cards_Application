package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:servicenow.properties")
public class ServiceNowConfig {

    private String instance;
    private String serviceNowCredentials;
    private String incidentTable;
    private String requestItemTable;
    private String numberField;
    private String shortDescriptionField;
    private String longDescriptionField;
    private String jsonArrayName;
    private String idmGroup;
    private String appQaGroup;
    private String sysIdField;
    private String incidentStateField;

    public String getInstance() {
        return instance;
    }

    @Value("${instance}")
    public void setInstance(String instance1) {
        this.instance = instance1;
    }

    public String getServiceNowCredentials() {
        return serviceNowCredentials;
    }

    @Value("${sn.cred}")
    public void setServiceNowCredentials(String serviceNowCredentials) {
        this.serviceNowCredentials = serviceNowCredentials;
    }

    public String getIncidentTable() {
        return incidentTable;
    }

    @Value("${incident.table}")
    public void setIncidentTable(String incidentTable) {
        this.incidentTable = incidentTable;
    }

    public String getRequestItemTable() {
        return requestItemTable;
    }

    @Value("${req.item.table}")
    public void setRequestItemTable(String requestItemTable) {
        this.requestItemTable = requestItemTable;
    }

    public String getNumberField() {
        return numberField;
    }

    @Value("${number.field}")
    public void setNumberField(String numberField) {
        this.numberField = numberField;
    }

    public String getShortDescriptionField() {
        return shortDescriptionField;
    }

    @Value("${short.description.field}")
    public void setShortDescriptionField(String shortDescriptionField) {
        this.shortDescriptionField = shortDescriptionField;
    }

    public String getLongDescriptionField() {
        return longDescriptionField;
    }

    @Value("${long.description.field}")
    public void setLongDescriptionField(String longDescriptionField) {
        this.longDescriptionField = longDescriptionField;
    }

    public String getJsonArrayName() {
        return jsonArrayName;
    }

    @Value("${json.array.name}")
    public void setJsonArrayName(String jsonArrayName) {
        this.jsonArrayName = jsonArrayName;
    }

    public String getIdmGroup() {
        return idmGroup;
    }

    @Value("${idm.group.num}")
    public void setIdmGroup(String idmGroup) {
        this.idmGroup = idmGroup;
    }

    public String getAppQaGroup() {
        return appQaGroup;
    }

    @Value("${app.qa.group.num}")
    public void setAppQaGroup(String appQaGroup) {
        this.appQaGroup = appQaGroup;
    }

    public String getSysIdField() {
        return sysIdField;
    }

    @Value("${sys.id.field}")
    public void setSysIdField(String sysIdField) {
        this.sysIdField = sysIdField;
    }

    public String getIncidentStateField() {
        return incidentStateField;
    }

    @Value("${incident.state.field}")
    public void setIncidentStateField(String incidentStateField) {
        this.incidentStateField = incidentStateField;
    }
}
