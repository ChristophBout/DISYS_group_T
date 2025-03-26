package org.example.spring_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class energyController {
    @RestController
    @RequestMapping("/energy")
    public class EnergyController {

        @GetMapping("/current")
        public ResponseEntity<EnergyCurrent> getCurrent() {
            return ResponseEntity.ok(new EnergyCurrent());
        }

        @GetMapping("/historical")
        public ResponseEntity<EnergyHistorical> getHistorical(
                @RequestParam String start,
                @RequestParam String end
        ) {
            return ResponseEntity.ok(new EnergyHistorical());
        }
    }
}
