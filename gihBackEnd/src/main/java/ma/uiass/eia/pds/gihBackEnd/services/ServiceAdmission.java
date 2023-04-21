package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.AdmissionDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IAdmissionDao;
import ma.uiass.eia.pds.gihBackEnd.model.Admission;

public class ServiceAdmission {
    private IAdmissionDao admissionDao;
    public ServiceAdmission(){
        admissionDao = new AdmissionDaoImp();
    }
    public void add (Admission admission){
        admissionDao.create(admission);
    }
}
