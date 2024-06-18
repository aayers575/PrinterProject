package printerproject.activity.filamentActivities;

import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.UpdateFilamentRequest;
import printerproject.results.filamentResults.UpdateFilamentResult;

import javax.inject.Inject;

/**
 * Implementation of the UpdateFilamentActivity for the Filament endpoint.
 *
 * This API allows the customer to interact with Filament objects in the database.
 */
public class UpdateFilamentActivity {
    private final FilamentDao filamentDao;

    /**
     * Instantiates a new GetFilamentActivity object.
     *
     * @param filamentDao FilamentDao to access the playlist table.
     */
    @Inject
    public UpdateFilamentActivity(FilamentDao filamentDao) {
        this.filamentDao = filamentDao;
    }

    /**
     * This method handles the incoming request by checking to see if an existing filament exists,
     * then replacing it with the provided new content.
     * <p>
     * It then returns the new filament.
     * <p>
     * If the filament does not exist on the database, this method will propagate a FilamentNotFoundException.
     * If either orgId or filamentId is null, this method will throw an invalid attribute exception.
     *
     * @param updateFilamentRequest request object containing the orgId and filamentId
     * @return UpdateFilamentResult result object containing the API defined {@link Filament}
     */

    public UpdateFilamentResult handleRequest(final UpdateFilamentRequest updateFilamentRequest) {
        Filament updatedfilament = filamentDao.loadSingleFilament(updateFilamentRequest.getFilamentId());
        updatedfilament.setFilamentId(updateFilamentRequest.getFilamentId());
        updatedfilament.setColor(updateFilamentRequest.getColor());
        updatedfilament.setIsActive(updateFilamentRequest.getIsActive());
        updatedfilament.setMaterial(updateFilamentRequest.getMaterial());
        updatedfilament.setMaterialRemaining(updateFilamentRequest.getMaterialRemaining());

        filamentDao.writeFilament(updatedfilament);
        return UpdateFilamentResult.builder()
                .withFilament(updatedfilament)
                .build();
    }


}
