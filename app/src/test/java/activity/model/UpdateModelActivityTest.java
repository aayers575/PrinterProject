package activity.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import printerproject.activity.modelActivities.UpdateModelActivity;
import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.requests.modelRequests.UpdateModelRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateModelActivityTest {
    @Mock
    private ModelDao modelDao;

    private UpdateModelActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new UpdateModelActivity(modelDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesModelKeyword() {
        // GIVEN
        String modelId = "modelId";
        String oldKeyword = "oldKeyword";
        String newKeyword = "newKeyword";

        UpdateModelRequest request = UpdateModelRequest.builder()
                .withModelId(modelId)
                .withKeyword(newKeyword)
                .build();

        Model start = new Model();
        start.setModelId(modelId);
        start.setKeyword(oldKeyword);

        ArgumentCaptor<Model> argumentCaptor = ArgumentCaptor.forClass(Model.class);

        doReturn(start).when(modelDao).loadSingleModel(modelId);

        // WHEN
        activity.handleRequest(request);
        verify(modelDao).writeModel(argumentCaptor.capture());

        // THEN
        verify(modelDao).loadSingleModel(modelId);
        assertEquals(newKeyword, argumentCaptor.getValue().getKeyword(), "Expected class to pass updated keyword to DAO for write");
    }
}