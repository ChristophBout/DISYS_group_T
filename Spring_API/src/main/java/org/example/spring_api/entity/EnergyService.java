package org.example.spring_api.entity;

import org.example.spring_api.repository.EnergyRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class EnergyService {

    private final EnergyRepository repository;

    public EnergyService(EnergyRepository repository) {
        this.repository = repository;
    }

    public EnergyCurrent getCurrent() throws SQLException {
        return repository.getCurrentEnergyPercentage();
    }

    public EnergyHistorical getHistorical(LocalDateTime start, LocalDateTime end) throws SQLException {
        return repository.getHistoricalEnergyUsage(start, end);
    }
}