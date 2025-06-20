package org.example;

import org.example.service.UsageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageMain {
    private static final Logger logger = LoggerFactory.getLogger(UsageMain.class);

    public static void main(String[] args) {
        try {
            UsageService service = new UsageService("energy_queue", "", "localhost");
            logger.info("UsageService (energy_queue) started");
            service.run();
        } catch (Exception e) {
            logger.error("Error in usage service", e);
        }
    }

}