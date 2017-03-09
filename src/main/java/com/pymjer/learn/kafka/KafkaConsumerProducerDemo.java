package com.pymjer.learn.kafka;

import kafka.producer.KeyedMessage;

import java.io.IOException;

/**
 * @author leicui bourne_cui@163.com
 */
public class KafkaConsumerProducerDemo
{
    public static void main(String[] args) throws IOException {
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic) {
            public KeyedMessage<Object, String> getMessage(String topic, int messageNo) throws InterruptedException {
                String messageStr = new String("Message_" + messageNo);
                System.out.println("Send:" + messageStr);
                return new KeyedMessage<Object, String>(topic, messageStr);
            }
        };
        producerThread.start();
        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        consumerThread.start();
        System.in.read();
    }
}