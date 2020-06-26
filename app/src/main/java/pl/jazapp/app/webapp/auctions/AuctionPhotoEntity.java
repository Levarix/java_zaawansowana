package pl.jazapp.app.webapp.auctions;

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
}
