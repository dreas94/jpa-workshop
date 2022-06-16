package se.lexicon.dreas94.jpaworkshop.dao;

import se.lexicon.dreas94.jpaworkshop.entity.Author;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface AuthorDAO
{
    Author findById(int id) throws DataNotFoundException;

    Collection<Author> findAll();

    Author create(Author appUser);

    Author update(Author appUser);

    void delete(int id) throws DataNotFoundException;
}