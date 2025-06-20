package org.example.spring_api.repository;

import org.example.spring_api.entity.CurrentPercentage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentage, java.sql.Timestamp> {
}