package application.services;

import application.api.LeankitApi;
import application.model.Card;
import application.model.Ticket;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.jayway.jsonpath.JsonPath;
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

public class LeankitServiceUTest {
/*
    @Mock
    LeankitService leankitService;

    @Mock
    LeankitApi leankitApi;

    @Mock
    LambdaLogger logger;

    private MockLeankitService mockLeankitService;
    private JSONArray assignedUsers = new JSONArray();

    private final String BOARD_ID = "686446611"; //AppDev Student Experimentation Board
    private final  String LK_CREDENTIALS = "Basic b21lLWFzYUBrc3UuZWR1Ok51NXFRZ3hZRllLb0k="; //OME Bot
    private final String CARD_ID = "842792731";  //Assignment Card in the AppDev Student Experimentation Board
    private final String USER_ID = "104012288"; //OME Bot UserId
    private final String LANE_ID = "686481315"; //Unplanned lane inside AppDev Student Experimentation Board




    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        leankitService = new LeankitService(BOARD_ID, CARD_ID, LK_CREDENTIALS, logger);
        mockLeankitService = new MockLeankitService(BOARD_ID, CARD_ID, LK_CREDENTIALS, logger);
        leankitApi = new LeankitApi(LK_CREDENTIALS);
        assignedUsers.put(USER_ID);



    }

    @Test(expected = NullPointerException.class)
    public void testEmptyTicketNumber() {
        Ticket ticket = new Ticket("", "shortDescription", "longDescription", "java_dashboard", "dklsjfldksjf", "New");
        leankitService.createCard(ticket, assignedUsers);
    }

    @Test
    public void testEmptyShortDescription() {
        Ticket ticket = new Ticket("INC12345", "", "longDescription", "java_dashboard", "jsdlkfjskdljflks", "New");
        Card card = leankitService.createCard(ticket, assignedUsers);
        String title = card.getTitle();
        String[] items = title.split(" - ");

        Assert.assertEquals("There is no short description available", items[1]);
    }

    @Test
    public void testEmptyLongDescription() {
        Ticket ticket = new Ticket("INC12345", "shortDescription", "", "java_dashboard", "jldkjflskdjflkdsf", "New");
        Card card = leankitService.createCard(ticket, assignedUsers);

        Assert.assertEquals("There is no long description available", card.getDescription());

    }

    @Test
    public void testLongShortDescription() {
        Ticket ticket = new Ticket("INC12345", "Over100CharactersLong487349857938475983475983475983724875938475493875290437327" +
                "745873948759384759837495873458739485734987590384759083475983745987349587394587", "longDescription", "java_dashboard", "jlkdsfjkldsjfkl", "New");
        Card card = leankitService.createCard(ticket, assignedUsers);

        String title = card.getTitle();
        String[] items = title.split(" - ");
        Assert.assertEquals(100, items[1].length());
    }

    @Test
    public void createCard_HappyPath() {
        Ticket ticket = new Ticket("INC12345", "shortDescription", "longDescription", "java_dashboard", "jlkjlksjfdlksdjflkds", "New");
        Card card = leankitService.createCard(ticket, assignedUsers);

        Assert.assertEquals("INC12345 - shortDescription", card.getTitle());
        Assert.assertEquals("longDescription", card.getDescription());
        Assert.assertEquals(assignedUsers, card.getUserId());

    }



    @Test(expected = IOException.class)
    public void testExceptionGetAssignedDeveloper () throws IOException {
        mockLeankitService.getAssignedDeveloper("BadBoardId", "BadCardId");
    }

    @Test(expected = IOException.class)
    public void testExceptionSearchCards () throws IOException {
        List<Card> testList = new ArrayList<>();
        Card card = new Card("Title", "Description", "01/01/2020", assignedUsers,"CardId", "INC123454");
        testList.add(card);
        mockLeankitService.searchCards(testList, "BadBoardId");
    }

    @Test(expected = IOException.class)
    public void testExceptionGetCardsOnBoard () throws IOException {
        mockLeankitService.getCardsOnBoard("BadBoardId");
    }

    @Test(expected = IOException.class)
    public void testExceptionAddCardsToLeankitBoard () throws IOException {
        List<Card> testList = new ArrayList<>();
        Card card = new Card("Title", "Description", "01/01/2020", assignedUsers,"CardId", "INC123454");
        testList.add(card);
        leankitService.addCardsToLeankitBoard(testList, "BadLaneId");
    }

    @Test(expected = IOException.class)
    public void testExceptionDeleteCardsFromBoard () throws IOException {
        List<Card> testList = new ArrayList<>();
        Card card = new Card("Title", "Description", "01/01/2020", assignedUsers,"CardId", "INC123454");
        testList.add(card);
        mockLeankitService.deleteCardsFromBoard(testList, "BadBoardId");
    }

    @Test
    public void testFindAssignedDeveloper () throws IOException {
        JSONArray arr = leankitService.getAssignedDeveloper();
        Assert.assertEquals(assignedUsers.toString().replace("\"", ""), arr.toString());
    }

    @Test
    public void testFindCard() throws IOException {
        Assert.assertTrue(createCard());

        Card card1 = new Card("INC12345 - Testing Post Request from LeankitServiceUTest", null, null, null, null, "INC12345");
        Card card2 = new Card("INC54321 - Should add this Card", null, null, null, null, "INC54321");
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        List<Card> updatedCards = leankitService.searchCards(cards);

        Assert.assertEquals(1, updatedCards.size());
        Assert.assertEquals("INC54321 - Should add this Card", updatedCards.get(0).getTitle());
        deleteCreatedCards();
    }


    private boolean createCard () throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/AddCard/lane/%s/position/0", BOARD_ID, LANE_ID);
        String messageBody = "{\"Title\": \"INC12345 - Testing Post Request from LeankitServiceUTest\",\"Description\": \"Some Description\",\"TypeId\": 686504088,\"IsBlocked\": true,\"BlockReason\": \"DELETE THIS CARD\",\"DueDate\": \"01/01/2020\"}";
        JSONObject obj = leankitApi.postRequest(url, messageBody);
        int id  = obj.getInt("ReplyCode");

        if(id == 201){
            return true;
        }
        return false;
    }


    private void deleteCreatedCards() throws IOException {
        List<Card> getCards = leankitService.getCardsOnBoard();
        List<Card> cardsToDelete = new ArrayList<>();
        for (Card cardOnBoard : getCards){
            if(!cardOnBoard.getCardId().equals(CARD_ID)){
                cardsToDelete.add(cardOnBoard);
            }
        }
        leankitService.deleteCardsFromBoard(cardsToDelete);
    }

    public class MockLeankitService extends LeankitService {


        public MockLeankitService(String boardId, String cardId, String authorizationCode, LambdaLogger logger) {
            super(boardId, cardId, authorizationCode, logger);
        }

        public JSONArray getAssignedDeveloper(String boardId, String cardId) throws IOException {
            String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/GetCard/%s", boardId, cardId);

            JSONObject obj = leankitApi.getRequest(url);
            if(obj.getInt("ReplyCode") != 200){
                throw new IOException();
            }
            int id  = JsonPath.read(obj.toString(), String.format("ReplyData[0].AssignedUsers[0].Id"));
            JSONArray arr = new JSONArray();
            arr.put(id);

            return arr;
        }

        public List<Card> searchCards (List<Card> incomingCards, String boardId) throws IOException {
            String url = "https://ksu.leankit.com/kanban/api/board/" + boardId + "/searchcards";
            List<Card> updatedList = new ArrayList<>();
            for (Card incomingCard : incomingCards){
                String messageBody = "{\"searchOptions\": {\"SearchTerm\": \"" + incomingCard.getNumber()+ "\",\"SearchInBoard\":\"true\",\"Page\": 1 }}";
                JSONObject object= leankitApi.postRequest(url, messageBody);

                if(object.getInt("ReplyCode") != 200){
                    throw new IOException();
                }
                int totalResults = JsonPath.read(object.toString(), String.format("ReplyData[0].TotalResults"));
                if(totalResults == 0){
                    updatedList.add(incomingCard);
                }
            }
            return updatedList;
        }

        public List<Card> getCardsOnBoard (String boardId) throws IOException {
            String url = String.format("https://ksu.leankit.com/kanban/api/boards/%s", boardId);
            JSONObject data = leankitApi.getRequest(url);
            if(data.getInt("ReplyCode") != 200){
                throw new IOException();
            }
            JSONArray lanes = data.getJSONArray("ReplyData").getJSONObject(0).getJSONArray("Lanes");

            List<Card> cardsOnBoard = new ArrayList<>();

            for (int i = 0; i < lanes.length(); i++){
                JSONObject lanesObj = lanes.getJSONObject(i);
                JSONArray cards = lanesObj.getJSONArray("Cards");

                for(int j = 0; j < cards.length(); j++){
                    JSONObject card = cards.getJSONObject(j);
                    String title = card.getString("Title");
                    String id = card.get("Id").toString();
                    Card newCard = new Card(title, null, null, null, id, null);
                    cardsOnBoard.add(newCard);
                }
            }
            return cardsOnBoard;
        }

        public void deleteCardsFromBoard (List<Card> cardsToDelete, String boardId) throws IOException {
            String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/deletecards", boardId);
            List<String> idsToDelete = new ArrayList<>();
            for(Card card : cardsToDelete){
                if(card.getCardId() == null){
                    throw new NullPointerException("Card id cannot be null");
                }
                idsToDelete.add(card.getCardId());
            }

            JSONArray arr = new JSONArray(idsToDelete);
            JSONObject response = leankitApi.postRequest(url, arr.toString());
            if(response.getInt("ReplyCode") != 203){
                throw new IOException();
            }
        }
    }*/
}