package printerproject.activity.modelActivities;

import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.GetModelRequest;
import printerproject.results.modelResults.GetModelResult;

import javax.inject.Inject;

/**
 * Implementation of the GetModelActivity for the Model endpoint.
 *
 * This API allows the customer to interact with Model objects in the database.
 */
public class GetModelActivity {
    private final ModelDao modelDao;

    /**
     * Instantiates a new GetModelActivity object.
     *
     * @param modelDao ModelDao to access the playlist table.
     */
    @Inject
    public GetModelActivity(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    /**
     * This method handles the incoming request by retrieving a model from the database, if it exists.
     * <p>
     * It then returns the model.
     * <p>
     * If the model does not exist on the database, this method will propagate a ModelNotFoundException.
     *
     * @param getModelRequest request object containing the orgId and modelId
     * @return GetModelResult result object containing the API defined
     * {@link com.nashss.se.musicplaylistservice.dynamodb.models.Model}
     */

    public GetModelResult handleRequest(final GetModelRequest getModelRequest) {
        Model model = modelDao.loadSingleModel(getModelRequest.getModelId());
        return GetModelResult.builder()
                .withModel(model)
                .build();
    }


}
