package baitap4;

import com.opencsv.CSVWriter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import static org.apache.kafka.common.requests.DeleteAclsResponse.log;
public class Consumer {
    private static Path currentRelativePath = Paths.get("");
    private static String PATH = currentRelativePath.toAbsolutePath().toString()+"/src/main/java/CSV/outcsv.csv";
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.26:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "cusssss");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, Integer.MAX_VALUE);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, Integer.MAX_VALUE);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("customer999"));
        File file = new File(PATH);
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        String [] header = {"customerId","num_order","age","phoneNumber"};
        writer.writeNext(header);
        writer.flush();
        ArrayList<Integer> arr = new ArrayList<>();
        while (true){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records){
                String [] x = record.value().split(",");
                try {
                    int order_id = Integer.parseInt(x[1]);
                    int age = Integer.parseInt(x[2]);
                    int id = Integer.parseInt(x[0]);
                    if ( order_id > 100 && age < 20 && !arr.contains(id) ){
                        arr.add(id);
                        log.info("Key: " + record.key() + ", Value: " + record.value());
                        writer.writeNext(x);
                        writer.flush();
                    }
                }
                catch (Exception e){
                    writer.flush();
                }
            }
        }
    }
}
