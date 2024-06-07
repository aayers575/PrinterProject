package printerproject.activity.modelActivities;

import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.DeleteModelRequest;
import printerproject.results.modelResults.DeleteModelResult;

import javax.inject.Inject;

public class DeleteModelActivity {
    private final ModelDao modelDao;

    /**
     * Instantiates a new DeleteModelActivity object.
     *
     * @param modelDao ModelDao to access the playlist table.
     */
    @Inject
    public DeleteModelActivity(ModelDao modelDao) { this.modelDao = modelDao; }

    /**
     * This method handles the incoming request by deleting a model from the database, if it exists.
     * <p>
     * It then returns the deleted model.
     *
     * @param deleteModelRequest request object containing the orgId and modelId
     * @return GetModelResult result object containing the API defined {@link Model}
     */

    public DeleteModelResult handleRequest(final DeleteModelRequest deleteModelRequest) {

        Model model = new Model();
        model.setModelId(deleteModelRequest.getModelId());
        modelDao.deleteModel(model);
        return DeleteModelResult.builder()
                .withModel(model)
                .build();
    }


}