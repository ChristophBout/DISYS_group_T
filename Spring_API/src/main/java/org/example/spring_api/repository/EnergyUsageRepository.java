package org.example.spring_api.repository;

import org.example.spring_api.entity.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, Timestamp> {
    List<EnergyUsage> findByHourBetween(Timestamp start, Timestamp end);
}