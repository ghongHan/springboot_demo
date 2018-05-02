package com.hskj.service;

import com.alibaba.fastjson.JSONObject;
import com.hskj.domain.Train;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by hongHan_gao
 * Date: 2018/4/25
 * redis服务
 */

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    //插入单个String数据到redis
    public void addString(String key, String value){
        this.addObject(key,value);
    }

    //插入list型String数据到redis
    public void addList(String key, List<Train> value){
        this.addObject(key,value);
    }

    //插入String数据到redis
    public void addObject(String key, Object value){
        String[] param = {key, String.valueOf(value)};
        if(StringUtils.isAnyEmpty(param)){
            return;
        }
        //判断value类型
        if(value instanceof String){
            String strValue = String.valueOf(value);
             stringRedisTemplate.opsForValue().set(key, strValue);
             return;
        }else if(value instanceof List){
            List<Train> listValue = (List<Train>) value;
            Train[] trains = listValue.toArray(new Train[0]);
            redisTemplate.opsForList().leftPushAll(key,trains);
            return;
        }
    }

    //从redis获取String数据
    public String getKey(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 往redis插入zset数据
     * @param key
     * @param value
     * @param score
     */
    public void addZSet(String key, String value, double score){
        String[] param = {key, value, String.valueOf(score)};
        if(StringUtils.isAnyEmpty(param)){
            return;
        }
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 删除ZSet数据
     * @param key
     */
    public void removeZSet(String key){
        if(StringUtils.isEmpty(key)){
            return;
        }
        stringRedisTemplate.opsForZSet().removeRangeByScore(key,0,-1);
    }

    /**
     * 获取ZSet数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> getZSet(String key, double start, double end){
        String[] param = {key, String.valueOf(start), String.valueOf(end)};
        if(StringUtils.isAnyEmpty(param)){
            return null;
        }
        return stringRedisTemplate.opsForZSet().rangeByScore(key, start, end);
    }

}
