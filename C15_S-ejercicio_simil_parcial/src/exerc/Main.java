package exerc;

import exerc.daos.PatientDaoH2;
import exerc.models.Address;
import exerc.models.Patient;
import exerc.servicies.PatientService;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        Address dom1 = new Address("bol", 123, "www", "eee");
        Patient pat1 = new Patient("vuoso", "bbb", 123456789, new Date(2022, 03, 01), dom1);

        PatientService patService1 = new PatientService();

        //Set a sentence to persist (DAO)
        patService1.setPatientDao(new PatientDaoH2());
        //patService1.registerPatient(pat1);
        patService1.findOne(1L);
        //patService1.deletePatient(7L);
        //patService1.findAll();

    }
}
