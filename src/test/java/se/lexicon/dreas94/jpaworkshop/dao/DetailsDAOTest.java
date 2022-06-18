package se.lexicon.dreas94.jpaworkshop.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.dreas94.jpaworkshop.entity.Details;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // spring boot test is used to test the unit test in jpa and entity manager
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DetailsDAOTest
{
    @Autowired
    private DetailsDAO testObject;

    private Details testDetails;

    @BeforeAll
    public void setUp()
    {
        testDetails = testObject.create(new Details("tras94@gmail.com", "Andreas", "1994-03-14"));

        testObject.create(new Details("glitter89@gmail.com", "Mehrdad", "1989-02-27"));
    }

    @Test
    @Order(1)
    @Transactional
    void create()
    {
        Details actualData = testObject.create(new Details("asdasd4@gmail.com", "Test", "1990-11-22"));
        Details expectedData = testObject.findById(3).orElse(null);
        assertEquals(expectedData, actualData);
    }

    @Test
    @Order(2)
    @DisplayName("test find id 1 with the result and testDetails being equal")
    @Transactional
    public void findId1()
    {
        try
        {
            Details expectedData = testDetails;
            Details actualData = testObject.findById(1).orElseThrow(() -> new DataNotFoundException("Not Found", " Details"));
            assertEquals(expectedData, actualData);
        }
        catch (DataNotFoundException e)
        {
            System.out.println(e.getObjectName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    @DisplayName("test get all method from DetailsDAO with result 2")
    public void findAll()
    {
        int actualSize = testObject.findAll().size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(4)
    @DisplayName("test get delete method from AppUser, then get all method from DetailsDAO with result 1")
    public void delete()
    {
        try
        {
            testObject.delete(testDetails);
        }
        catch (DataNotFoundException e)
        {
            e.printStackTrace();
        }

        int actualSize = testObject.findAll().size();
        int expectedSize = 1;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(5)
    void find_gives_OptionalEmpty()
    {
        assertEquals(Optional.empty(), testObject.findById(10));
    }

    @Test
    @Order(6)
    void delete_throws_DataNotFoundException()
    {
        assertThrows(DataNotFoundException.class, () -> testObject.delete(new Details("asdasd4@gmail.com", "Test", "1990-11-22")));
    }
}
