package C2_A;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TarjetaDebitoTest {

    Tarjeta td1;
    TarjetaDebito td2;
    @BeforeEach
    void doBefore() {
        td1 = new TarjetaDebito(1234, 123, LocalDate.of(2023, 12, 31), 5000.00);
        td2 = new TarjetaDebito(4444, 123, LocalDate.of(2023, 12, 31), 5000.00);
    }

    @Test
    void testConstructor() {
        assertEquals(4444, td2.getNumFrente());
        assertEquals(123, td2.getCodSeg());
        assertEquals(LocalDate.of(2023, 12, 31), td2.getFechaExpiracion());
        assertEquals(5000.00, td2.getSaldoDisponible());
    }

    @Test
    void getSaldoDisponible() {
        assertEquals(5000.00, td2.getSaldoDisponible());
    }

    @Test
    void setSaldoDisponible() {
        td2.setSaldoDisponible(8000.00);
        assertEquals(8000.00, td2.getSaldoDisponible());
    }

    @Test
    void procesarPago() {
        double monto = 3000.00;
        assertTrue(td2.getSaldoDisponible() > monto);
    }
}