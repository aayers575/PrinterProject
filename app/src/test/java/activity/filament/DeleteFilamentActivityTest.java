package activity.filament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import printerproject.activity.filamentActivities.DeleteFilamentActivity;
import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.DeleteFilamentRequest;
import printerproject.results.filamentResults.DeleteFilamentResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteFilamentActivityTest {
    @Mock
    private FilamentDao filamentDao;

    private DeleteFilamentActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new DeleteFilamentActivity(filamentDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoDeleteMethod() {
        // GIVEN
        String filamentId = "filamentId";
        Filament filament = new Filament();
        DeleteFilamentRequest request = DeleteFilamentRequest.builder()
                .withFilamentId(filamentId)
                .build();
        ArgumentCaptor<Filament> argumentCaptor = ArgumentCaptor.forClass(Filament.class);
        // WHEN
        DeleteFilamentResult result = activity.handleRequest(request);

        // THEN
        verify(filamentDao).deleteFilament(argumentCaptor.capture());
        assertEquals(filamentId, argumentCaptor.getValue().getFilamentId(), "Expected method to call Dao with values matching those provided in request");
        assertEquals(filamentId, result.getFilament().getFilamentId(), "Expected method to output result with values matching those provided in request");
    }
}
