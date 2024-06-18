package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.exceptions.FilamentNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class FilamentDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedQueryList<Filament> paginatedQueryList;
    private FilamentDao filamentDao;
    private Filament testFilament;


    @BeforeEach
    public void setup() {
        initMocks(this);
        filamentDao = new FilamentDao(mapper);
        testFilament = new Filament();
        testFilament.setFilamentId("testFilamentId");
        testFilament.setColor("test");
        testFilament.setMaterial("testMaterial");
        testFilament.setMaterialRemaining(10);
        testFilament.setIsActive("Yes");
    }

    @Test
    public void getSingleFilament_MatchingFilament_returnsFilament() {
        //GIVEN
        doReturn(testFilament).when(mapper).load(Filament.class, testFilament.getFilamentId());

        //WHEN
        Filament result = filamentDao.loadSingleFilament(testFilament.getFilamentId());

        //THEN
        assertEquals(testFilament.getFilamentId(), result.getFilamentId(), "Expected method to pass back object with matching values");
        assertEquals(testFilament.getColor(), result.getColor(), "Expected method to pass back object with matching values");
        assertEquals(testFilament.getIsActive(), result.getIsActive(), "Expected method to pass back object with matching values");
        assertEquals(testFilament.getMaterial(), result.getMaterial(), "Expected method to pass back object with matching values");
        assertEquals(testFilament.getMaterialRemaining(), result.getMaterialRemaining(), "Expected method to pass back object with matching values");
    }

    @Test
    public void getSingleFilament_noMatchingFilament_ThrowsFilamentNotFoundException() {
        //GIVEN
        doReturn(null).when(mapper).load(Filament.class, testFilament.getFilamentId());

        //WHEN
        //THEN
        assertThrows(FilamentNotFoundException.class, () -> filamentDao.loadSingleFilament(testFilament.getFilamentId()), "Expected method to throw error");
    }

    @Test
    public void loadFilamentsForColor_anyFilament_returnsList() {
        //GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<Filament>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        doReturn(paginatedQueryList).when(mapper).query(eq(Filament.class),any(DynamoDBQueryExpression.class));
        //WHEN
        filamentDao.loadFilamentsForColor(testFilament.getColor(), testFilament.getIsActive());
        verify(mapper).query(eq(Filament.class),argumentCaptor.capture());

        //THEN
        assertEquals(new AttributeValue(testFilament.getColor()), argumentCaptor.getValue().getExpressionAttributeValues().get(":color"), "Expected method to faithfully pass provided values to mapper");

    }

    @Test
    public void writeFilament_anyFilament_interactsWithSaveMethod() {
        //GIVEN
        //WHEN
        filamentDao.writeFilament(testFilament);

        //THEN
        verify(mapper).save(testFilament);
    }

    @Test
    public void deleteFilament_anyFilament_interactsWithDeleteMethod() {
        //GIVEN
        //WHEN
        filamentDao.deleteFilament(testFilament);

        //THEN
        verify(mapper).delete(testFilament);
    }
}