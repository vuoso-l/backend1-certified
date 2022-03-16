package maven.servicies;

import maven.daos.IDao;
import maven.entities.Airplane;

import java.util.List;

public class AirplaneService {
    private IDao<Airplane> airplaneDao;

    public AirplaneService() {

    }

    public AirplaneService(IDao<Airplane> airplaneDao) {
        this.airplaneDao = airplaneDao;
    }

    public void setAirplaneDao(IDao<Airplane> airplaneDao) {
        this.airplaneDao = airplaneDao;
    }

    public Airplane registerAirplane(Airplane airplane) throws Exception {
        airplaneDao.register(airplane);
        return airplane;
    }

    public void deleteAirplane(Long id) {
        airplaneDao.delete(id);
    }

    public Airplane findOne(Long id) {
        return airplaneDao.findOne(id);
    }

    public List<Airplane> findAll() {
        return airplaneDao.findAll();
    }
}
