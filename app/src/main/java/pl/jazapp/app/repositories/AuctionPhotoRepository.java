package pl.jazapp.app.repositories;

import pl.jazapp.app.webapp.auctions.AuctionEntity;
import pl.jazapp.app.webapp.auctions.photos.AuctionPhotoEntity;
import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@ApplicationScoped
public class AuctionPhotoRepository {

    @PersistenceContext
    private EntityManager em;

    public ArrayList<AuctionPhotoEntity> getPhotosByAuction(AuctionEntity auctionEntity) {
        TypedQuery<AuctionPhotoEntity> getAllQuery = em.createQuery("SELECT d FROM AuctionPhotoEntity d WHERE d.auctionEntity = :auction", AuctionPhotoEntity.class)
                .setParameter("auction", auctionEntity);
        return ((ArrayList<AuctionPhotoEntity>) getAllQuery.getResultList());
    }

    public void removePhoto(AuctionPhotoEntity _auctionPhotoEntity) {
        AuctionPhotoEntity auctionPhotoEntity = findById(_auctionPhotoEntity.getId());
        em.remove(em.merge(_auctionPhotoEntity));
    }

    public AuctionPhotoEntity findById(Long auctionPhotoId) {
        return em.find(AuctionPhotoEntity.class, auctionPhotoId);
    }
}
