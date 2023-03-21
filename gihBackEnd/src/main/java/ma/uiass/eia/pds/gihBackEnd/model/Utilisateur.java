package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Entity
@Table(name = "TUtilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Fonction", length = 255)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fonction")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChefService.class, name = "ChefService"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin")
})
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected int idUtilisateur;

    @Column(name = "NomUtilisateur")
    protected String nomUtil;

    @Column(name = "MotDePasse")
    protected String motDePasse;

    @Column(name = "Fonction", insertable = false, updatable = false)
    private String fonction;

    @JoinColumn(name = "idService", referencedColumnName = "Id")
    @OneToOne
    private Service service;


    public Utilisateur(String nomUtil, String motDePasse, String fonction, Service service) {
        this.nomUtil = nomUtil;
        this.motDePasse = motDePasse;
        this.fonction = fonction;
        this.service = service;
    }

    public Utilisateur(String nomUtil, String motDePasse) {
        this.nomUtil = nomUtil;
        this.motDePasse = motDePasse;
    }


    public Utilisateur() {
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
