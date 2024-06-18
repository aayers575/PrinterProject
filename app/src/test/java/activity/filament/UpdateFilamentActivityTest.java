package activity.filament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import printerproject.activity.filamentActivities.UpdateFilamentActivity;
import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.UpdateFilamentRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateFilamentActivityTest {
    @Mock
    private FilamentDao filamentDao;

    private UpdateFilamentActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new UpdateFilamentActivity(filamentDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesFilamentColor() {
        // GIVEN
        String filamentId = "filamentId";
        String oldColor = "oldColor";
        String newColor = "newColor";

        UpdateFilamentRequest request = UpdateFilamentRequest.builder()
                .withFilamentId(filamentId)
                .withColor(newColor)
                .build();

        Filament start = new Filament();
        start.setFilamentId(filamentId);
        start.setColor(oldColor);

        ArgumentCaptor<Filament> argumentCaptor = ArgumentCaptor.forClass(Filament.class);

        doReturn(start).when(filamentDao).loadSingleFilament(filamentId);

        // WHEN
        activity.handleRequest(request);
        verify(filamentDao).writeFilament(argumentCaptor.capture());

        // THEN
        verify(filamentDao).loadSingleFilament(filamentId);
        assertEquals(newColor, argumentCaptor.getValue().getColor(), "Expected class to pass updated color to DAO for write");
    }
}
