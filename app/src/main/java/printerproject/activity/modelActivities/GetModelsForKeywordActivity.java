package printerproject.activity.modelActivities;

import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.GetModelsForKeywordRequest;
import printerproject.results.modelResults.GetModelsForKeywordResult;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetModelsForKeywordActivity for the Model endpoint.
 *
 * This API allows the customer to interact with Model objects in the database.
 */
public class GetModelsForKeywordActivity {
    private final ModelDao modelDao;

    /**
     * Instantiates a new GetModelsForKeywordActivity object.
     *
     * @param modelDao ModelDao to access the playlist table.
     */
    @Inject
    public GetModelsForKeywordActivity(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    /**
     * This method handles the incoming request by retrieving list of models belong to a
     * keyword from the database, if any exist.
     * It then returns the list.
     * If no models are found, the method will return an empty list.
     * @param getModelsForKeywordRequest request object containing the keyword
     * @return GetModelsForKeywordResult result object containing the API defined
     */

    public GetModelsForKeywordResult handleRequest(final GetModelsForKeywordRequest getModelsForKeywordRequest) {
        List<Model> modelList = new ArrayList<>();
        modelList = modelDao.loadModelsForKeyword(getModelsForKeywordRequest.getKeyword());
        return GetModelsForKeywordResult.builder()
                .withModelList(modelList)
                .build();
    }


}
