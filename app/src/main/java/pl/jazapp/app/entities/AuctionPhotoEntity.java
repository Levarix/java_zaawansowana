package pl.jazapp.app.entities;

import javax.persistence.*;

@Entity
@Table(name ="auction_photo")
public class AuctionPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private AuctionEntity auctionEntity;

    @Column(name = "url")
    private String url;

    @Column(name = "orderId")
    private Long orderId;

    public AuctionPhotoEntity(Long id, AuctionEntity auctionEntity, String url, Long orderId) {
        this.id = id;
        this.auctionEntity = auctionEntity;
        this.url = url;
        this.orderId = orderId;
    }

    public AuctionPhotoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuctionEntity getAuction() {
        return auctionEntity;
    }

    public void setAuction(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
