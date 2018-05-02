package com.hskj.repository;

import com.hskj.domain.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findByRailwayStationId(Long stationId);

    Page<Train> findByRailwayStationId(Long stationId, Pageable pageable);

    @Query(nativeQuery = true, value="select get_trains(?1)")
    String getAllTrain(Long stationId);

}
