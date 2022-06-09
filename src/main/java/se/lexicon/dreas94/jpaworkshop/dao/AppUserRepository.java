package se.lexicon.dreas94.jpaworkshop.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.dreas94.jpaworkshop.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AppUserRepository implements AppUserDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<AppUser> findById(int id)
    {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }

    @Override
    public Collection<AppUser> findAll()
    {
        return entityManager.createQuery("select a from AppUser a").getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser)
    {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser)
    {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(AppUser appUser)
    {
        findById(appUser.getAppUserId()).orElseThrow(() -> new IllegalArgumentException("Data not found Exception"));
        entityManager.remove(appUser);
    }
}
