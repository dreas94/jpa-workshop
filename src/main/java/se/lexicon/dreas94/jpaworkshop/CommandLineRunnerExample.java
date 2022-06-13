package se.lexicon.dreas94.jpaworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.dreas94.jpaworkshop.dao.AppUserDAO;
import se.lexicon.dreas94.jpaworkshop.dao.DetailsDAO;
import se.lexicon.dreas94.jpaworkshop.entity.AppUser;
import se.lexicon.dreas94.jpaworkshop.entity.Details;

import java.time.LocalDate;

@Component
public class CommandLineRunnerExample implements CommandLineRunner {

    AppUserDAO appUserDAO;
    DetailsDAO detailsDAO;

    @Autowired
    public CommandLineRunnerExample(AppUserDAO appUserDAO, DetailsDAO detailsDAO) {
        this.appUserDAO = appUserDAO;
        this.detailsDAO = detailsDAO;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception
    {
        //Details details1 = new Details("tras94@gmail.com", "Andreas", LocalDate.parse("1994-03-14"));
        //Details insertedDetailsRow1 = detailsDAO.create(details1);
//
        //AppUser appUser1 = new AppUser("dreas94", "ewfw4et2r4", insertedDetailsRow1);
//
        //AppUser insertedAppUserRow1 = appUserDAO.create(appUser1);
        //System.out.println("insertedStudentRow1 = " + insertedAppUserRow1);
//
//
        //// CascadeType.PERSIST
        //AppUser appUser2 = new AppUser("mer89", "wef2342trg23",
        //        new Details("glitter89@gmail.com", "Mehrdad", LocalDate.parse("1989-02-27")));
        //AppUser insertedAppUserRow2 = appUserDAO.create(appUser2);
        //System.out.println("insertedStudentRow2 = " + insertedAppUserRow2);
//
        //// CascadeType.REMOVE
        ////appUserDAO.remove(insertedStudentRow1);


        // CascadeType.MERGE
        //insertedAppUserRow2.setUsername("TEST");
        //insertedAppUserRow2.getDetails().setName("ABCD");
        //AppUser updatedStudentRow2 = appUserDAO.update(insertedAppUserRow2);
        //System.out.println("updatedStudentRow2 = " + updatedStudentRow2);
    }

}
