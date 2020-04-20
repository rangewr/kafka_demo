/*
 * @Author: wangran
 * @Date: 2020-04-15 14:53:10
 * @LastEditors: wangran
 * @LastEditTime: 2020-04-15 15:03:18
 */
package com.kafka.demo.kafka_demo.controller;

import com.kafka.demo.kafka_demo.entity.TrainingInfo;
import com.kafka.demo.kafka_demo.producer.KafkaProducer;
import com.kafka.demo.kafka_demo.utils.ResultJSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    KafkaProducer kafkaProducer;

    /**
     * @Author: wangran
     * @Date: 2020-03-09 13:20:22
     * @msg: 新增算法训练任务
     * @param {type}
     * @return:
     */
    @RequestMapping("/addMessage")
    public ResultJSON addMessage(TrainingInfo trainingInfo) {
        // 任务存入到kafka消息队列中
        kafkaProducer.addMessage(trainingInfo);
        return ResultJSON.success();
    }
}