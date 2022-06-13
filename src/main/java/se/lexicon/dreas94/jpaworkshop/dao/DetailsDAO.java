package se.lexicon.dreas94.jpaworkshop.dao;

import se.lexicon.dreas94.jpaworkshop.entity.Details;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface DetailsDAO
{
    Optional<Details> findById(int id);

    Collection<Details> findAll();

    Details create(Details details);

    Details update(Details details);

    void delete(Details details) throws DataNotFoundException;
}
