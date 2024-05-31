package printerproject.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "ModelsTable")
public class Model {
    private String modelId;
    private String keyword;
    private byte[] preview;
    private String isActive;
    private Integer materialUsed;


    @DynamoDBHashKey(attributeName = "modelId")
    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "TasksSortByAssigneeIndex", attributeName = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @DynamoDBAttribute(attributeName = "preview")
    public byte[] getPreview() {
        return preview;
    }

    public void setPreview(byte[] preview) {
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

    public void setInventoryCount(Integer materialUsed) {
        this.materialUsed = materialUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(modelId, model.modelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId);
    }
}
