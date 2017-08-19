//package com.pymjer.learn.kafka.consumer;
//
//import com.pymjer.learn.kafka.KafkaProperties;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
//import java.util.Arrays;
//import java.util.Properties;
//
///**
// * @author leicui bourne_cui@163.com
// */
//public class KafkaNewConsumer extends Thread {
//
//    private final KafkaConsumer<String, String>  consumer;
//    private final String topic;
//
//    public KafkaNewConsumer(String topic) {
//        consumer = new KafkaConsumer(createConsumerConfig(topic));
//        this.topic = topic;
//    }
//
//    private static Properties createConsumerConfig(String topic) {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", KafkaProperties.brokerList);
//        props.put("group.id", "consumer-tutorial");
//        props.put("key.deserializer", StringDeserializer.class.getName());
//        props.put("value.deserializer", StringDeserializer.class.getName());
//        return props;
//    }
//
//    @Override
//    public void run() {
//        consumer.subscribe(Arrays.asList("production_process_flow","order_interception"));
//        try {
//            boolean running = true;
//            while (running) {
//                ConsumerRecords<String, String> records = consumer.poll(1000);
//                for (ConsumerRecord<String, String> record : records)
//                    System.out.println(record.offset() + ": " + record.value());
//            }
//        } finally {
//            consumer.close();
//        }
//    }
//}