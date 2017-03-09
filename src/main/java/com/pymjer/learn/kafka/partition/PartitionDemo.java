package com.pymjer.learn.kafka.partition;

import com.pymjer.learn.kafka.KafkaConsumer;
import com.pymjer.learn.kafka.KafkaProducer;
import com.pymjer.learn.kafka.KafkaProperties;
import kafka.producer.KeyedMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leicui bourne_cui@163.com
 */
public class PartitionDemo
{
    public static void main(String[] args) throws IOException {
        Map<String,String> params = new HashMap<String, String>();
        params.put("partition.class","com.pymjer.learn.kafka.partition.OrderPartitioner");

        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.partition_test_topic, params) {
            public KeyedMessage<Object, String> getMessage(String topic, int messageNo) throws InterruptedException {
                return new KeyedMessage<Object, String>(topic, String.valueOf(messageNo), String.format("The message for key %d", messageNo));
            }
        };
        producerThread.start();

        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.partition_test_topic);
        consumerThread.start();
        System.in.read();
    }
}