package oga.stage.product_management.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String Nom  ;

    private int Quantity ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date DateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date  DateModif ;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Product> products ;

    public Category() {
    }

    public Category(long id, String nom, int quantity, Timestamp dateCreation, Timestamp dateModif, List<Product> products) {
        this.id = id;
        this.Nom = nom;
        this.Quantity = quantity;
        this.DateCreation = dateCreation;
        this.DateModif = dateModif;
        this.products = products;
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

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        DateCreation = dateCreation;
    }

    public Date getDateModif() {
        return DateModif;
    }

    public void setDateModif(Timestamp dateModif) {
        DateModif = dateModif;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", DateCreation=" + DateCreation +
                ", DateModif=" + DateModif +
                ", products=" + products +
                '}';
    }
}
