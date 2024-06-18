package printerproject.activity.filamentActivities;

import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.GetFilamentRequest;
import printerproject.results.filamentResults.GetFilamentResult;

import javax.inject.Inject;

/**
 * Implementation of the GetFilamentActivity for the Filament endpoint.
 *
 * This API allows the customer to interact with Filament objects in the database.
 */
public class GetFilamentActivity {
    private final FilamentDao filamentDao;

    /**
     * Instantiates a new GetFilamentActivity object.
     *
     * @param filamentDao FilamentDao to access the playlist table.
     */
    @Inject
    public GetFilamentActivity(FilamentDao filamentDao) {
        this.filamentDao = filamentDao;
    }

    /**
     * This method handles the incoming request by retrieving a filament from the database, if it exists.
     * <p>
     * It then returns the filament.
     * <p>
     * If the filament does not exist on the database, this method will propagate a FilamentNotFoundException.
     *
     * @param getFilamentRequest request object containing the orgId and filamentId
     * @return GetFilamentResult result object containing the API defined
     * {@link com.nashss.se.musicplaylistservice.dynamodb.models.Filament}
     */

    public GetFilamentResult handleRequest(final GetFilamentRequest getFilamentRequest) {
        Filament filament = filamentDao.loadSingleFilament(getFilamentRequest.getFilamentId());
        return GetFilamentResult.builder()
                .withFilament(filament)
                .build();
    }


}
