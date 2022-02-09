package application.services;

import application.model.Ticket;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ServiceNowServiceUTest {
    /*private String INSTANCE = "kstatedev"; //Dev instance of SN
    private JSONArray snGetAppQAData;
    private JSONArray snGetIDMData;

    private final String SN_CREDENTIALS = "Basic amF2YV9kYXNoYm9hcmQ6elQ5YV5iYnFJWXg4";  //Java Dashboard
    private final String INCIDENT_TABLE_NAME = "incident";  //All Incidents or INC tickets
    private final String REQ_ITM_TABLE_NAME = "sc_req_item"; //All Request Items or RITM tickets
    private final String IDM_GROUP = "452d84880a0a3c22004c76b38ede3530";


    @Mock
    ServiceNowService serviceNowService;

    @Mock
    MockServiceNowService mockServiceNowService;

    @Mock
    LambdaLogger logger;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        snGetIDMData = new JSONArray();
        snGetAppQAData = new JSONArray();

        serviceNowService = new ServiceNowService(SN_CREDENTIALS, INSTANCE, logger);
        mockServiceNowService = new MockServiceNowService(SN_CREDENTIALS, INSTANCE, logger);
    }


    @Test
    public void testEmptyIncidentJSONArray () {
        List<Ticket> actual = mockServiceNowService.getListOfIdmTickets(INCIDENT_TABLE_NAME);
        Assert.assertNull(actual);
    }

    @Test
    public void testEmptyRequestItemJSONArray () {
        List<Ticket> actual = mockServiceNowService.getListOfIdmTickets(REQ_ITM_TABLE_NAME);
        Assert.assertNull(actual);
    }

    @Test
    public void testGetListOfIdmTickets () throws IOException {
        List<Ticket> expectedTickets = setupListOfExpectedIncidents();
        List<Ticket> actualResults = serviceNowService.getListOfIdmTickets(INCIDENT_TABLE_NAME);
        Assert.assertEquals(expectedTickets.size(), actualResults.size());
    }

    @Test
    public void testGetListOfIdmRequestItems () throws IOException {
        List<Ticket> expectedTickets = setupListOfExpectedRequestItems();
        List<Ticket> actualResults = serviceNowService.getListOfIdmTickets(REQ_ITM_TABLE_NAME);
        Assert.assertEquals(expectedTickets.size(), actualResults.size());
    }

    @Test(expected = IOException.class)
    public void testBadGetApiCall () throws IOException {
        serviceNowService.getServiceNowData("BadTableName", "BadGroupNumber");
    }

    @Test(expected = IOException.class)
    public void testBadPutApiCall () throws IOException {
        serviceNowService.updateServiceNowData("BadSysId", "BadMessageBody", "BadTableName", true);
    }

    @Test(expected = IOException.class)
    public void testBadPostApiCall () throws IOException {
        serviceNowService.updateServiceNowData("BadSysId", "BadMessageBody", "BadTableName", false);
    }

    private List<Ticket> setupListOfExpectedIncidents () throws IOException {
        JSONArray expectedResults = serviceNowService.getServiceNowData(INCIDENT_TABLE_NAME, IDM_GROUP);
        List<Ticket> expectedTickets = new ArrayList<>();
        for (int i = 0; i < expectedResults.length(); i++){
            JSONObject obj = expectedResults.getJSONObject(i);

            if(!obj.getString("state").equals("Closed")){
                Ticket ticket = serviceNowService.createTicketObject(obj);
                expectedTickets.add(ticket);
            }
        }
        return expectedTickets;
    }

    private List<Ticket> setupListOfExpectedRequestItems () throws IOException {
        JSONArray expectedResults = serviceNowService.getServiceNowData(REQ_ITM_TABLE_NAME, IDM_GROUP);
        List<Ticket> expectedTickets = new ArrayList<>();
        for (int i = 0; i < expectedResults.length(); i++){
            JSONObject obj = expectedResults.getJSONObject(i);

            if(!obj.getString("state").equals("Closed Complete")){
                Ticket ticket = serviceNowService.createTicketObject(obj);
                expectedTickets.add(ticket);
            }
        }
        return expectedTickets;
    }


    public class MockServiceNowService extends ServiceNowService {

        private MockServiceNowService(String authorizationCode, String instance, LambdaLogger logger) {
            super(authorizationCode, instance, logger);
        }

        public List<Ticket> getListOfIdmTickets(String tableName)  {

            JSONArray appQATickets = snGetAppQAData;
            if (tableName.equals("incident") && appQATickets.length() != 0) {
                assignAppQAItemsToIDM();
            }

            List<Ticket> idmTickets = new ArrayList<>();
            JSONArray arr = snGetIDMData;

            if (arr.length() == 0) {
                return null;
            }

            for (int i = 0; i < arr.length(); i++) {
                Ticket ticket = createTicketObject(arr.getJSONObject(i));
                idmTickets.add(ticket);
            }
            return idmTickets;
        }

        private void assignAppQAItemsToIDM() {
        }

    }*/
}
