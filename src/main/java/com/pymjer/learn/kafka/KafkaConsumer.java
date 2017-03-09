package com.pymjer.learn.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author leicui bourne_cui@163.com
 */
public class KafkaConsumer extends Thread {
    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer(String topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(topic));
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig(String topic) {
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.zkConnect);
        props.put("group.id", KafkaProperties.groupId);
        props.put("topic.name", topic);
        props.put("rebalance.backoff.ms", "10000");
        props.put("rebalance.max.retries", "10");
        props.put("auto.offset.reset", "smallest");
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.connection.timeout.ms", "1000000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.commit.enable", "true");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> next = it.next();
            System.out.println(String.format("Partition:%s, receive message：%s",next.partition(),new String(next.message())));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}