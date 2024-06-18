package activity.filament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import printerproject.activity.filamentActivities.GetFilamentActivity;
import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.GetFilamentRequest;
import printerproject.results.filamentResults.GetFilamentResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetFilamentActivityTest {
    @Mock
    private FilamentDao filamentDao;

    private GetFilamentActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetFilamentActivity(filamentDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String filamentId = "filamentId";
        Filament filament = new Filament();
        filament.setFilamentId(filamentId);
        GetFilamentRequest request = GetFilamentRequest.builder()
                .withFilamentId(filamentId)
                .build();

        doReturn(filament).when(filamentDao).loadSingleFilament(filamentId);

        // WHEN
        GetFilamentResult result = activity.handleRequest(request);

        // THEN
        verify(filamentDao).loadSingleFilament(filamentId);
        assertEquals(filamentId, result.getFilament().getFilamentId(), "Expected method to output result with values matching those provided in request");
    }
}