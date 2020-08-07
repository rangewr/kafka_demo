/*
 * @Author: wangran
 * 
 * @Date: 2020-03-12 11:56:11
 * 
 * @LastEditors: wangran
 * 
 * @LastEditTime: 2020-07-17 10:03:38
 */
package com.kafka.demo.kafka_demo.producer;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 启动发送消息方法
    public void addMessage(Map<String, Object> map) {
        String jsonStrTraining = JSON.toJSONString(map);
        System.out.println(jsonStrTraining);
        kafkaTemplate.send("start_clear_data", jsonStrTraining);
    }

    public void customTopic(Map<String, Object> map) {
        String jsonStrTraining = JSON.toJSONString(map);
        System.out.println(jsonStrTraining);
        kafkaTemplate.send(map.get("topic").toString(), map.get("pwd").toString());
    }

}