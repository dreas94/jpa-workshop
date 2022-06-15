package se.lexicon.dreas94.jpaworkshop.dao.repo;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.dao.AppUserDAO;
import se.lexicon.dreas94.jpaworkshop.entity.AppUser;
import se.lexicon.dreas94.jpaworkshop.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDAORepository implements AppUserDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<AppUser> findById(int id)
    {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<AppUser> findAll()
    {
        return (List<AppUser>) entityManager.createQuery("select au from AppUser au").getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser)
    {
        if (appUser == null) throw new IllegalArgumentException("AppUser is null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser)
    {
        if (appUser == null) throw new IllegalArgumentException("AppUser is null");
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(AppUser appUser) throws DataNotFoundException
    {
        findById(appUser.getId()).orElseThrow(() -> new DataNotFoundException("Not Found", " AppUser"));
        entityManager.remove(entityManager.contains(appUser) ? appUser : entityManager.merge(appUser));
    }
}
