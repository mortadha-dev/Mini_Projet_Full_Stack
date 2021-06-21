package oga.stage.product_management.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String Nom ;

    private int Quantity ;

    private boolean Disponible  ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date  DateModif ;

    @JsonIgnore
    @ManyToOne
   // @JoinColumn(name = "id")
    private Category category;


    public Product() {
    }

    public Product(long id, String nom, int quantity, boolean disponible, Timestamp dateCreation, Timestamp dateModif, Category category) {
        this.id = id;
        this.Nom = nom;
        this.Quantity = quantity;
        this.Disponible = disponible;
        this.DateCreation = dateCreation;
        this.DateModif = dateModif;
        this.category = category;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public Date getDateModif() {
        return DateModif;
    }

    public void setDateModif(Date dateModif) {
        DateModif = dateModif;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Quantity=" + Quantity +
                ", Disponible=" + Disponible +
                ", DateCreation=" + DateCreation +
                ", DateModif=" + DateModif +
                ", category=" + category +
                '}';
    }
}
