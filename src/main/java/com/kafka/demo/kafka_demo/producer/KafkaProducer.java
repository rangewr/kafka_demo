/*
 * @Author: wangran
 * 
 * @Date: 2020-03-12 11:56:11
 * 
 * @LastEditors: wangran
 * 
 * @LastEditTime: 2020-04-15 15:21:57
 */
package com.kafka.demo.kafka_demo.producer;

import com.alibaba.fastjson.JSON;
import com.kafka.demo.kafka_demo.entity.TrainingInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 启动发送消息方法
    public void addMessage(TrainingInfo trainingInfo) {
        String jsonStrTraining = JSON.toJSONString(trainingInfo);
        System.out.println(jsonStrTraining);
        kafkaTemplate.send("start_kafka", jsonStrTraining);
    }

}