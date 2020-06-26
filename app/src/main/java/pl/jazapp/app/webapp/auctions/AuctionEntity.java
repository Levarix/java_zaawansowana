package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.users.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name ="auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price = 0.0;

    @Column(name = "version")
    private int version;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity created_by;

    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category_id;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "auctionEntity",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<AuctionPhotoEntity> auctionPhotoList;

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

    public UserEntity getCreated_by() {
        return created_by;
    }

    public void setCreated_by(UserEntity created_by) {
        this.created_by = created_by;
    }

    public CategoryEntity getCategory_id() {
        return category_id;
    }

    public void setCategory_id(CategoryEntity category_id) {
        this.category_id = category_id;
    }

    public List<AuctionPhotoEntity> getAuctionPhotoList() {
        return auctionPhotoList;
    }

    public void setAuctionPhotoList(List<AuctionPhotoEntity> auctionPhotoList) {
        this.auctionPhotoList = auctionPhotoList;
    }
}
