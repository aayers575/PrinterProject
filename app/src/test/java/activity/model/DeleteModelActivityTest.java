package activity.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import printerproject.activity.modelActivities.DeleteModelActivity;
import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.DeleteModelRequest;
import printerproject.results.modelResults.DeleteModelResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteModelActivityTest {
    @Mock
    private ModelDao modelDao;

    private DeleteModelActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new DeleteModelActivity(modelDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoDeleteMethod() {
        // GIVEN
        String modelId = "modelId";
        Model model = new Model();
        DeleteModelRequest request = DeleteModelRequest.builder()
                .withModelId(modelId)
                .build();
        ArgumentCaptor<Model> argumentCaptor = ArgumentCaptor.forClass(Model.class);
        // WHEN
        DeleteModelResult result = activity.handleRequest(request);

        // THEN
        verify(modelDao).deleteModel(argumentCaptor.capture());
        assertEquals(modelId, argumentCaptor.getValue().getModelId(), "Expected method to call Dao with values matching those provided in request");
        assertEquals(modelId, result.getModel().getModelId(), "Expected method to output result with values matching those provided in request");
    }
}
