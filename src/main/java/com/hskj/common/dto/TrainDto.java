package com.hskj.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by hongHan_gao
 * Date: 2017/12/19
 */

@Data
public class TrainDto {

    private Long id;

    private String name;

    private Integer speed;

    private Boolean diesel;

    private Long stationId;

    private Date createTime;

}
