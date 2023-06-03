package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TDMwithExemplaire")
public class DMwithExemplaire extends DM{

    @OneToMany(mappedBy = "dm", fetch = FetchType.LAZY)
    private List<ExemplaireDm> exemplaireDmList = new ArrayList<>();

    public DMwithExemplaire() {
    }

    public DMwithExemplaire(String code, String nom, TypeDM typeDM, DetailDemandeDm detailDemandeDm, DetailLivraison detailLivraison, List<ExemplaireDm> exemplaireDmList, Stock stock) {
        super(code, nom, typeDM, detailDemandeDm, detailLivraison,stock);
        this.exemplaireDmList = exemplaireDmList;
    }

    public DMwithExemplaire(String code, String nom, TypeDM typeDM, List<ExemplaireDm> exemplaireDmList) {
        super(code, nom, typeDM);
        this.exemplaireDmList = exemplaireDmList;
    }
    public DMwithExemplaire(String code, String nom, TypeDM typeDM, List<ExemplaireDm> exemplaireDmList,Stock stock) {
        super(code, nom, typeDM,stock);
        this.exemplaireDmList = exemplaireDmList;
    }

    public DMwithExemplaire(String code, String nom, TypeDM typeDM, Stock stock) {
        super(code, nom, typeDM,stock);
    }

    public List<ExemplaireDm> getExemplaireDmList() {
        return exemplaireDmList;
    }

    public void setExemplaireDmList(List<ExemplaireDm> exemplaireDmList) {
        this.exemplaireDmList = exemplaireDmList;
    }
    @Override
    public String toString() {
        return getNom();
    }
}
