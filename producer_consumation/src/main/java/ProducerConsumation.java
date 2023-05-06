import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.opencsv.CSVReader;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.*;


public class ProducerConsumation {

    private final static String TOPIC_NAME = "energy_consumation";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    // dataset path, csv file has to be in resources directory
    private final static String CSV_DATASET = "loadprofiles/2022_Consumation.csv";

    private final static String delimiter = ";";

    public static void main(String[] args) throws Exception {

        // read csv dataset to arraylist
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(Resources.getResource(CSV_DATASET).getPath()))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }

        // Read Kafka properties file and create Kafka producer with the given properties
        KafkaProducer<byte[], String> producer;
        try (InputStream props = Resources.getResource("producer.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            // Create Kafka producer
            producer = new KafkaProducer<byte[], String>(properties);
        }

        // first line in csv are headers, save to array and drop line
        String[] headers = records.get(0).get(0).split((delimiter));
        records.remove(0);

        byte[] key = null;
        // generate production data, based on csv dataset
        records.forEach((r) -> {
            String[] values = r.get(0).split(delimiter);
            JSONObject jsonObj = new JSONObject();
            for (int i = 0; i < values.length; i++) {
                jsonObj.put(headers[i], values[i]);
            }

            String message = jsonObj.toJSONString(); // gaze.toJSONString();
            System.out.println("Producing - " + message);
            ProducerRecord<byte[], String> record = new ProducerRecord<>(TOPIC_NAME, key, message);
            producer.send(record);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}