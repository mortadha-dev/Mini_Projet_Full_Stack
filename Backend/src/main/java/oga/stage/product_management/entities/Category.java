package oga.stage.product_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String nom  ;

    private int quantity ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif ;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Product> products ;




}
