package activity.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import printerproject.activity.modelActivities.CreateModelActivity;
import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.exceptions.ModelNotFoundException;
import printerproject.requests.modelRequests.CreateModelRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateModelActivityTest {
    @Mock
    private ModelDao modelDao;

    private CreateModelActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new CreateModelActivity(modelDao);
    }

    @Test
    public void handleRequest_goodRequest_returnsNewOrganization() {
        // GIVEN
        String keyword = "test";


        CreateModelRequest request = CreateModelRequest.builder()
                .withKeyword(keyword)
                .build();

        Model model = new Model();
        model.setKeyword(keyword);

        ArgumentCaptor<Model> argumentCaptor = ArgumentCaptor.forClass(Model.class);

        doThrow(new ModelNotFoundException()).when(modelDao).loadSingleModel(any(String.class));

        // WHEN
        activity.handleRequest(request);
        verify(modelDao).writeModel(argumentCaptor.capture());

        // THEN
        assertEquals(keyword, argumentCaptor.getValue().getKeyword(), "Expected class to pass provided keyword to DAO for write");
    }


}
