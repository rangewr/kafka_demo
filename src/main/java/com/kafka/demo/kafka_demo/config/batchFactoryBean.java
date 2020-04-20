/*
 * @Author: wangran
 * 
 * @Date: 2020-03-23 10:34:45
 * 
 * @LastEditors: wangran
 * 
 * @LastEditTime: 2020-04-15 13:40:56
 */
package com.kafka.demo.kafka_demo.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class batchFactoryBean {
    /**
     * kafka监听工厂
     * @param configurer
     * @return
     */
    @Bean("batchFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        // 开启批量消费功能
        factory.setBatchListener(true);
        // 不自动启动
        factory.setAutoStartup(false);
        configurer.configure(factory, consumerFactory);
        return factory;
    }
}