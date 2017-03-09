package com.pymjer.learn.kafka.partition;

import kafka.producer.Partitioner;

/**
 * @author: jiang.huang
 * @create Date: 2017/3/9 Time: 14:30
 */
public class OrderPartitioner implements Partitioner {

    public int partition(Object key, int numPartitions) {
        try {
            int orderId = Integer.parseInt(key.toString());
            return orderId % numPartitions;
        } catch (Exception e) {
            e.printStackTrace();
            return Math.abs(key.hashCode() % numPartitions);
        }
    }

}
