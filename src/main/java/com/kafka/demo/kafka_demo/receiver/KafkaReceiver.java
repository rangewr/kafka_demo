/*
 * @Author: wangran
 * 
 * @Date: 2020-03-24 11:45:36
 * 
 * @LastEditors: wangran
 * 
 * @LastEditTime: 2020-04-22 10:50:14
 */
package com.kafka.demo.kafka_demo.receiver;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    private static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    /**
     * 定时执行<任务启动>监听
     *
     * @param recordList
     * @param acknowledgment
     */
    @KafkaListener(id = "postoffice_topic_1", topics = {
            "postoffice_topic_1" }, groupId = "kafka-group", containerFactory = "batchFactory")
    public void listenTaskStart(List<ConsumerRecord> recordList, Acknowledgment acknowledgment) {
        for (ConsumerRecord record : recordList) {
            JSONObject jsonObject = JSON.parseObject(record.value().toString());
            int id = jsonObject.getInteger("id");
            logger.info("定时查询成功");
            logger.info("查询结果为id ==========>" + id);
        }
        acknowledgment.acknowledge();
    }

    // 任务启动 每隔15分钟获取一次
    @Scheduled(cron = "30 * * * * ?")
    public void taskStartListener() {
        logger.info("开启<任务启动>监听");
        MessageListenerContainer containerStart = registry.getListenerContainer("postoffice_topic_1");
        if (!containerStart.isRunning()) {
            containerStart.start();
        }
        // 恢复监听
        containerStart.resume();
        try {
            Thread.sleep(10 * 1000);// 暂停10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("关闭<任务启动>监听");
        // 暂停监听
        MessageListenerContainer containerClose = registry.getListenerContainer("postoffice_topic_1");
        containerClose.pause();
    }

}