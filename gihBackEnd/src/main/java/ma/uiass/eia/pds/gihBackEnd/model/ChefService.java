package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("ChefService")
public class ChefService extends Utilisateur{

}
