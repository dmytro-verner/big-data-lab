import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.Properties;

public class ConsumerApp {
    public static void main(String[] args) {

        Consumer kafkaConsumer = initConsumer();

    }

    private static Consumer<String, String> initConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers",
                "localhost:9092");
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Collections.singletonList("uservisits"));
        return consumer;
    }
}
