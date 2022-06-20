package se.lexicon.dreas94.jpaworkshop.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.dreas94.jpaworkshop.entity.AppUser;
import se.lexicon.dreas94.jpaworkshop.entity.Book;
import se.lexicon.dreas94.jpaworkshop.entity.BookLoan;
import se.lexicon.dreas94.jpaworkshop.entity.Details;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // spring boot test is used to test the unit test in jpa and entity manager
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookLoanDAOTest
{
    private final BookLoanDAO testObject;

    private final AppUserDAO appUserDAOObject;

    private final BookDAO bookDAOObject;

    private BookLoan testBookLoan;

    @Autowired
    public BookLoanDAOTest(AppUserDAO appUserDAOObject, BookDAO bookDAOObject, BookLoanDAO testObject)
    {
        this.appUserDAOObject = appUserDAOObject;
        this.bookDAOObject = bookDAOObject;
        this.testObject = testObject;
    }

    @BeforeAll
    public void setUp()
    {
        AppUser appUserObject1 = new AppUser("dreas94", "ewfw4et2r4",
                new Details("tras94@gmail.com", "Andreas", "1994-03-14"));

        AppUser appUserObject2 = new AppUser("mer89", "wef2342trg23",
                new Details("glitter89@gmail.com", "Mehrdad", "1989-02-27"));

        appUserDAOObject.create(appUserObject1);
        appUserDAOObject.create(appUserObject2);

        Book bookObject1 = bookDAOObject.create(new Book("123454536", "Tester", 8));

        Book bookObject2 = bookDAOObject.create(new Book("345194856", "Gralemald", 20));

        testBookLoan = new BookLoan(false, bookObject1);
        BookLoan bookLoan2 = new BookLoan(false, bookObject2);

        testObject.create(testBookLoan);
        testObject.create(bookLoan2);

        appUserObject1.createBookLoan(testBookLoan);
        appUserObject2.createBookLoan(bookLoan2);

        appUserDAOObject.update(appUserObject1);
        appUserDAOObject.update(appUserObject2);

        testBookLoan = testObject.update(testBookLoan);
        testObject.update(bookLoan2);

    }

    @Test
    @Order(1)
    @Transactional
    void create()
    {
        BookLoan actualData = null;
        BookLoan expectedData = null;
        try
        {
            AppUser appTemp = appUserDAOObject.findById(1).orElseThrow(() -> new DataNotFoundException("Not Found", " BookLoan"));
            Book bookTemp = bookDAOObject.findById(2);
            actualData = new BookLoan(false, bookTemp);
            appTemp.createBookLoan(actualData);
            testObject.create(actualData);
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
    @Transactional
    public void findId1()
    {
        try
        {
            BookLoan expectedData = testBookLoan;
            BookLoan actualData = testObject.findById(1);
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
    @DisplayName("test get all method from AppUserDAO with result 2")
    public void findAll()
    {
        int actualSize = testObject.findAll().size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(4)
    @DisplayName("test get delete method from AppUser, then get all method from AppUserDAO with result 1")
    public void delete()
    {
        try
        {
            testObject.delete(testBookLoan.getId());
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