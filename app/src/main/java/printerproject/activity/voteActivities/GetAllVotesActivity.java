package printerproject.activity.voteActivities;

import printerproject.dynamodb.VoteDao;
import printerproject.requests.voteRequests.GetAllVotesRequest;
import printerproject.results.voteResults.GetAllVotesResult;

import javax.inject.Inject;

/**
 * Implementation of GetAllVotesActivity for Project Binford's GetAllVotes API
 *
 * This API allows a consumer to retrieve a list of Vote objects by voteId
 */
public class GetAllVotesActivity {
    private final VoteDao voteDao;

    /**
     * Instantiates a new GetAllVotesActivity object
     *
     * @param voteDao VoteDao to interact with the vote table
     */

    @Inject
    public GetAllVotesActivity(VoteDao voteDao) {
        this. voteDao = voteDao;
    }

    /**
     * This method handles the request by retrieving all available votes from the database
     * @return GetAllVoteResults object containing a list of {@link com.nashss.se.musicplaylistservice.dynamodb.models.Vote}
     */
    public GetAllVotesResult handleRequest(final GetAllVotesRequest getAllVotesRequest) {
        return GetAllVotesResult.builder()
                .withVoteList(voteDao.getAllVotes())
                .build();
    }
}
