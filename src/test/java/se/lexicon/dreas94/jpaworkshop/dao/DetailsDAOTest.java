package se.lexicon.dreas94.jpaworkshop.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.dreas94.jpaworkshop.entity.Details;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // spring boot test is used to test the unit test in jpa and entity manager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // it configures the test database
@DirtiesContext // this tells the Spring to clear the application context after the test has sun
public class DetailsDAOTest
{
    @Autowired
    private DetailsDAO testObject;

    private Details testDetails;

    @BeforeEach
    public void setUp()
    {
        testDetails = testObject.create(new Details("tras94@gmail.com", "Andreas", "1994-03-14"));

        testObject.create(new Details("glitter89@gmail.com", "Mehrdad", "1989-02-27"));
    }

    @Test
    void create()
    {
        Details actualData = testObject.create(new Details("asdasd4@gmail.com", "Test", "1990-11-22"));
        Details expectedData = testObject.findById(3).orElse(null);
        assertEquals(expectedData, actualData);
    }

    @Test
    @DisplayName("test find id 1 with the result and testDetails being equal")
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
    @DisplayName("test get all method from DetailsDAO with result 2")
    public void findAll()
    {
        int actualSize = testObject.findAll().size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
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
    void find_gives_OptionalEmpty()
    {
        assertEquals(Optional.empty(), testObject.findById(10));
    }

    @Test
    void delete_throws_DataNotFoundException()
    {
        assertThrows(DataNotFoundException.class, () -> testObject.delete(new Details("asdasd4@gmail.com", "Test", "1990-11-22")));
    }
}
