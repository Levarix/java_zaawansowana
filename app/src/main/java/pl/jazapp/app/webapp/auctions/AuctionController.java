package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.auctions.photos.AuctionPhotoEntity;
import pl.jazapp.app.webapp.auctions.photos.AuctionPhotoRepository;
import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.categories.CategoryRepository;
import pl.jazapp.app.webapp.users.UserContext;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RequestScoped
@Named
public class AuctionController {

    @Inject
    AuctionRepository auctionRepository;

    @Inject
    AuctionPhotoRepository auctionPhotoRepository;

    @Inject
    UserContext userContext;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    SaveAuctionService saveAuctionService;

    @PersistenceContext
    private EntityManager em;

    private AuctionRequest auctionRequest;

    public List<CategoryEntity> getCategoryList() {
        return categoryRepository.getAll();
    }

    public ArrayList<AuctionEntity> getAuctionList() {
        return auctionRepository.getAllAuctions();
    }

    public ArrayList<AuctionEntity> getUserAuctionList() {
        return auctionRepository.getUserAuctions(userContext.getUserEntity());
    }

    public AuctionRequest getAuctionRequest() {
        Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (auctionRequest == null) {
            if (param.containsKey("auctionId")) {
                var auctionId = Long.parseLong(param.get("auctionId"));
                var auctionEntity = auctionRepository.findById(auctionId);

                auctionRequest = new AuctionRequest(auctionEntity);
            } else {
                auctionRequest = new AuctionRequest();
            }
        }
        return auctionRequest;
    }


    @Transactional
    public String edit() {

        var categoryEntity = categoryRepository.findById(auctionRequest.getCategory_id());
        var auctionEntity = auctionRequest.auctionEntity();
        var userEntity = userContext.getUserEntity();

        auctionEntity.setCategory_id(categoryEntity);
        auctionEntity.setCreated_by(userEntity);

        var auctionPhotos = new LinkedList<AuctionPhotoEntity>();

        var order = 0L;
        if(auctionRequest != null) {
            if(auctionRequest.getPhotoUrl1() != null)
            auctionPhotos.add(new AuctionPhotoEntity(null, auctionEntity, auctionRequest.getPhotoUrl1(), order));
            order++;
            if(auctionRequest.getPhotoUrl2() != null)
            auctionPhotos.add(new AuctionPhotoEntity(null, auctionEntity, auctionRequest.getPhotoUrl2(), order));
            order++;
            if(auctionRequest.getPhotoUrl3() != null)
            auctionPhotos.add(new AuctionPhotoEntity(null, auctionEntity, auctionRequest.getPhotoUrl3(), order));
        }

        auctionEntity.setAuctionPhotoList(auctionPhotos);
        saveAuctionService.save(auctionEntity);

        return "/auctions/mine.xhtml?faces-redirect=true";
    }

    @Transactional
    public String remove(AuctionEntity auctionEntity){
        auctionRepository.removeAuction(auctionEntity);

        return"/auctions/mine.xhtml?faces-redirect=true";
    }

    @Transactional
    public String removePhoto(AuctionPhotoEntity auctionPhotoEntity){
        auctionPhotoRepository.removePhoto(auctionPhotoEntity);

        return"/auctions/mine.xhtml?faces-redirect=true";
    }
}
