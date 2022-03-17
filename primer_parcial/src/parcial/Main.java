package parcial;

import parcial.daos.OdontologoDaoH2;
import parcial.models.Odontologo;
import parcial.servicies.OdontologoService;

public class Main {
    public static void main(String[] args) throws Exception {
        Odontologo odontologo1 = new Odontologo(123456789, "Lucas", "Vuoso");
        Odontologo odontologo2 = new Odontologo(147258369, "Juan", "García");
        Odontologo odontologo3 = new Odontologo(987654321, "Carlos", "Pérez");
        Odontologo odontologo4 = new Odontologo(963258741, "Mabel", "Gómez");

        OdontologoService odontologoService = new OdontologoService();

        odontologoService.setOdontologoDao(new OdontologoDaoH2());
        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);
        odontologoService.registrarOdontologo(odontologo3);
        odontologoService.registrarOdontologo(odontologo4);

        //odontologoService.eliminarOdontologo(8L);
        odontologoService.listarUno(1L);
        odontologoService.listarTodos();
    }
}
