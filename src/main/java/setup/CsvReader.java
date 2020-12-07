package setup;

import model.Restaurant;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import schema.CsvSchema;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {

    protected static Map<String,Restaurant> readCsvFile() throws IOException {
        String resourcesPathString = System.getProperty("user.dir") + "/src/main/resources";
        String csvFilename = "restaurant_dataset.csv";
        String file = resourcesPathString + "/" + csvFilename;

        Iterable<CSVRecord> parser = CSVFormat.RFC4180
                .withHeader(CsvSchema.class).withFirstRecordAsHeader()
                .parse(new FileReader(file));

        Map<String,Restaurant> listOfRestaurant = new HashMap<>();

        for (CSVRecord record : parser) {
            listOfRestaurant.put(createNewKey(record), createNewRestaurant(record));
        }

        return listOfRestaurant;
    }

    private static Restaurant createNewRestaurant(CSVRecord record) {
        return new Restaurant(
                record.get(CsvSchema.Restaurant_ID),
                record.get(CsvSchema.Restaurant_Name),
                Float.parseFloat(record.get(CsvSchema.Latitude)),
                Float.parseFloat(record.get(CsvSchema.Longitude)),
                record.get(CsvSchema.City),
                record.get(CsvSchema.Address),
                record.get(CsvSchema.Locality),
                (record.get(CsvSchema.Cuisines)).split("-"),
                Double.parseDouble(record.get(CsvSchema.Average_Cost_for_two)),
                record.get(CsvSchema.Has_Table_booking),
                record.get(CsvSchema.Has_Online_delivery),
                record.get(CsvSchema.Is_delivering_now),
                Integer.parseInt(record.get(CsvSchema.Price_range)),
                Double.parseDouble(record.get(CsvSchema.Aggregate_rating)),
                record.get(CsvSchema.Rating_text),
                Integer.parseInt(record.get(CsvSchema.Votes))
        );
    }

    private static String createNewKey(CSVRecord record){
        return record.get(CsvSchema.Restaurant_ID);
    }
}
