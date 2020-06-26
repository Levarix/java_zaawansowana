package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.webapp.users.UserContext;
import pl.jazapp.app.webapp.users.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Map;


@RequestScoped
@Named
public class AuctionController {

    @Inject
    AuctionRepository auctionRepository;

    @Inject
    UserContext userContext;

    @PersistenceContext
    private EntityManager em;

    public ArrayList<AuctionEntity> getAuctionList() {
        return auctionRepository.getAllAuctions();
    }

    public ArrayList<AuctionEntity> getUserAuctionList(){
        return auctionRepository.getUserAuctions(userContext.getId());
    }

    @Transactional
    public boolean editAuction(AuctionRequest auctionRequest) {
        Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return true;
    }
}
