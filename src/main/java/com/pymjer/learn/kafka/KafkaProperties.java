package com.pymjer.learn.kafka;

/**
 * Created by FENG on 2016/3/15.
 */
public interface KafkaProperties
{
    final static String zkConnect = "114.215.182.124:2181";
    final static String groupId = "group1";
    final static String topic = "test";
    final static String kafkaServerURL = "192.168.184.134";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "SimpleConsumerDemoClient";
}