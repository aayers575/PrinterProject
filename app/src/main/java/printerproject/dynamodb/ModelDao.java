package printerproject.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import printerproject.dynamodb.models.Model;
import printerproject.exceptions.ModelNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for a Model using {@link Model} to interact with the model in DynamoDB.
 */

@Singleton
public class ModelDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates a ModelDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the album_track table
     */

    @Inject
    public ModelDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a model by modelId.
     *
     * If not found, throws ModelNotFoundException.
     *
     * @param modelId The modelId to look up
     * @return The corresponding Model if found
     */
    public Model loadSingleModel(String modelId) {
        Model model = mapper.load(Model.class, modelId);
        if (model == null) {
            throw new ModelNotFoundException(String.format("Could not find model with modelId %s", modelId));
        }
        return model;
    }

    /**
     * Retrieves all Models matching provided keyword and mode.
     *
     * If none found, returns an empty list.
     *
     * @param keyword The keyword to look up
     * @return A list of Models found, if any
     */
    public List<Model> loadModelsForKeyword(String keyword) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":keyword", new AttributeValue(keyword));
        DynamoDBQueryExpression<Model> queryExpression = new DynamoDBQueryExpression<Model>()
                .withIndexName("ModelsSortByKeywordIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("keyword = :keyword")
                .withExpressionAttributeValues(valueMap);
        return mapper.query(Model.class, queryExpression);
    }


    /**
     * Saves provided Model to DynamoDB to create or update DynamoDB record.
     *
     * @param model The Model to be saved
     */
    public void writeModel(Model model) {
        mapper.save(model);
    }

    public boolean checkIfExist(Model model) {
        Model loaded = mapper.load(Model.class, model.getModelId());
        return loaded != null;
    }

    /**
     * Removes the provided Model from DynamoDB, if present.
     *
     * @param model The Model to be deleted
     */
    public void deleteModel(Model model) {
        mapper.delete(model);
    }

}