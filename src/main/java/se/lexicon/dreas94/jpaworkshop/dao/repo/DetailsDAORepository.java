package se.lexicon.dreas94.jpaworkshop.dao.repo;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.dao.DetailsDAO;
import se.lexicon.dreas94.jpaworkshop.entity.Details;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
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


    @SuppressWarnings("unchecked")
    @Override
    public List<Details> findAll()
    {
        return (List<Details>) entityManager.createQuery("select d from Details d").getResultList();
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
    public void delete(Details details) throws DataNotFoundException
    {
        findById(details.getId()).orElseThrow(() -> new DataNotFoundException("Not Found", " AppUser"));
        entityManager.remove(entityManager.contains(details) ? details : entityManager.merge(details));
    }
}
