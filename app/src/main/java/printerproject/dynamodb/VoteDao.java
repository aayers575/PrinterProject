package printerproject.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import printerproject.dynamodb.models.Vote;
import printerproject.exceptions.VoteNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class VoteDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates an VoteDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the Votes table
     */

    @Inject
    public VoteDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a vote by voteId.
     *
     * If not found, throws VoteNotFoundException.
     *
     * @param voteId The voteId to look up
     * @return The corresponding Vote if found
     */
    public Vote getSingleVote(String voteId) {
        Vote vote = mapper.load(Vote.class, voteId);
        if (null == vote) {
            throw new VoteNotFoundException(String.format("Could not find vote with voteId %s", voteId));
        }
        return vote;
    }

    /**
     * Retrieves all votes in database.
     *
     * If none found, returns an empty list.
     *
     * @return A list of Votes found, if any
     */
    public List<Vote> getAllVotes() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Vote> result = mapper.scan(Vote.class, scanExpression);
        return result;
    }

    /**
     * Saves provided Vote to DynamoDB to create or update DynamoDB record.
     *
     * @param vote The Vote to be written
     */
    public Vote writeVote(Vote vote) {
        mapper.save(vote);
        return vote;
    }

    /**
     * Removes the provided Vote from DynamoDB, if present.
     *
     * @param vote The Vote to be deleted
     */
    public void deleteVote(Vote vote) {
        mapper.delete(vote);
    }

}
