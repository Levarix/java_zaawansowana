package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@ApplicationScoped
public class AuctionRepository {

    @PersistenceContext
    private EntityManager em;


    public ArrayList<AuctionEntity> getAllAuctions() {
        TypedQuery<AuctionEntity> getAllQuery = em.createQuery("SELECT d FROM AuctionEntity d", AuctionEntity.class);
        return ((ArrayList<AuctionEntity>) getAllQuery.getResultList());
    }


    public ArrayList<AuctionEntity> getUserAuctions(UserEntity userEntity) {
        TypedQuery<AuctionEntity> getAllQuery = em.createQuery("SELECT d FROM AuctionEntity d WHERE d.created_by = :user", AuctionEntity.class)
                .setParameter("user", userEntity);
        return ((ArrayList<AuctionEntity>) getAllQuery.getResultList());
    }

    public void removeAuction(AuctionEntity _auctionEntity) {
        AuctionEntity auctionEntity = findById(_auctionEntity.getId());
        em.remove(em.merge(_auctionEntity));
    }

    public AuctionEntity findById(Long auctionId) {
        return em.find(AuctionEntity.class, auctionId);
    }

}
