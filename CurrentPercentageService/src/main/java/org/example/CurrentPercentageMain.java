package org.example;

import org.example.service.CurrentPercentageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentPercentageMain {
    private static final Logger logger = LoggerFactory.getLogger(CurrentPercentageMain.class);

    public static void main(String[] args) {
        try {
            CurrentPercentageService service = new CurrentPercentageService("update message", "", "localhost");
            logger.info("CurrentPercentageService (update message) started");
            service.run();
        } catch (Exception e) {
            logger.error("Error in CurrentPercentageService", e);
        }
    }
}