package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:leankit.properties")
public class LeankitConfig {

    private String boardId;
    private String plannedLaneId;
    private String assignedDeveloper;
    private String leankitCredentials;
    private String cardTag;
    private long cardTypeId;

    public String getBoardId() {
        return boardId;
    }

    @Value("${student.board.id}")
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getPlannedLaneId() {
        return plannedLaneId;
    }

    @Value("${student.planned.lane.id}")
    public void setPlannedLaneId(String plannedLaneId) {
        this.plannedLaneId = plannedLaneId;
    }

    public String getAssignedDeveloper() {
        return assignedDeveloper;
    }

    @Value("${assigned.dev.card.id}")
    public void setAssignedDeveloper(String assignedDeveloper) {
        this.assignedDeveloper = assignedDeveloper;
    }

    public String getLeankitCredentials() {
        return leankitCredentials;
    }

    @Value("${lk.cred}")
    public void setLeankitCredentials(String leankitCredentials) {
        this.leankitCredentials = leankitCredentials;
    }

    public String getCardTag() {
        return cardTag;
    }

    @Value("${card.tag}")
    public void setCardTag(String cardTag) {
        this.cardTag = cardTag;
    }

    public long getCardTypeId() {
        return cardTypeId;
    }

    @Value("${card.type}")
    public void setCardTypeId(long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }
}
