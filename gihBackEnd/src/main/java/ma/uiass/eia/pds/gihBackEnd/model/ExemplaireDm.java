package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "TExemplairesDM")
public class ExemplaireDm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Nom")
    private String code;

    @ManyToOne
    @JoinColumn(name = "idDM", referencedColumnName = "Id")
    @JsonIgnore
    private DM dm;

    @JoinColumn(name = "idStock", referencedColumnName = "Id")
    @ManyToOne
    private Stock stock;


    public ExemplaireDm() {
    }

    public ExemplaireDm(DM dm, Stock stock) {
        this.dm = dm;
        this.stock = stock;
        this.code = dm.getNom();
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DM getDm() {
        return dm;
    }

    public void setDm(DM dm) {
        this.dm = dm;
    }
}
