package se.lexicon.dreas94.jpaworkshop.dao;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.dreas94.jpaworkshop.dao.AppUserDAO;
import se.lexicon.dreas94.jpaworkshop.dao.DetailsDAO;
import se.lexicon.dreas94.jpaworkshop.entity.AppUser;
import se.lexicon.dreas94.jpaworkshop.entity.Details;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // spring boot test is used to test the unit test in jpa and entity manager
public class AppUserDAOTest
{
    @Autowired
    private AppUserDAO testObject;

    private AppUser testAppUser;

    @BeforeEach
    public void setUp()
    {
        testAppUser = testObject.create(new AppUser("dreas94", "ewfw4et2r4",
                new Details("tras94@gmail.com", "Andreas", "1994-03-14")));

        testObject.create(new AppUser("mer89", "wef2342trg23",
                new Details("glitter89@gmail.com", "Mehrdad", "1989-02-27")));
    }

    @Test
    void create()
    {
        AppUser actualData = testObject.create(new AppUser("sdfasd4", "dsasdadfwt23rv",
                new Details("asdasd4@gmail.com", "Test", "1990-11-22")));
        AppUser expectedData = testObject.findById(3).orElse(null);
        assertEquals(expectedData, actualData);
    }

    @Test
    @DisplayName("test find id 1 with the result and testAppUser being equal")
    public void findId1()
    {
        try
        {
            AppUser expectedData = testAppUser;
            AppUser actualData = testObject.findById(1).orElseThrow(() -> new DataNotFoundException("Not Found", " AppUser"));
            assertEquals(expectedData, actualData);
        }
        catch (DataNotFoundException e)
        {
            System.out.println(e.getObjectName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("test get all method from AppUserDAO with result 2")
    public void findAll()
    {
        int actualSize = testObject.findAll().size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("test get delete method from AppUser, then get all method from AppUserDAO with result 1")
    public void delete()
    {
        try
        {
            testObject.delete(testAppUser);
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
        assertThrows(DataNotFoundException.class, () -> testObject.delete(new AppUser("sdfasd4", "dsasdadfwt23rv",
                new Details("asdasd4@gmail.com", "Test", "1990-11-22"))));
    }
}
