package pl.jazapp.app.webapp.auctions;

import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class AuctionRequest {
    private String title;

    private String description;

    private double price;

    private int version;

    private UserEntity created_by;

    private CategoryEntity category_id;

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
}
