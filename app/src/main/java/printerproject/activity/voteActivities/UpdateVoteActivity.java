package printerproject.activity.voteActivities;

import printerproject.dynamodb.VoteDao;
import printerproject.requests.voteRequests.UpdateVoteRequest;
import printerproject.results.voteResults.UpdateVoteResult;
import printerproject.dynamodb.models.Vote;

import javax.inject.Inject;

/**
 * Implementation of UpdateVoteActivity for Project Binford's Votes API
 *
 * This API allows a consumer to update an existing Vote in the database
 */
public class UpdateVoteActivity {
    private final VoteDao voteDao;

    /**
     * Instantiates a new UpdateVoteActivity object
     *
     * @param voteDao VoteDao to interact with the vote table
     */

    @Inject
    public UpdateVoteActivity(VoteDao voteDao) {
        this. voteDao = voteDao;
    }

    /**
     * This method handles the request by accepting a UpdateVoteActivity and creating a new Vote object to write to the DB
     * @return single {@link Vote} that was just written
     */
    public UpdateVoteResult handleRequest(final UpdateVoteRequest updateVoteRequest) {

        Vote vote = voteDao.getSingleVote(updateVoteRequest.getVoteId());
        vote.setIsActive(updateVoteRequest.getIsActive());
        vote.setVotesByKeyword(updateVoteRequest.getVotesByKeyword());

        return UpdateVoteResult.builder()
                .withVote(voteDao.writeVote(vote))
                .build();
    }
}