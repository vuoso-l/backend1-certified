package parcial.servicies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parcial.daos.IDao;
import parcial.daos.OdontologoDaoH2;
import parcial.models.Odontologo;

class OdontologoServiceTest {

    private IDao<Odontologo> odontologoIDaoTest1 = new OdontologoDaoH2();
    private OdontologoService odontologoServiceTest1 = new OdontologoService();

    @BeforeEach
    void setUp() throws Exception {
        Odontologo odontologo1 = new Odontologo(123456789, "Lucas", "Vuoso");
        Odontologo odontologo2 = new Odontologo(147258369, "Juan", "García");
        Odontologo odontologo3 = new Odontologo(987654321, "Carlos", "Pérez");
        Odontologo odontologo4 = new Odontologo(963258741, "Mabel", "Gómez");

        odontologoServiceTest1.setOdontologoDao(odontologoIDaoTest1);

        /*odontologoServiceTest1.registrarOdontologo(odontologo1);
        odontologoServiceTest1.registrarOdontologo(odontologo2);
        odontologoServiceTest1.registrarOdontologo(odontologo3);
        odontologoServiceTest1.registrarOdontologo(odontologo4);*/

    }

    @Test
    void listarTodos() {
        odontologoServiceTest1.listarTodos();
    }
}