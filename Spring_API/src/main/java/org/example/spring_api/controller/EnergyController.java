package org.example.spring_api.controller;

import org.example.spring_api.entity.EnergyCurrent;
import org.example.spring_api.entity.EnergyHistorical;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/energy")
public class EnergyController {

    @GetMapping("/current")
    public ResponseEntity<EnergyCurrent> getCurrent() {
        return ResponseEntity.ok(new EnergyCurrent(78.54, 7.23));
    }

    @GetMapping("/historical")
    public ResponseEntity<EnergyHistorical> getHistorical(
            @RequestParam String start,
            @RequestParam String end
    ) {
        return ResponseEntity.ok(new EnergyHistorical(143.024, 130.101, 4.75));
    }
}