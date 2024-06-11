package activity.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import printerproject.activity.modelActivities.GetModelActivity;
import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.GetModelRequest;
import printerproject.results.modelResults.GetModelResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetModelActivityTest {
    @Mock
    private ModelDao modelDao;

    private GetModelActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetModelActivity(modelDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String modelId = "modelId";
        Model model = new Model();
        model.setModelId(modelId);
        GetModelRequest request = GetModelRequest.builder()
                .withModelId(modelId)
                .build();

        doReturn(model).when(modelDao).loadSingleModel(modelId);

        // WHEN
        GetModelResult result = activity.handleRequest(request);

        // THEN
        verify(modelDao).loadSingleModel(modelId);
        assertEquals(modelId, result.getModel().getModelId(), "Expected method to output result with values matching those provided in request");
    }
}