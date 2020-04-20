/*
 * @Author: wangran
 * @Date: 2020-04-15 11:03:50
 * @LastEditors: wangran
 * @LastEditTime: 2020-04-15 15:31:28
 */
package com.kafka.demo.kafka_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//定时任务
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
