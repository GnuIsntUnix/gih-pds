package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TDm")
public class DMwithQuantity extends DM {

    @Column(name = "Quantité")
    private int quantite;
    @JoinColumn(name = "idStock", referencedColumnName = "Id")
    @ManyToOne
    @JsonIgnore
    private Stock stock;
    @Override
    public String toString() {
        return "name : "+getNom()+" Quantité : "+quantite;
    }

    public DMwithQuantity(int quantite,String code, String nom, TypeDM typeDM, DetailDemandeDm detailDemandeDm, DetailLivraison detailLivraison,Stock stock) {
        super(code, nom, typeDM, detailDemandeDm, detailLivraison);
        this.quantite = quantite;
        this.stock=stock;
    }

    public DMwithQuantity(int quantite,String code, String nom, TypeDM typeDM, Stock stock) {
        super(code, nom, typeDM);
        this.quantite = quantite;
        this.stock = stock;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
