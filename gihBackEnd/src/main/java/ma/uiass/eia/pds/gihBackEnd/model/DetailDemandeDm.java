package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "TDetailDemande")
public class DetailDemandeDm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @OneToOne
    @JoinColumn(name = "idDm", referencedColumnName = "Id")
    @JsonInclude
    private DM dm;




    @Column(name = "Qte")
    private int qte;

    @JoinColumn(name = "idDemande", referencedColumnName = "Id")
    @ManyToOne
    @JsonIgnore
    private DemandeDm demandeDm;


    public DM getDm() {
        return dm;
    }

    public void setDm(DM dm) {
        this.dm = dm;
    }

    public DetailDemandeDm(DM dm, int qte, DemandeDm demandeDm) {
        this.dm = dm;
        this.qte = qte;
        this.demandeDm = demandeDm;
    }
    public DetailDemandeDm(DM dm, int qte) {
        this.dm = dm;
        this.qte = qte;

    }

    public DetailDemandeDm() {
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public DemandeDm getDemandeDm() {
        return demandeDm;
    }

    public void setDemandeDm(DemandeDm demandeDm) {
        this.demandeDm = demandeDm;
    }

    @Override
    public String toString() {
        return  dm +
                ", qte=" + qte;
    }
}
