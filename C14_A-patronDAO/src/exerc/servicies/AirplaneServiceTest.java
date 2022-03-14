package exerc.servicies;

import exerc.daos.AirplaneDaoH2;
import exerc.daos.IDao;
import exerc.entities.Airplane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneServiceTest {

    private IDao<Airplane> airplaneIDaoTest1 = new AirplaneDaoH2();
    private AirplaneService airplaneServiceTest1 = new AirplaneService();

    @BeforeEach
    void registerAirplane() throws Exception {
        Airplane airplane1 = new Airplane(1L, "aaa", "a1", "a1-789", "01/03/2022");
        Airplane airplane2 = new Airplane(2L, "bbb", "b1", "b1-789", "01/03/2022");
        airplaneServiceTest1.setAirplaneDao(airplaneIDaoTest1);
        airplaneServiceTest1.registerAirplane(airplane1);
        airplaneServiceTest1.registerAirplane(airplane2);
    }

    @Test
    void deleteAirplane() {
    }

    @Test
    void findOne() {
        Airplane airp1 = airplaneServiceTest1.findOne(1L);
        assertEquals(airp1.getBrand(), "aaa");
        assertEquals(airp1.getModel(), "a1");
        assertEquals(airp1.getLicense(), "a1-789");
        assertEquals(airp1.getStartService(), "01/03/2022");
    }

    @Test
    void findAll() {
    }
}