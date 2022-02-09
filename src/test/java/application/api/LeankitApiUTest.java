package application.api;

import application.config.LeankitConfig;
import application.config.ServiceNowConfig;
import application.controller.MainController;
import application.services.LeankitService;
import application.services.ServiceNowService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class LeankitApiUTest {

    @Mock
    LeankitApi leankitApi;

    @Mock
    LeankitService leankitService;

    @Mock
    MainController mainController;

    @Mock
    LeankitConfig leankitConfig;

    @Mock
    Model model;

    @Mock
    ServiceNowService serviceNowService;

    @Mock
    ServiceNowConfig serviceNowConfig;




    private final String BOARD_ID = "686446611"; //AppDev Student Experimentation Board
    private final String LANE_ID = "686481315"; //Unplanned lane inside AppDev Student Experimentation Board
    private final String CARD_ID = "842792731";  //Assignment Card in the AppDev Student Experimentation Board
    private final  String LK_CREDENTIALS = "Basic b21lLWFzYUBrc3UuZWR1Ok51NXFRZ3hZRllLb0k="; //OME Bot


/*    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        leankitApi = new LeankitApi(LK_CREDENTIALS);
        serviceNowService = new ServiceNowService(serviceNowConfig);
        //leankitService = new LeankitService(leankitConfig);

    }

    @Test
    public void testEverything () {
        mainController = new MainController(serviceNowConfig, serviceNowService, leankitConfig, leankitService);
        mainController.handleRequest(model);

    }

    @Test
    public void testGetBoardRequest () throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/boards/%s", BOARD_ID);
        JSONObject obj = leankitApi.getRequest(url);
        int replyCode = obj.getInt("ReplyCode");

        Assert.assertEquals(200, replyCode);
    }

    @Test
    public void testGetCardRequest () throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/GetCard/%s", BOARD_ID, CARD_ID);
        JSONObject obj = leankitApi.getRequest(url);
        int replyCode = obj.getInt("ReplyCode");

        Assert.assertEquals(200, replyCode);
    }

    @Test
    public void testPostCardRequest () throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/AddCard/lane/%s/position/0", BOARD_ID, LANE_ID);
        String messageBody = "{\"Title\": \"INC12345 - Testing Post Request from LeankitApiUTest\",\"Description\": \"Some Description\",\"TypeId\": 686504088,\"IsBlocked\": true,\"BlockReason\": \"DELETE THIS CARD\",\"DueDate\": \"01/01/2020\"}";
        JSONObject obj = leankitApi.postRequest(url, messageBody);
        int id  = obj.getInt("ReplyCode");
        Assert.assertEquals(201, id);

    }


    @Test
    public void deleteCreatedCards() throws IOException {
        List<Card> getCards = leankitService.getCardsOnBoard();
        List<Card> cardsToDelete = new ArrayList<>();
        for (Card cardOnBoard : getCards){
            if(!cardOnBoard.getCardId().equals(CARD_ID)){
                cardsToDelete.add(cardOnBoard);
            }
        }
        leankitService.deleteCardsFromBoard(cardsToDelete);
    }*/
}
