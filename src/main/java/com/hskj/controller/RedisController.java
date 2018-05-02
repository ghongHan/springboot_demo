package com.hskj.controller;

import com.hskj.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Set;

/**
 * Created by hongHan_gao
 * Date: 2018/4/25
 * 只是测试一下与redis的连通，返回格式尚未整理成项目的固定出参方式
 */

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiIgnore
    @RequestMapping("/addStrValue")
    public String setString(String key, String value){
        redisService.addString(key, value);
        return "success";
    }

    @ApiIgnore
    @RequestMapping("/getValue")
    public String getKey(String key){
        return redisService.getKey(key);
    }

    /**
     * 从redis查询车辆的相关信息
     * @param key
     * @param start
     * @param end
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/getZSet")
    public String getZSet(String key, double start, double end){
        Set<String> result = redisService.getZSet(key, start, end);
        if(result.size() == 0){
            log.info("未查询到结果");
            return null;
        }
        String trains = null;
        for(String value : result){
            trains =  value;
        }
        return trains;
    }

}
