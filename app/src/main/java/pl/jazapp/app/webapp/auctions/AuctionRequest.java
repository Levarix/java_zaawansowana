package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.auctions.photos.AuctionPhotoEntity;
import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.transform.sax.SAXResult;
import java.util.List;

@RequestScoped
@Named
public class AuctionRequest {
    private  Long id;
    private String title;
    private String description;
    private double price;
    private int version;
    private Long created_by;
    private Long category_id;
    private String photoUrl1;
    private String photoUrl2;
    private String photoUrl3;
    private List<AuctionPhotoEntity> auctionPhotoEntityList;

    public AuctionRequest(AuctionEntity auctionEntity) {
        this.id = auctionEntity.getId();
        this.title = auctionEntity.getTitle();
        this.description = auctionEntity.getDescription();
        this.price = auctionEntity.getPrice();
        this.created_by = auctionEntity.getCreated_by().getId();
        this.category_id = auctionEntity.getCategory_id().getId();
        this.auctionPhotoEntityList = auctionEntity.getAuctionPhotoList();
    }

    public AuctionRequest() {}

    public AuctionEntity auctionEntity() {
        var auctionEntity = new AuctionEntity();
        auctionEntity.setId(id);
        auctionEntity.setTitle(title);
        auctionEntity.setDescription(description);
        auctionEntity.setPrice(price);
        auctionEntity.setVersion(version);
        auctionEntity.setAuctionPhotoList(auctionPhotoEntityList);

        return auctionEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getPhotoUrl1() {
        return photoUrl1;
    }

    public void setPhotoUrl1(String photoUrl1) {
        this.photoUrl1 = photoUrl1;
    }

    public String getPhotoUrl2() {
        return photoUrl2;
    }

    public void setPhotoUrl2(String photoUrl2) {
        this.photoUrl2 = photoUrl2;
    }

    public String getPhotoUrl3() {
        return photoUrl3;
    }

    public void setPhotoUrl3(String photoUrl3) {
        this.photoUrl3 = photoUrl3;
    }

    public List<AuctionPhotoEntity> getAuctionPhotoEntityList() {
        return auctionPhotoEntityList;
    }

    public void setAuctionPhotoEntityList(List<AuctionPhotoEntity> auctionPhotoEntityList) {
        this.auctionPhotoEntityList = auctionPhotoEntityList;
    }
}
