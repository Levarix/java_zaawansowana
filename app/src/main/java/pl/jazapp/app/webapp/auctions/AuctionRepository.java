package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.users.UserContext;
import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class AuctionRepository {

    @PersistenceContext
    private EntityManager em;


    public ArrayList<AuctionEntity> getAllAuctions() {
        TypedQuery<AuctionEntity> getAllQuery = em.createQuery("SELECT d FROM AuctionEntity d", AuctionEntity.class);
        return ((ArrayList<AuctionEntity>) getAllQuery.getResultList());
    }

    public ArrayList<AuctionEntity> getUserAuctions(Long userId) {
        TypedQuery<AuctionEntity> getAllQuery = em.createQuery("SELECT d FROM AuctionEntity d WHERE created_by = :userId", AuctionEntity.class)
                .setParameter("userId", userId);
        return ((ArrayList<AuctionEntity>) getAllQuery.getResultList());
    }

    public static <T> Optional<T> findOrEmpty(final pl.jazapp.app.webapp.DaoRetriever<T> retriever) {
        try {
            return Optional.of(retriever.retrieve());
        } catch (NoResultException ex) {
            //log
        }
        return Optional.empty();
    }
}
