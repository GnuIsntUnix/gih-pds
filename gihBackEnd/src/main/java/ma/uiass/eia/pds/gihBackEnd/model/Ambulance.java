package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TAmbulance")
public class Ambulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "miseEnCirculation")
    private Date dateMiseEnCirculation;
    @JsonIgnore
    @OneToMany(mappedBy = "ambulance")
    private List<Historique> historiques;
    @JsonIgnore
    @OneToMany(mappedBy = "ambulance")
    private List<Revision> revisions;
    public Ambulance() {
    }
    public Ambulance(Date dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public Ambulance(Date dateMiseEnCirculation, List<Historique> historiques, List<Revision> revisions) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.historiques = historiques;
        this.revisions = revisions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(Date dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }
}
