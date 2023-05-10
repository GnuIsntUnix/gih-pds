package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TDMwithQuatity")
public class DMwithQuantity extends DM {

    @Column(name = "Quantité")
    private int quantite;

    @Override
    public String toString() {
        return "name : "+getNom()+" Quantité : "+quantite;
    }

    public DMwithQuantity() {
    }

    public DMwithQuantity(int quantite, String code, String nom, TypeDM typeDM, DetailDemandeDm detailDemandeDm, DetailLivraison detailLivraison, Stock stock) {
        super(code, nom, typeDM, detailDemandeDm, detailLivraison,stock);
        this.quantite = quantite;
    }

    public DMwithQuantity(int quantite,String code, String nom, TypeDM typeDM, Stock stock) {
        super(code, nom, typeDM,stock);
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


}
