package maven;

import maven.daos.AirplaneDaoH2;
import maven.entities.Airplane;
import maven.servicies.AirplaneService;

public class Main {
    public static void main(String[] args) throws Exception {
        Airplane airplane1 = new Airplane(1L, "aaa", "a1", "a1-789", "01/03/2022");
        Airplane airplane2 = new Airplane(2L, "bbb", "b1", "b1-789", "01/03/2022");

        AirplaneService airplaneService1 = new AirplaneService();

        //Set a sentence to persist (DAO)
        airplaneService1.setAirplaneDao(new AirplaneDaoH2());
        airplaneService1.registerAirplane(airplane1);

        airplaneService1.setAirplaneDao(new AirplaneDaoH2());
        airplaneService1.registerAirplane(airplane2);

        airplaneService1.findAll();
    }
}
