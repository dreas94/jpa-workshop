package se.lexicon.dreas94.jpaworkshop.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.entity.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public class DetailsDAORepository implements DetailsDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Details> findById(int id)
    {
        return Optional.ofNullable(entityManager.find(Details.class, id));
    }

    @Override
    public Collection<Details> findAll()
    {
        return entityManager.createQuery("select d from Details d").getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details)
    {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details)
    {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(Details details)
    {
        findById(details.getDetailsId()).orElseThrow(() -> new IllegalArgumentException("Data not found Exception"));
        entityManager.remove(details);
    }
}
