package se.lexicon.dreas94.jpaworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.dreas94.jpaworkshop.dao.*;
import se.lexicon.dreas94.jpaworkshop.entity.*;

@Component
public class CommandLineRunnerExample implements CommandLineRunner
{

    AppUserDAO appUserDAO;
    DetailsDAO detailsDAO;
    BookDAO bookDAO;

    AuthorDAO authorDAO;
    BookLoanDAO bookLoanDAO;

    @Autowired
    public CommandLineRunnerExample(AppUserDAO appUserDAO, DetailsDAO detailsDAO, BookDAO bookDAO, AuthorDAO authorDAO, BookLoanDAO bookLoanDAO)
    {
        this.appUserDAO = appUserDAO;
        this.detailsDAO = detailsDAO;
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.bookLoanDAO = bookLoanDAO;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception
    {
        Details details1 = new Details("tras94@gmail.com", "Andreas", "1994-03-14");
        Details insertedDetailsRow1 = detailsDAO.create(details1);

        AppUser appUser1 = new AppUser("dreas94", "ewfw4et2r4", insertedDetailsRow1);

        AppUser insertedAppUserRow1 = appUserDAO.create(appUser1);
        System.out.println("insertedStudentRow1 = " + insertedAppUserRow1);


        // CascadeType.PERSIST
        AppUser appUser2 = new AppUser("mer89", "wef2342trg23",
                new Details("glitter89@gmail.com", "Mehrdad", "1989-02-27"));
        AppUser insertedAppUserRow2 = appUserDAO.create(appUser2);
        System.out.println("insertedStudentRow2 = " + insertedAppUserRow2);

        Book book1 = new Book("123454536", "Tester", 8);
        Book book2 = new Book("345194856", "Gralemald", 20);

        bookDAO.create(book1);
        bookDAO.create(book2);

        bookLoanDAO.create(new BookLoan(false,appUser1,book1));
        bookLoanDAO.create(new BookLoan(false,appUser1,book2));
        bookLoanDAO.create(new BookLoan(false,appUser2,book1));
        bookLoanDAO.create(new BookLoan(false,appUser2,book2));

        Author testAuthor1 = authorDAO.create(new Author("Andreas", "Eriksson"));

        testAuthor1.addBook(book1);

        Author testAuthor2 = authorDAO.create(new Author("Lilja", "Teufel"));

        testAuthor2.addBook(book2);

        testAuthor1 = authorDAO.update(testAuthor1);
        testAuthor2 = authorDAO.update(testAuthor1);
    }

}
