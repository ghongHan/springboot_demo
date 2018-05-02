package com.hskj.service;

import com.alibaba.fastjson.JSONObject;
import com.hskj.common.dto.Message;
import com.hskj.common.dto.MessageType;
import com.hskj.common.dto.TrainDto;
import com.hskj.common.util.PageUtil;
import com.hskj.domain.RailwayStation;
import com.hskj.domain.Train;
import com.hskj.repository.RailwayStationRepository;
import com.hskj.repository.TrainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

@Service
@Slf4j
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private RailwayStationRepository railwayStationRepository;

    @Autowired
    private RedisService redisService;

    /**
     * 通过createTime进行降序，并分页进行展示
     * @return
     */
    @Override
    public ResponseEntity<Message> getAllTrains() {
        Pageable pageable = PageUtil.buildPageRequest(1, 2, "DESC", new String[]{"createTime"});
        Page<Train> trains = trainRepository.findByRailwayStationId(Long.valueOf(1), pageable);
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, trains), HttpStatus.OK);
    }

    /**
     * 添加车辆
     * @param trainDto
     * @return
     */
    @Override
    public ResponseEntity<Message> addTrain(TrainDto trainDto){
        if(!StringUtils.isEmpty(trainDto)){
            if(null != trainDto.getStationId()){
                RailwayStation railwayStation = railwayStationRepository.findById(trainDto.getStationId());
                if(null != railwayStation){
                    Train train = Train.builder()
                            .name(trainDto.getName())
                            .speed(trainDto.getSpeed())
                            .diesel(trainDto.getDiesel())
                            .railwayStation(railwayStation)
                            .createTime(new Date())
                            .build();

                    trainRepository.save(train);
                    log.info("添加成功");
                    List<Train> trains = trainRepository.findByRailwayStationId(trainDto.getStationId());
                    String result = JSONObject.toJSONString(trains);
                    System.out.println(result);
                    //插入redis
                    redisService.removeZSet("get_trains");
                    redisService.addZSet("get_trains", result, 1);
                    return new ResponseEntity<Message>(new Message(MessageType.SUCCESS), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<Message>(new Message(MessageType.ERROR), HttpStatus.OK);
    }

    /**
     * 通过车站id查找车辆
     * @param railStationId 车站id
     * @return
     */
    @Override
    public ResponseEntity<Message> findTrainByRailStationId(Long railStationId) {
        Message message;
        if(null == railStationId){
            message = new Message(MessageType.SUCCESS, "error", "railStationId is null");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        List<Train> trains = trainRepository.findByRailwayStationId(railStationId);
        if(StringUtils.isEmpty(trains)){
            message = new Message(MessageType.SUCCESS, "error", "result not found");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, trains), HttpStatus.OK);
    }

    /**
     * 运用pg存储函数查找数据
     * @param stationId
     * @return
     */
    @Override
    public ResponseEntity<Message> getAllTrain(Long stationId) {
        if(StringUtils.isEmpty(stationId)){
            return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "param is null"), HttpStatus.OK);
        }
        String result = trainRepository.getAllTrain(stationId);
        List<Train> trains = JSONObject.parseObject(result, List.class);
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, trains), HttpStatus.OK);
    }
}
