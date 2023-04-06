package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;

import java.util.List;

public interface ILitDao extends Dao<Lit>{
    List<Lit> getLitbyNumber(int quantite, TypeLit typeLit);
}
