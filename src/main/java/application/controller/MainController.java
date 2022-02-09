package application.controller;

import application.config.LeankitConfig;
import application.config.ServiceNowConfig;
import application.model.Ticket;
import application.services.LeankitService;
import application.services.ServiceNowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@PropertySource(value = "classpath:servicenow.properties")
@PropertySource(value = "classpath:leankit.properties")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private ServiceNowConfig serviceNowConfig;
    private LeankitConfig leankitConfig;
    private ServiceNowService serviceNowService;
    private LeankitService leankitService;


    public MainController (ServiceNowConfig serviceNowConfig, ServiceNowService serviceNowService, LeankitConfig leankitConfig,
                           LeankitService leankitService){
        this.serviceNowConfig = serviceNowConfig;
        this.leankitConfig = leankitConfig;
        this.serviceNowService = serviceNowService;
        this.leankitService = leankitService;
    }

    @RequestMapping(value = {"/welcome"})
    public String handleRequest(Model model) {
        logger.info("Starting application...");

        List<Ticket> idmIncidents = serviceNowService.getListOfIdmTickets(serviceNowConfig.getIncidentTable());
        if (idmIncidents != null){
            logger.info("Adding " + idmIncidents.size() + " incidents to the Leankit board");
            leankitService.updateBoard(idmIncidents);
        }

        List<Ticket> idmRequestItems = serviceNowService.getListOfIdmTickets(serviceNowConfig.getRequestItemTable());
        if (idmRequestItems != null){
            logger.info("Adding " + idmRequestItems.size() + " request items to the Leankit board");
            leankitService.updateBoard(idmRequestItems);
        }

        model.addAttribute("welcome", "Service Now Leankit Sync Application");
        model.addAttribute("time", "Cron Task :: Execution Time - " + dateTimeFormatter.format(LocalDateTime.now()));

        logger.info("End of application...");
        return "welcome";
    }



    @RequestMapping("/testProperties")
    public String testProperties (Model model){
        model.addAttribute("instance", serviceNowConfig.getInstance());
        model.addAttribute("serviceNowCred", serviceNowConfig.getServiceNowCredentials());
        model.addAttribute("incident", serviceNowConfig.getIncidentTable());
        model.addAttribute("reqItm", serviceNowConfig.getRequestItemTable());
        model.addAttribute("number", serviceNowConfig.getNumberField());
        model.addAttribute("shortDescription", serviceNowConfig.getShortDescriptionField());
        model.addAttribute("longDescription", serviceNowConfig.getLongDescriptionField());
        model.addAttribute("jsonArray", serviceNowConfig.getJsonArrayName());
        model.addAttribute("idmGroup", serviceNowConfig.getIdmGroup());
        model.addAttribute("appQaGroup", serviceNowConfig.getAppQaGroup());
        model.addAttribute("sysId", serviceNowConfig.getSysIdField());
        model.addAttribute("incidentState", serviceNowConfig.getIncidentStateField());


        model.addAttribute("boardId", leankitConfig.getBoardId());
        model.addAttribute("plannedLaneId", leankitConfig.getPlannedLaneId());
        model.addAttribute("assignedDeveloper", leankitConfig.getAssignedDeveloper());
        model.addAttribute("lkcred", leankitConfig.getLeankitCredentials());
        model.addAttribute("cardTag", leankitConfig.getCardTag());
        model.addAttribute("cardTypeId", leankitConfig.getCardTypeId());
        return "testProperties";
    }
}