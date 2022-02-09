package application.model;


import org.json.JSONArray;

public class Card {

    private String title;
    private String description;
    private String dueDate;
    private JSONArray userId;
    private String cardId;
    private String number;


    public Card(String title, String description, String dueDate, JSONArray userId, String cardId, String number) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.userId = userId;
        this.cardId = cardId;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public JSONArray getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }
}
