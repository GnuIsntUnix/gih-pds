package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TDm")
public class DMwithExemplaire extends DM{

    @OneToMany(mappedBy = "dm", fetch = FetchType.LAZY)
    private List<ExemplaireDm> exemplaireDmList = new ArrayList<>();

    public DMwithExemplaire(String code, String nom, TypeDM typeDM, DetailDemandeDm detailDemandeDm, DetailLivraison detailLivraison,List<ExemplaireDm> exemplaireDmList) {
        super(code, nom, typeDM, detailDemandeDm, detailLivraison);
        this.exemplaireDmList = exemplaireDmList;
    }

    public DMwithExemplaire(String code, String nom, TypeDM typeDM, List<ExemplaireDm> exemplaireDmList) {
        super(code, nom, typeDM);
        this.exemplaireDmList = exemplaireDmList;
    }

    public DMwithExemplaire(String code, String nom, TypeDM typeDM) {
        super(code, nom, typeDM);
    }

    public List<ExemplaireDm> getExemplaireDmList() {
        return exemplaireDmList;
    }

    public void setExemplaireDmList(List<ExemplaireDm> exemplaireDmList) {
        this.exemplaireDmList = exemplaireDmList;
    }
}
