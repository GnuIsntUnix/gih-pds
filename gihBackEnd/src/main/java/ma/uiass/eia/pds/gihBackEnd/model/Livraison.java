package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TLivraison")
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @OneToMany(mappedBy = "livraison", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailLivraison> detailLivraisonList;

    public Livraison(LocalDate date, List<DetailLivraison> detailLivraisonList) {
        this.date = date;
        this.detailLivraisonList = detailLivraisonList;
    }

    public Livraison() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public List<DetailLivraison> getDetailLivraisonList() {
        return detailLivraisonList;
    }

    public void setDetailLivraisonList(List<DetailLivraison> detailLivraisonList) {
        this.detailLivraisonList = detailLivraisonList;
    }
}
