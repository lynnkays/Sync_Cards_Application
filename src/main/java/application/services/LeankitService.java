package application.services;

import application.api.LeankitApi;
import application.config.LeankitConfig;
import application.model.Card;
import application.model.Ticket;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeankitService {

    private LeankitConfig leankitConfig;

    private LeankitApi leankitApi;

    public LeankitService(LeankitConfig leankitConfig){
        this.leankitConfig = leankitConfig;
        leankitApi = new LeankitApi(leankitConfig.getLeankitCredentials());
    }

    public List<Card> updateBoard (List<Ticket> incomingSNTickets) {
        List<Card> updatedCards = new ArrayList<>();
        try {
            List<Card> allCards = new ArrayList<>();
            JSONArray userId = getAssignedDeveloper();
            Card card;

            for (Ticket ticket : incomingSNTickets){
                card = createCard(ticket, userId);
                allCards.add(card);
            }

            updatedCards = searchCards(allCards);
            addCardsToLeankitBoard(updatedCards, leankitConfig.getPlannedLaneId());
        } catch (IOException ex){
        }

        return updatedCards;
    }

    public JSONArray getAssignedDeveloper() throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/GetCard/%s", leankitConfig.getBoardId(), leankitConfig.getAssignedDeveloper());

        JSONObject obj = leankitApi.getRequest(url);
        if(obj.getInt("ReplyCode") != 200){
            throw new IOException();
        }
        int id  = JsonPath.read(obj.toString(), String.format("ReplyData[0].AssignedUsers[0].Id"));
        JSONArray arr = new JSONArray();
        arr.put(id);

        return arr;
    }

    public Card createCard (Ticket ticket, JSONArray userId){
        String number = ticket.getNumber();
        String shortDescription = ticket.getShortDescription();
        String longDescription = ticket.getLongDescription();

        if(number.equals("")){
            throw new NullPointerException();
        } else if (shortDescription.equals("")){
            shortDescription = "There is no short description available";
        } else if (shortDescription.length() > 100){
            shortDescription = shortDescription.substring(0, 100);
        } else if (longDescription.equals("")){
            longDescription = "There is no long description available";
        }

        String title = number + " - " + shortDescription;
        return new Card(title, longDescription, createDueDate(), userId, null, number);

    }

    private String createDueDate (){
        LocalDate today = LocalDate.now();
        LocalDate next2Week = today.plus(2, ChronoUnit.WEEKS);

        next2Week.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        String date = next2Week.format(formatter);
        return date;
    }


    public List<Card> searchCards (List<Card> incomingCards) throws IOException {
        String url = "https://ksu.leankit.com/kanban/api/board/" + leankitConfig.getBoardId() + "/searchcards";
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

    public List<Card> getCardsOnBoard () throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/boards/%s", leankitConfig.getBoardId());
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

    public void addCardsToLeankitBoard(List<Card> cards, String laneId) throws IOException {
        if(cards != null && !cards.isEmpty()){
            String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/AddCard/lane/%s/position/0", leankitConfig.getBoardId(), laneId);
            for(Card card : cards){
                String requestBody = new JSONObject()
                        .put("Title", card.getTitle())
                        .put("TypeId", leankitConfig.getCardTypeId())
                        .put("Description", card.getDescription())
                        .put("DueDate", card.getDueDate())
                        .put("Tags", leankitConfig.getCardTag())
                        .put("AssignedUserIds", card.getUserId()).toString();

                JSONObject response = leankitApi.postRequest(url, requestBody);
                if(response.getInt("ReplyCode") != 200){
                    throw new IOException();
                }
            }
        }
    }

    public void deleteCardsFromBoard (List<Card> cardsToDelete) throws IOException {
        String url = String.format("https://ksu.leankit.com/kanban/api/board/%s/deletecards", leankitConfig.getBoardId());
        List<String> idsToDelete = new ArrayList<>();
        for(Card card : cardsToDelete){
            if(card.getCardId() == null){
                throw new NullPointerException();
            }
            idsToDelete.add(card.getCardId());
        }

        JSONArray arr = new JSONArray(idsToDelete);
        JSONObject response = leankitApi.postRequest(url, arr.toString());
        if(response.getInt("ReplyCode") != 203){
            throw new IOException();
        }
    }
}
