package ma.uiass.eia.pds.gihBackEnd.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Utilisateur{
    public Admin(String nomUtil, String motDePasse) {
        super(nomUtil, motDePasse);
    }

    public Admin() {
    }
}
