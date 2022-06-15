package se.lexicon.dreas94.jpaworkshop.dao.repo;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.dao.BookLoanDAO;
import se.lexicon.dreas94.jpaworkshop.entity.BookLoan;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class BookLoanDAORepository implements BookLoanDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookLoan findById(int id) throws DataNotFoundException
    {
        return Optional.ofNullable(entityManager.find(BookLoan.class, id)).orElseThrow(() -> new DataNotFoundException("Not Found", "BookLoan"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<BookLoan> findAll()
    {
        return (List<BookLoan>) entityManager.createQuery("select bl from BookLoan bl").getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan)
    {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan is null");
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan)
    {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan is null");
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException
    {
        Optional.ofNullable(findById(id)).ifPresent(entityManager::remove);
    }
}
