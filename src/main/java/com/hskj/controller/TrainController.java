package com.hskj.controller;

import com.hskj.common.dto.Message;
import com.hskj.common.dto.TrainDto;
import com.hskj.service.TrainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/18.
 */
@Api(value = "/train", tags = "车辆模块")
@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @ApiOperation(value="获取全部车辆信息")
    @RequestMapping(value = "/allTrains", method = RequestMethod.GET)
    public ResponseEntity<Message> getAllTrains(){
        return trainService.getAllTrains();
    }

    @ApiOperation(value = "新增车辆")
    @ApiImplicitParam(name = "trainDto", value = "TrainDto", required = true, dataType = "TrainDto")
    @PostMapping("/addTrain")
    public ResponseEntity<Message> addTrain(@RequestBody TrainDto trainDto){
        return trainService.addTrain(trainDto);
    }

    @ApiOperation(value="通过车站id查询车辆—直接查询数据库")
    @RequestMapping(value = "/getTrainByStationId", method = RequestMethod.GET)
    public ResponseEntity<Message> getTrainByStationId(@RequestParam("stationId") Long stationId){
        return trainService.findTrainByRailStationId(stationId);
    }

    @ApiOperation(value="通过车站id查询车辆—查询redis")
    @RequestMapping(value = "/getAllTrain" , method = RequestMethod.GET)
    public ResponseEntity<Message> getAllTrains(@RequestParam("stationId") Long stationId){
        return trainService.getAllTrain(stationId);
    }

}
