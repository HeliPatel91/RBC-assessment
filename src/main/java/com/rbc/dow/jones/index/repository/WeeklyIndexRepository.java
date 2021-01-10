package com.rbc.dow.jones.index.repository;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import com.rbc.dow.jones.index.entity.WeeklyIndexId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeeklyIndexRepository extends JpaRepository<WeeklyIndex, WeeklyIndexId> {

    List<WeeklyIndex> findByStock(String stock);

}
