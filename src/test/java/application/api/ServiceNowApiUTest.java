package application.api;

import application.model.Ticket;
import application.services.ServiceNowService;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;


public class ServiceNowApiUTest{
/*
    private final String IDM_GROUP = "452d84880a0a3c22004c76b38ede3530";
    private final String INCIDENT_TABLE_NAME = "incident";  //All Incidents or INC tickets
    private final String SN_CREDENTIALS = "Basic amF2YV9kYXNoYm9hcmQ6elQ5YV5iYnFJWXg4";  //Java Dashboard
    private final String TEST_SHORT_DESCRIPTION = "Running tests for Java App Dev application";

    private String instance = "kstatedev";
    private Ticket testTicket;


    @Mock
    ServiceNowApi serviceNowApi;

    @Mock
    ServiceNowService serviceNowService;

    @Mock
    LambdaLogger logger;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        serviceNowApi = new ServiceNowApi(SN_CREDENTIALS);
        serviceNowService = new ServiceNowService(SN_CREDENTIALS, "kstatedev", logger);
    }

    @Test
    public void testPostRequest() throws IOException {
        String messageBody = "{\"assignment_group\":\"" + IDM_GROUP + "\",\"short_description\":\"" + TEST_SHORT_DESCRIPTION + "\",\"priority\":\"4\"}";
        String url = "https://" + instance + ".service-now.com/api/now/table/" + INCIDENT_TABLE_NAME;
        JSONObject response = serviceNowApi.postRequest(url, messageBody);

        Assert.assertEquals(201, response.getInt("ReplyCode"));
    }


    @Test
    public void testGetRequest () throws IOException {
        String url = "https://" + instance + ".service-now.com/api/now/table/" + INCIDENT_TABLE_NAME + "?sysparm_query=assignment_group%3D" + IDM_GROUP + "&sysparm_display_value=true";
        JSONObject data = serviceNowApi.getRequest(url);

        Assert.assertEquals(200, data.getInt("ReplyCode"));
    }


    @Test
    public void testPutRequest () throws IOException {
        Ticket testTicket = getCreatedIDMIncidentTicket();
        Assert.assertNotNull(testTicket);

        String url = "https://" + instance + ".service-now.com/api/now/table/" + INCIDENT_TABLE_NAME + "/" + testTicket.getSysId();
        String messageBody = "{\"assignment_group\":\"452d84880a0a3c22004c76b38ede3530\"}";
        JSONObject response = serviceNowApi.putRequest(url, messageBody);

        Assert.assertEquals(200, response.getInt("ReplyCode"));
    }


    @Test
    public void moveCreatedIDMTicketToClosed() throws IOException {
        testTicket = getCreatedIDMIncidentTicket();
        String putUrl = "https://" + instance + ".service-now.com/api/now/table/" + INCIDENT_TABLE_NAME + "/" + testTicket.getSysId();

        JSONObject response = null;

        if(testTicket != null){
            String messageBody = "{\"incident_state\":\"7\"}";
            response = serviceNowApi.putRequest(putUrl, messageBody);
        }
        Assert.assertEquals(200, response.getInt("ReplyCode"));

    }


    private Ticket getCreatedIDMIncidentTicket() throws IOException {
        List<Ticket> currentIDMTickets = serviceNowService.getListOfIdmTickets(INCIDENT_TABLE_NAME);
        for(Ticket ticket : currentIDMTickets){
            if (ticket.getShortDescription().equals(TEST_SHORT_DESCRIPTION)){
                return ticket;
            }
        }
        return null;
    }*/
}




