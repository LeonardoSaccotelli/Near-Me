package setup;

import model.CoordinateGPS;
import model.GeolocationData;
import model.Rating;
import model.Restaurant;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {

    protected static Map<String,Restaurant> readCsvFile() throws IOException {
        String resourcesPathString = System.getProperty("user.dir") + "/src/main/resources";
        String csvFilename = "restaurant_dataset.csv";

        Iterable<CSVRecord> parser = CSVFormat.RFC4180
                .withHeader(HeaderCsvFile.class).withFirstRecordAsHeader()
                .parse(new FileReader(resourcesPathString + "\\" + csvFilename));

        Map<String,Restaurant> listOfRestaurant = new HashMap<>();

        for (CSVRecord record : parser) {
            listOfRestaurant.put(createNewKey(record), createNewRestaurant(record));
        }

        return listOfRestaurant;
    }

    private static Restaurant createNewRestaurant(CSVRecord record) {
        return new Restaurant(
                record.get(HeaderCsvFile.Restaurant_ID),
                record.get(HeaderCsvFile.Restaurant_Name),
                new GeolocationData(new CoordinateGPS(record.get(HeaderCsvFile.Longitude),
                        record.get(HeaderCsvFile.Latitude)),
                        record.get(HeaderCsvFile.City),
                        record.get(HeaderCsvFile.Address),
                        record.get(HeaderCsvFile.Locality),
                        record.get(HeaderCsvFile.Locality_Verbose)
                ),
                (record.get(HeaderCsvFile.Cuisines)).split("-"),
                Float.parseFloat(record.get(HeaderCsvFile.Average_Cost_for_two)),
                record.get(HeaderCsvFile.Has_Table_booking),
                record.get(HeaderCsvFile.Has_Online_delivery),
                record.get(HeaderCsvFile.Is_delivering_now),
                Integer.parseInt(record.get(HeaderCsvFile.Price_range)),
                new Rating(Float.parseFloat(record.get(HeaderCsvFile.Aggregate_rating)),
                record.get(HeaderCsvFile.Rating_text),
                Integer.parseInt(record.get(HeaderCsvFile.Votes)))
        );
    }

    private static String createNewKey(CSVRecord record){
        return record.get(HeaderCsvFile.Restaurant_ID);
    }
}
