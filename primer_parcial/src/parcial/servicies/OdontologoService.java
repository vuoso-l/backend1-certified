package parcial.servicies;

import parcial.daos.IDao;
import parcial.models.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoDao;

    public OdontologoService() {

    }

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public void setOdontologoDao(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) throws Exception {
        odontologoDao.registrar(odontologo);
        return odontologo;
    }

    public void eliminarOdontologo(Long id) {
        odontologoDao.eliminar(id);
    }

    public Odontologo listarUno(Long id) {
        return odontologoDao.listarUno(id);
    }

    public List<Odontologo> listarTodos() {
        return odontologoDao.listarTodos();
    }
}
