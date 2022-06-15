package se.lexicon.dreas94.jpaworkshop.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.dreas94.jpaworkshop.entity.Book;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // spring boot test is used to test the unit test in jpa and entity manager
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookDAOTest
{
    @Autowired
    private BookDAO testObject;

    private Book testBook;

    @BeforeAll
    public void setUp()
    {
        testBook = testObject.create(new Book("123454536", "Tester", 8));

        testObject.create(new Book("345194856", "Gralemald", 20));
    }

    @Test
    @Order(1)
    void create()
    {
        Book actualData = testObject.create(new Book("123425624", "Tester123", 45));
        Book expectedData = null;
        try
        {
            expectedData = testObject.findById(3);
        }
        catch (DataNotFoundException e)
        {
            System.out.println(e.getObjectName());
            System.out.println(e.getMessage());
        }
        assertEquals(expectedData, actualData);
    }

    @Test
    @Order(2)
    @DisplayName("test find id 1 with the result and testAppUser being equal")
    public void findId1()
    {
        try
        {
            Book expectedData = testBook;
            Book actualData = testObject.findById(1);
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
    @DisplayName("test get all method from AppUserDAO with result 3")
    public void findAll()
    {
        int actualSize = testObject.findAll().size();
        int expectedSize = 3;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(4)
    @DisplayName("test get delete method from AppUser, then get all method from AppUserDAO with result 2")
    public void delete()
    {
        try
        {
            testObject.delete(testBook.getId());
        }
        catch (DataNotFoundException e)
        {
            e.printStackTrace();
        }

        int actualSize = testObject.findAll().size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(5)
    void find_throws_DataNotFoundException()
    {
        assertThrows(DataNotFoundException.class, () -> testObject.findById(10));
    }

    @Test
    @Order(6)
    void delete_throws_DataNotFoundException()
    {
        assertThrows(DataNotFoundException.class, () -> testObject.delete(10));
    }
}
