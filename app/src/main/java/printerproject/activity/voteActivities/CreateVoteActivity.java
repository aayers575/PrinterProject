package printerproject.activity.voteActivities;

import printerproject.dynamodb.VoteDao;
import printerproject.dynamodb.models.Vote;
import printerproject.requests.voteRequests.CreateVoteRequest;
import printerproject.results.voteResults.CreateVoteResult;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

/**
 * Implementation of CreateVoteActivity for Project Printer's Votes API
 *
 * This API allows a consumer to write a Vote to the database
 */
public class CreateVoteActivity {
    private final VoteDao voteDao;

    /**
     * Instantiates a new CreateVoteActivity object
     *
     * @param voteDao VoteDao to interact with the vote table
     */

    @Inject
    public CreateVoteActivity(VoteDao voteDao) {
        this. voteDao = voteDao;
    }

    /**
     * This method handles the request by accepting a CreateVoteActivity and creating a new Vote object to write to the DB
     * @return single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Vote} that was just written
     */
    public CreateVoteResult handleRequest(final CreateVoteRequest createVoteRequest) {
        Vote vote = new Vote();
        vote.setVoteId(createVoteRequest.getVoteId());
        vote.setIsActive(createVoteRequest.getIsActive());
        vote.setVotesByKeyword(createVoteRequest.getVotesByKeyword());

        return CreateVoteResult.builder()
                .withVote(voteDao.writeVote(vote))
                .build();
    }
}
