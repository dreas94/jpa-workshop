package se.lexicon.dreas94.jpaworkshop.dao.repo;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.dao.AuthorDAO;
import se.lexicon.dreas94.jpaworkshop.entity.Author;
import se.lexicon.dreas94.jpaworkshop.entity.Book;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDAORepository implements AuthorDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author findById(int id) throws DataNotFoundException
    {
        return Optional.ofNullable(entityManager.find(Author.class, id)).orElseThrow(() -> new DataNotFoundException("Not Found", "Author"));
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Author> findAll()
    {
        return (List<Author>) entityManager.createQuery("select au from Author au").getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author)
    {
        if (author == null) throw new IllegalArgumentException("Author is null");
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author)
    {
        if (author == null) throw new IllegalArgumentException("Author is null");
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException
    {
        Optional.ofNullable(findById(id)).ifPresent(entityManager::remove);
    }
}
