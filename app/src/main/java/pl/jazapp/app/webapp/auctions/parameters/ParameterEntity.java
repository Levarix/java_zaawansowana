package pl.jazapp.app.webapp.auctions.parameters;

import javax.persistence.*;

@Entity
@Table(name = "parameter")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="key")
    private String key;

}
