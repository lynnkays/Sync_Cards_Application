package application.config;

import application.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SchedulerConfig {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private MainController mainController;
    private Model model;

    public SchedulerConfig(MainController mainController){
        model = new ExtendedModelMap();
        this.mainController = mainController;
    }

    //@Scheduled(cron = "0 * * * * ?") <--- Every minute
    @Scheduled(cron = "0 0/15 6-19 ? * MON,TUE,WED,THU,FRI *")
    public void timer () {
        reloadPage();
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    private String reloadPage (){
        mainController.handleRequest(model);
        return "welcome";
    }

}
