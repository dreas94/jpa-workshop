package se.lexicon.dreas94.jpaworkshop.dao;

import se.lexicon.dreas94.jpaworkshop.entity.BookLoan;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.util.Collection;

public interface BookLoanDAO
{
    BookLoan findById(int id) throws DataNotFoundException;

    Collection<BookLoan> findAll();

    BookLoan create(BookLoan bookLoan);

    BookLoan update(BookLoan bookLoan);

    void delete(int id) throws DataNotFoundException;
}
