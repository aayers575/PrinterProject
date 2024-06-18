package printerproject.activity.filamentActivities;

import printerproject.dynamodb.FilamentDao;
import printerproject.requests.filamentRequests.GetAllFilamentsRequest;
import printerproject.results.filamentResults.GetAllFilamentsResult;

import javax.inject.Inject;

/**
 * Implementation of GetAllFilamentsActivity for Project Binford's GetAllFilaments API.
 *
 * This API allows a consumer to retrieve a list of Filament objects by filamentId.
 */
public class GetAllFilamentsActivity {
    private final FilamentDao filamentDao;

    /**
     * Instantiates a new GetAllFilamentsActivity object .
     *
     * @param filamentDao FilamentDao to interact with the filament table.
     */

    @Inject
    public GetAllFilamentsActivity(FilamentDao filamentDao) {
        this. filamentDao = filamentDao;
    }

    /**
     * This method handles the request by retrieving all available filaments from the database.
     * @param getAllFilamentsRequest gets all filaments
     * @return GetAllFilamentResults object containing a list of
     * {@link com.nashss.se.musicplaylistservice.dynamodb.models.Filament}
     */
    public GetAllFilamentsResult handleRequest(final GetAllFilamentsRequest getAllFilamentsRequest) {
        return GetAllFilamentsResult.builder()
                .withFilamentList(filamentDao.getAllFilaments())
                .build();
    }
}
