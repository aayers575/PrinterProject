package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
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
    }

    @Test
    public void getSingleModel_MatchingModel_returnsModel() {
        //GIVEN
        doReturn(testModel).when(mapper).load(Model.class, testModel.getModelId());

        //WHEN
        Model result = modelDao.loadSingleModel(testModel.getModelId());

        //THEN
        assertEquals(testModel.getModelId(), result.getModelId(), "Expected method to pass back object with matching values");
    }

    @Test
    public void getSingleModel_noMatchingModel_ThrowsModelNotFoundException() {
        //GIVEN
        doReturn(null).when(mapper).load(Model.class, testModel.getModelId());

        //WHEN
        //THEN
        assertThrows(ModelNotFoundException.class, () -> modelDao.loadSingleModel(testModel.getModelId()), "Expected method to throw error");
    }

//    @Test
//    public void loadModelsForKeyword_anyModel_returnsList() {
//        //GIVEN
//        ArgumentCaptor<DynamoDBQueryExpression<Model>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
//        doReturn(paginatedQueryList).when(mapper).query(eq(Model.class),any(DynamoDBQueryExpression.class));
//        //WHEN
//        modelDao.loadModelsForKeyword(testModel.getKeyword());
//        verify(mapper).query(eq(Model.class),argumentCaptor.capture());
//
//        //THEN
//        assertEquals(testModel.getKeyword(), argumentCaptor.getValue().getHashKeyValues().getKeyword(), "Expected method to faithfully pass provided values to mapper");
//
//    }

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