package com.hskj.repository;

import com.hskj.domain.RailwayStation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hongHan_gao
 * Date: 2017/12/19
 */

public interface RailwayStationRepository extends JpaRepository<RailwayStation, Long> {

    RailwayStation findById(Long id);

}
