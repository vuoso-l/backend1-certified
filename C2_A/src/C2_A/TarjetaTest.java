package C2_A;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TarjetaTest {

    Tarjeta td1;
    @BeforeEach
    void doBefore() {
        td1 = new TarjetaDebito(1234, 123, LocalDate.of(2023, 12, 31), 1000.00);
    }

    @Test
    void realizarPago() {
        if (td1.fechaExpiracionOk() && td1.procesarPago(3000.00)){
           assertEquals("Pago realizado", td1.fechaExpiracionOk() && td1.procesarPago(3000.00));
           assertTrue(td1.fechaExpiracionOk() && td1.procesarPago(3000.00));
        }
    }

    @Test
    void fechaExpiracionOk() {
        assertTrue(td1.getFechaExpiracion().isAfter(LocalDate.now()));
    }
}