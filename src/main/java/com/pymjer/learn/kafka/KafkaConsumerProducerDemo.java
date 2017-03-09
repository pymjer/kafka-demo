package com.pymjer.learn.kafka;

import java.io.IOException;

/**
 * @author leicui bourne_cui@163.com
 */
public class KafkaConsumerProducerDemo
{
    public static void main(String[] args) throws IOException {
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
        producerThread.start();
        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        consumerThread.start();
        System.in.read();
    }
}