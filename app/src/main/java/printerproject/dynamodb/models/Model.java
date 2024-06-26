package printerproject.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "ModelsTable")
public class Model {
    private String modelId;
    private String keyword;
    private String preview;
    private String isActive;
    private Integer materialUsed;


    @DynamoDBHashKey(attributeName = "modelId")
    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ModelsSortByKeywordIndex", attributeName = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @DynamoDBAttribute(attributeName = "preview")
    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "ModelsSortByKeywordIndex", attributeName = "isActive")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String active) {
        isActive = active;
    }

    @DynamoDBAttribute(attributeName = "materialUsed")
    public Integer getMaterialUsed() {
        return materialUsed;
    }

    public void setMaterialUsed(Integer materialUsed) {
        this.materialUsed = materialUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }
        Model model = (Model) o;
        return Objects.equals(modelId, model.modelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId);
    }
}
