package InterviewByCompany.Walmart.OA;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

public class KafkaConsumerExample {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        List<String> topics = new ArrayList<>();
        topics.add("topic-test");

        try {
            kafkaConsumer.subscribe(topics);

            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(10);
                Iterator a = records.iterator();
                while (a.hasNext()) {
                    ConsumerRecord<String, String> b = (ConsumerRecord<String, String>) a.next();
                    System.out.println(String.format("Topic - %s, Value: %s", b.topic(), b.value()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.close();
        }
    }
}

