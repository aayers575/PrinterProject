package printerproject.activity.filamentActivities;

import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.exceptions.FilamentNotFoundException;
import printerproject.requests.filamentRequests.CreateFilamentRequest;
import printerproject.results.filamentResults.CreateFilamentResult;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Implementation of the CreateFilamentActivity for the Filament endpoint.
 *
 * This API allows the customer to interact with Filament objects in the database.
 */
public class CreateFilamentActivity {
    private final FilamentDao filamentDao;

    /**
     * Instantiates a new GetFilamentActivity object.
     *
     * @param filamentDao FilamentDao to access the playlist table.
     */
    @Inject
    public CreateFilamentActivity(FilamentDao filamentDao) { this.filamentDao = filamentDao; }

    /**
     * This method handles the incoming request by checking to see if an existing filament exists, then replacing it with the provided new content.
     * <p>
     * It then returns the new filament.
     * <p>
     * If the filament does not exist on the database, this method will propagate a FilamentNotFoundException.
     * If either orgId or filamentId is null, this method will throw an invalid attribute exception.
     *
     * @param createFilamentRequest request object containing the orgId and filamentId
     * @return CreateFilamentResult result object containing the API defined {@link Filament}
     */

    public CreateFilamentResult handleRequest(final CreateFilamentRequest createFilamentRequest) {
        Filament newFilament = new Filament();
        newFilament.setFilamentId(UUID.randomUUID().toString());
        while (filamentIdExists(newFilament)) {
            newFilament.setFilamentId(UUID.randomUUID().toString());
        }
        newFilament.setColor(createFilamentRequest.getColor());
        newFilament.setIsActive(createFilamentRequest.getIsActive());
        newFilament.setMaterial(createFilamentRequest.getMaterial());
        newFilament.setMaterialRemaining(createFilamentRequest.getMaterialRemaining());

        filamentDao.writeFilament(newFilament);
        return CreateFilamentResult.builder()
                .withFilament(newFilament)
                .build();
    }

    private boolean filamentIdExists(Filament filament) {
        try {
            filamentDao.loadSingleFilament(filament.getFilamentId());
            return true;
        } catch (FilamentNotFoundException e) {
            return false;
        }
    }


}