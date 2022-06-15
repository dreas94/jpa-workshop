package se.lexicon.dreas94.jpaworkshop.dao;

import se.lexicon.dreas94.jpaworkshop.entity.Book;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.util.Collection;

public interface BookDAO
{
    Book findById(int id) throws DataNotFoundException;

    Collection<Book> findAll();

    Book create(Book book);

    Book update(Book book);

    void delete(int id) throws DataNotFoundException;
}
