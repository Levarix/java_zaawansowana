package pl.jazapp.app.webapp.auctions;

import javax.persistence.*;

@Entity
@Table(name = "auction_parameter", schema = "public")
public class AuctionParameterEntity {
    @Id
    @Column(name = "auction_id")
    private Long auction_id;

    @Id
    @Column(name = "parameter_id", unique = true)
    private Long parameter_id;

    @Column(name = "value")
    private String value;

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public Long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Long parameter_id) {
        this.parameter_id = parameter_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
