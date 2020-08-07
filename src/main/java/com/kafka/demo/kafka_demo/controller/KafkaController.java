/*
 * @Author: wangran
 * @Date: 2020-04-15 14:53:10
 * @LastEditors: wangran
 * @LastEditTime: 2020-07-17 10:03:47
 */
package com.kafka.demo.kafka_demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.kafka.demo.kafka_demo.producer.KafkaProducer;
import com.kafka.demo.kafka_demo.utils.ResultJSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultJSON addMessage(Integer id, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("type", type);
        // 任务存入到kafka消息队列中
        kafkaProducer.addMessage(map);
        return ResultJSON.success();
    }

    @RequestMapping("/customTopic")
    public ResultJSON customTopic(String topic, String pwd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("topic", topic);
        map.put("pwd", pwd);
        // 任务存入到kafka消息队列中
        kafkaProducer.customTopic(map);
        return ResultJSON.success();
    }

    @PostMapping("testPost")
    public ResultJSON testPost(MultipartFile file, String srcLanguage, String tgtLanguage) {

        System.out.println(srcLanguage);
        return ResultJSON.success();

        // Map<String, String> map = new HashMap<String, String>();
        // map.put("name", Pls1InfArry.get(i).split(":")[0]);
        // map.put("value", Pls1InfArry.get(i).split(":")[1]);
        // maps.put("key"+i,map);

    }

}