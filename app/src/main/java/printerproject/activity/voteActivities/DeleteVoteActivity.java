package printerproject.activity.voteActivities;

import printerproject.dynamodb.VoteDao;
import printerproject.dynamodb.models.Vote;
import printerproject.requests.voteRequests.DeleteVoteRequest;
import printerproject.results.voteResults.DeleteVoteResult;

import javax.inject.Inject;

/**
 * Implementation of DeleteVoteActivity for Project Binford's GetVote API
 *
 * This API allows a consumer to retrieve a single Vote object by voteId
 */
public class DeleteVoteActivity {
    private final VoteDao voteDao;

    /**
     * Instantiates a new DeleteVoteActivity object
     *
     * @param voteDao VoteDao to interact with the vote table
     */

    @Inject
    public DeleteVoteActivity(VoteDao voteDao) {
        this. voteDao = voteDao;
    }

    /**
     * This method handles the request by deleting a single vote from the database, if present
     * @param deleteVoteRequest request object containing an voteId
     * @return DeleteVoteResult, and empty object
     */
    public DeleteVoteResult handleRequest(final DeleteVoteRequest deleteVoteRequest) {
        Vote vote = new Vote();
        vote.setVoteId(deleteVoteRequest.getVoteId());
        voteDao.deleteVote(vote);
        return DeleteVoteResult.builder()
                .withVote(vote)
                .build();
    }
}
