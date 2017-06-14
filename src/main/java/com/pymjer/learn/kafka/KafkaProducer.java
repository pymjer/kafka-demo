package com.pymjer.learn.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
/**
 * @author leicui bourne_cui@163.com
 */
public class KafkaProducer extends Thread
{
    private final Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public KafkaProducer(String topic)
    {

        props.put("zk.connect", KafkaProperties.zkConnect);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "45.63.22.9:9092");//45.32.9.214:9092,
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true)
        {
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send:" + messageStr);
            producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
            messageNo++;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // TODO Auto-generated catch block
            }
        }
    }
}