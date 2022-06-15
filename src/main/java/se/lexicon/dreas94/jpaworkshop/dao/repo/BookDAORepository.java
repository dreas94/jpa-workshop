package se.lexicon.dreas94.jpaworkshop.dao.repo;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.dao.BookDAO;
import se.lexicon.dreas94.jpaworkshop.entity.Book;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAORepository implements BookDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book findById(int id) throws DataNotFoundException
    {
        return Optional.ofNullable(entityManager.find(Book.class, id)).orElseThrow(() -> new DataNotFoundException("Not Found", "Book"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Book> findAll()
    {
        return (List<Book>) entityManager.createQuery("select b from Book b").getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book)
    {
        if (book == null) throw new IllegalArgumentException("Book is null");
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book)
    {
        if (book == null) throw new IllegalArgumentException("AppUser is null");
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException
    {
        Optional.ofNullable(findById(id)).ifPresent(entityManager::remove);
    }
}
