package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.departments.DepartmentEntity;

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
