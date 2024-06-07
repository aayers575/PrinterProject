package printerproject.activity.filamentActivities;

import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.exceptions.ModelNotFoundException;
import printerproject.requests.modelRequests.CreateModelRequest;
import printerproject.results.modelResults.CreateModelResult;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Implementation of the CreateModelActivity for the Model endpoint.
 *
 * This API allows the customer to interact with Model objects in the database.
 */
public class CreateModelActivity {
    private final ModelDao modelDao;

    /**
     * Instantiates a new GetModelActivity object.
     *
     * @param modelDao ModelDao to access the playlist table.
     */
    @Inject
    public CreateModelActivity(ModelDao modelDao) { this.modelDao = modelDao; }

    /**
     * This method handles the incoming request by checking to see if an existing model exists, then replacing it with the provided new content.
     * <p>
     * It then returns the new model.
     * <p>
     * If the model does not exist on the database, this method will propagate a ModelNotFoundException.
     * If either orgId or modelId is null, this method will throw an invalid attribute exception.
     *
     * @param createModelRequest request object containing the orgId and modelId
     * @return CreateModelResult result object containing the API defined {@link Model}
     */

    public CreateModelResult handleRequest(final CreateModelRequest createModelRequest) {
        Model newModel = new Model();
        newModel.setModelId(UUID.randomUUID().toString());
        while (modelIdExists(newModel)) {
            newModel.setModelId(UUID.randomUUID().toString());
        }
        newModel.setKeyword(createModelRequest.getKeyword());
        newModel.setIsActive(createModelRequest.getIsActive());
        newModel.setPreview(createModelRequest.getPreview());
        newModel.setMaterialUsed(createModelRequest.getMaterialUsed());

        modelDao.writeModel(newModel);
        return CreateModelResult.builder()
                .withModel(newModel)
                .build();
    }

    private boolean modelIdExists(Model model) {
        try {
            modelDao.loadSingleModel(model.getModelId());
            return true;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }


}