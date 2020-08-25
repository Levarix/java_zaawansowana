package pl.jazapp.app.services.auctions;

import pl.jazapp.app.entities.AuctionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class SaveAuctionService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(AuctionEntity auctionEntity) {
        if(auctionEntity.getId() == null) {
            em.persist(auctionEntity);
        } else {
            em.merge(auctionEntity);
        }
    }
}
