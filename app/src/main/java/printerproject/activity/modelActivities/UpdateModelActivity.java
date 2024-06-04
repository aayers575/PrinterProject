package printerproject.activity.modelActivities;

import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.UpdateModelRequest;
import printerproject.results.modelResults.UpdateModelResult;

import javax.inject.Inject;

/**
 * Implementation of the UpdateModelActivity for the Model endpoint.
 *
 * This API allows the customer to interact with Model objects in the database.
 */
public class UpdateModelActivity {
    private final ModelDao modelDao;

    /**
     * Instantiates a new GetModelActivity object.
     *
     * @param modelDao ModelDao to access the playlist table.
     */
    @Inject
    public UpdateModelActivity(ModelDao modelDao) { this.modelDao = modelDao; }

    /**
     * This method handles the incoming request by checking to see if an existing model exists, then replacing it with the provided new content.
     * <p>
     * It then returns the new model.
     * <p>
     * If the model does not exist on the database, this method will propagate a ModelNotFoundException.
     * If either orgId or modelId is null, this method will throw an invalid attribute exception.
     *
     * @param updateModelRequest request object containing the orgId and modelId
     * @return UpdateModelResult result object containing the API defined {@link Model}
     */

    public UpdateModelResult handleRequest(final UpdateModelRequest updateModelRequest) {
        Model updatedmodel = modelDao.loadSingleModel(updateModelRequest.getModelId());
        updatedmodel.setModelId(updateModelRequest.getModelId());
        updatedmodel.setKeyword(updateModelRequest.getKeyword());
        updatedmodel.setIsActive(updateModelRequest.getIsActive());
        updatedmodel.setPreview(updateModelRequest.getPreview());
        updatedmodel.setMaterialUsed(updateModelRequest.getMaterialUsed());

        modelDao.writeModel(updatedmodel);
        return UpdateModelResult.builder()
                .withModel(updatedmodel)
                .build();
    }


}