package application.services;

import application.api.ServiceNowApi;
import application.config.ServiceNowConfig;
import application.controller.MainController;
import application.model.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceNowService {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private ServiceNowConfig serviceNowConfig;
    private ServiceNowApi serviceNowApi;

    public ServiceNowService(ServiceNowConfig serviceNowConfig) {
        this.serviceNowConfig = serviceNowConfig;
        this.serviceNowApi = new ServiceNowApi(serviceNowConfig.getServiceNowCredentials());
    }

    public List<Ticket> getListOfIdmTickets(String tableName)  {
        List<Ticket> idmTickets = new ArrayList<>();
        try{
            JSONArray appQATickets = getServiceNowData(tableName, serviceNowConfig.getAppQaGroup());
            if(tableName.equals("incident") && appQATickets.length() != 0){
                assignAppQAItemsToIDM(tableName);
            }


            JSONArray arr = getServiceNowData(tableName, serviceNowConfig.getIdmGroup());

            if(arr.length() == 0){
                return null;
            }

            for (int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                String state = obj.getString("state");

                if(!state.equals("Closed") && !state.equals("Closed Complete")){
                    Ticket ticket = createTicketObject(obj);
                    idmTickets.add(ticket);
                }
            }
        } catch (IOException ex){
        }
        return idmTickets;
    }

    private void assignAppQAItemsToIDM(String tableName) throws IOException {
        JSONArray arr = getServiceNowData(tableName, serviceNowConfig.getAppQaGroup());
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String messageBody = "{\"assignment_group\":\"452d84880a0a3c22004c76b38ede3530\"}\n";
            updateServiceNowData(obj.getString("sys_id"), messageBody, tableName, true);
        }
    }

    public JSONArray getServiceNowData (String tableName, String groupNumber) throws IOException {
        String getUrl = "https://" + serviceNowConfig.getInstance() +".service-now.com/api/now/table/" + tableName + "?sysparm_query=assignment_group%3D" + groupNumber + "&sysparm_display_value=true";
        JSONObject data = serviceNowApi.getRequest(getUrl);
        if(data.getInt("ReplyCode") != 200){
            throw new IOException();
        }
        return data.getJSONArray(serviceNowConfig.getJsonArrayName());
    }

    public void updateServiceNowData (String sysId,  String messageBody, String tableName, boolean isPutUrl) throws IOException {
        JSONObject response;
        String postUrl = "https://" + serviceNowConfig.getInstance() + ".service-now.com/api/now/table/" + tableName;

        if (isPutUrl) {
            String putUrl = postUrl + "/" + sysId;
            response = serviceNowApi.putRequest(putUrl, messageBody);

            if(response.getInt("ReplyCode") != 200){
                throw new IOException();
            }
        } else {
            response = serviceNowApi.postRequest(postUrl, messageBody);
            if(response.getInt("ReplyCode") != 201){
                throw new IOException();
            }
        }
    }

    public Ticket createTicketObject (JSONObject obj){

        String ticketNum = obj.getString(serviceNowConfig.getNumberField());
        String shortDescription = obj.getString(serviceNowConfig.getShortDescriptionField());
        String longDescription = obj.getString(serviceNowConfig.getLongDescriptionField());
        String sysId = obj.getString(serviceNowConfig.getSysIdField());
        String incidentState = obj.getString(serviceNowConfig.getIncidentStateField());
        return new Ticket(ticketNum, shortDescription, longDescription, sysId, incidentState);
    }
}
