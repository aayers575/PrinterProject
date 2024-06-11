package printerproject.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import printerproject.dynamodb.models.Filament;
import printerproject.exceptions.FilamentNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for a Filament using {@link Filament} to interact with the filament in DynamoDB.
 */

@Singleton
public class FilamentDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates a FilamentDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the album_track table
     */

    @Inject
    public FilamentDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a filament by filamentId.
     *
     * If not found, throws FilamentNotFoundException.
     *
     * @param filamentId The filamentId to look up
     * @return The corresponding Filament if found
     */
    public Filament loadSingleFilament(String filamentId) {
        Filament filament = mapper.load(Filament.class, filamentId);
        if (filament == null) {
            throw new FilamentNotFoundException(String.format("Could not find filament with filamentId %s", filamentId));
        }
        return filament;
    }


    /**
     * Retrieves all Filaments matching provided color and mode.
     *
     * If none found, returns an empty list.
     *
     * @param color The color to look up
     * @return A list of Filaments found, if any
     */
    public List<Filament> loadFilamentsForColor(String color) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":color", new AttributeValue(color));
        DynamoDBQueryExpression<Filament> queryExpression = new DynamoDBQueryExpression<Filament>()
                .withIndexName("FilamentsSortByColorIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("color = :color")
                .withExpressionAttributeValues(valueMap);
        return mapper.query(Filament.class, queryExpression);
    }


    /**
     * Saves provided Filament to DynamoDB to create or update DynamoDB record.
     *
     * @param filament The Filament to be saved
     */
    public void writeFilament(Filament filament) {
        mapper.save(filament);
    }

    public boolean checkIfExist(Filament filament) {
        Filament loaded = mapper.load(Filament.class, filament.getFilamentId());
        return loaded != null;
    }

    /**
     * Removes the provided Filament from DynamoDB, if present.
     *
     * @param filament The Filament to be deleted
     */
    public void deleteFilament(Filament filament) {
        mapper.delete(filament);
    }

}
