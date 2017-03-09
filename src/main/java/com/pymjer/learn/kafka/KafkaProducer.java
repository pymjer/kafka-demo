package com.pymjer.learn.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Map;
import java.util.Properties;
/**
 * @author jiang.huang
 */
public abstract class KafkaProducer extends Thread
{
    private final Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public KafkaProducer(String topic)
    {
        this(topic,null);
    }

    public KafkaProducer(String topic, Map<String,String> parms)
    {
        if (parms != null && parms.size() > 0 ) {
            props.putAll(parms);
        }
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", KafkaProperties.brokerList);//45.32.9.214:9092,
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }


    @Override
    public void run() {
        int messageNo = 1;
        while (true)
        {
            try {
                KeyedMessage message = getMessage(topic, messageNo);
                producer.send(message);
                messageNo++;
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract KeyedMessage<Object, String> getMessage(String topic, int messageNo) throws InterruptedException;

}