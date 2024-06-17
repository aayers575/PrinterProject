package printerproject.activity.voteActivities;

import printerproject.dynamodb.VoteDao;
import printerproject.requests.voteRequests.GetVoteRequest;
import printerproject.results.voteResults.GetVoteResult;

import javax.inject.Inject;

/**
 * Implementation of GetVoteActivity for Project Binford's GetVote API
 *
 * This API allows a consumer to retrieve a single Vote object by voteId
 */
public class GetVoteActivity {
    private final VoteDao voteDao;

    /**
     * Instantiates a new GetVoteActivity object
     *
     * @param voteDao VoteDao to interact with the vote table
     */

    @Inject
    public GetVoteActivity(VoteDao voteDao) {
        this. voteDao = voteDao;
    }

    /**
     * This method handles the request by retrieving a single vote from the database
     * @param getVoteRequest request object containing an voteId
     * @return GetVoteResult object containing a single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Vote}
     */
    public GetVoteResult handleRequest(final GetVoteRequest getVoteRequest) {
        return GetVoteResult.builder()
                .withVote(voteDao.getSingleVote(getVoteRequest.getVoteId()))
                .build();
    }
}
