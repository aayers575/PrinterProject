package activity.filament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import printerproject.activity.filamentActivities.GetAllFilamentsActivity;
import printerproject.dynamodb.FilamentDao;
import printerproject.requests.filamentRequests.GetAllFilamentsRequest;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllFilamentsActivityTest {
    @Mock
    private FilamentDao filamentDao;

    private GetAllFilamentsActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetAllFilamentsActivity(filamentDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN

        GetAllFilamentsRequest request = GetAllFilamentsRequest.builder().build();

        doReturn(new ArrayList<>()).when(filamentDao).getAllFilaments();

        // WHEN
        activity.handleRequest(request);

        // THEN
        verify(filamentDao).getAllFilaments();
    }
}