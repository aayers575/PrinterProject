package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import printerproject.dynamodb.ModelDao;
import printerproject.dynamodb.models.Model;
import printerproject.exceptions.ModelNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ModelDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedQueryList<Model> paginatedQueryList;
    private ModelDao modelDao;
    private Model testModel;


    @BeforeEach
    public void setup() {
        initMocks(this);
        modelDao = new ModelDao(mapper);
        testModel = new Model();
        testModel.setModelId("testModelId");
        testModel.setKeyword("test");
        testModel.setPreview("test");
        testModel.setIsActive("Yes");
        testModel.setMaterialUsed(10);
    }

    @Test
    public void getSingleModel_MatchingModel_returnsModel() {
        //GIVEN
        testModel.setModelId("testModelId1");
        testModel.setKeyword("test1");
        testModel.setPreview("test1");
        testModel.setIsActive("No");
        testModel.setMaterialUsed(101);
        doReturn(testModel).when(mapper).load(Model.class, testModel.getModelId());

        //WHEN
        Model result = modelDao.loadSingleModel(testModel.getModelId());

        //THEN
        assertEquals(testModel.getModelId(), result.getModelId(), "Expected method to pass back object with matching values");
        assertEquals(testModel.getKeyword(), result.getKeyword(), "Expected method to pass back object with matching values");
        assertEquals(testModel.getIsActive(), result.getIsActive(), "Expected method to pass back object with matching values");
        assertEquals(testModel.getMaterialUsed(), result.getMaterialUsed(), "Expected method to pass back object with matching values");
        assertEquals(testModel.getPreview(), result.getPreview(), "Expected method to pass back object with matching values");
    }

    @Test
    public void getSingleModel_noMatchingModel_ThrowsModelNotFoundException() {
        //GIVEN
        doReturn(null).when(mapper).load(Model.class, testModel.getModelId());

        //WHEN
        //THEN
        assertThrows(ModelNotFoundException.class, () -> modelDao.loadSingleModel(testModel.getModelId()), "Expected method to throw error");
    }

    @Test
    public void loadModelsForKeyword_anyModel_returnsList() {
        //GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<Model>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        doReturn(paginatedQueryList).when(mapper).query(eq(Model.class),any(DynamoDBQueryExpression.class));
        //WHEN
        modelDao.loadModelsForKeyword(testModel.getKeyword());
        verify(mapper).query(eq(Model.class),argumentCaptor.capture());

        //THEN
        assertEquals(new AttributeValue(testModel.getKeyword()), argumentCaptor.getValue().getExpressionAttributeValues().get(":keyword"), "Expected method to faithfully pass provided values to mapper");

    }

    @Test
    public void writeModel_anyModel_interactsWithSaveMethod() {
        //GIVEN
        //WHEN
        modelDao.writeModel(testModel);

        //THEN
        verify(mapper).save(testModel);
    }

    @Test
    public void deleteModel_anyModel_interactsWithDeleteMethod() {
        //GIVEN
        //WHEN
        modelDao.deleteModel(testModel);

        //THEN
        verify(mapper).delete(testModel);
    }
}