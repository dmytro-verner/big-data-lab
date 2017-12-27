package edu.university.bigdata;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import edu.university.bigdata.model.model.UserVisit;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ConsumerApp {
    public static void main(String[] args){
        Consumer<String, String> kafkaConsumer = initConsumer();
        Cluster cassandraCluster = initCluster();
        Session cassandraSession = initSession(cassandraCluster);

        Mapper<UserVisit> userVisitMapper = initMapper(cassandraSession);

        while(true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(500);

            stream(records)
                    .map(ConsumerRecord::value)
                    .map(UserVisit::toUserVisit)
                    .forEach(userVisitMapper::save);
        }
    }

    private static Consumer<String, String> initConsumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "first");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList("uservisits1"));
        return consumer;
    }

    private static Cluster initCluster(){
        return Cluster.builder().addContactPoint("127.0.0.1").build();
    }

    private static Session initSession(Cluster cluster){
        return cluster.connect();
    }

    private static Mapper<UserVisit> initMapper(Session session) {
        MappingManager manager = new MappingManager(session);
        return manager.mapper(UserVisit.class);
    }

    private static <T> Stream<T> stream(Iterable<T> iterable) {
        return (!(iterable instanceof Collection)) ? StreamSupport.stream(iterable.spliterator(), false)
                : ((Collection<T>) iterable).stream();
    }
}
