package printerproject.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Map;
import java.util.Objects;

@DynamoDBTable(tableName =  "VotesTable")
public class Vote {
    private String voteId;
    private String isActive;
    private Map<String, String> votesByKeyword;

    @DynamoDBHashKey(attributeName = "voteId")
    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    @DynamoDBAttribute(attributeName = "votesByKeyword")

    public Map<String, String> getVotesByKeyword() {
        return votesByKeyword;
    }

    public void setVotesByKeyword(Map<String, String> votesByKeyword) {
        this.votesByKeyword = votesByKeyword;
    }

    @DynamoDBAttribute(attributeName = "isActive")

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Vote other = (Vote) o;
        return this.voteId.equals(other.getVoteId());
    }
}