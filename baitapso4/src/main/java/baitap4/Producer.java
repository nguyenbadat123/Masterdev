package baitap4;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;
import static org.apache.kafka.common.requests.DeleteAclsResponse.log;
public class Producer {
    private static final Path currentRelativePath = Paths.get("");
    private static final String PATH = currentRelativePath.toAbsolutePath().toString()+"/src/main/java/CSV/customer.csv";
    public static void main(String[] args) throws FileNotFoundException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "172.17.80.26:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");


        File file = new File(PATH);
        KafkaProducer producer = new KafkaProducer(properties);
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                log.info(line);
                producer.send(new ProducerRecord("customer999",null,line));
            }
            log.info("Finished Sending");
        }
        producer.close();
    }
}