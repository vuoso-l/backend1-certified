package exerc;

import exerc.daos.AirplaneDaoH2;
import exerc.entities.Airplane;
import exerc.servicies.AirplaneService;

public class Main {
    public static void main(String[] args) throws Exception {
        Airplane airplane1 = new Airplane(4L, "aaa", "a1", "a1-789", "01/03/2022");

        AirplaneService airplaneService1 = new AirplaneService();

        //Set a sentence to persist (DAO)
        airplaneService1.setAirplaneDao(new AirplaneDaoH2());
        airplaneService1.registerAirplane(airplane1);


        airplaneService1.findAll();
    }
}
