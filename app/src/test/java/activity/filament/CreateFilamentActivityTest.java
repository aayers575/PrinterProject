package activity.filament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import printerproject.activity.filamentActivities.CreateFilamentActivity;
import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.exceptions.FilamentNotFoundException;
import printerproject.requests.filamentRequests.CreateFilamentRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateFilamentActivityTest {
    @Mock
    private FilamentDao filamentDao;

    private CreateFilamentActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new CreateFilamentActivity(filamentDao);
    }

    @Test
    public void handleRequest_goodRequest_returnsNewOrganization() {
        // GIVEN
        String color = "test";


        CreateFilamentRequest request = CreateFilamentRequest.builder()
                .withColor(color)
                .build();

        Filament filament = new Filament();
        filament.setColor(color);

        ArgumentCaptor<Filament> argumentCaptor = ArgumentCaptor.forClass(Filament.class);

        doThrow(new FilamentNotFoundException()).when(filamentDao).loadSingleFilament(any(String.class));

        // WHEN
        activity.handleRequest(request);
        verify(filamentDao).writeFilament(argumentCaptor.capture());

        // THEN
        assertEquals(color, argumentCaptor.getValue().getColor(), "Expected class to pass provided color to DAO for write");
    }


}