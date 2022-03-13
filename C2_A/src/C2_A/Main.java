package C2_A;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Tarjeta tc1 = new TarjetaCredito(1234, 123, LocalDate.of(2023, 12, 31), 100000.00, 5000.00);
        Tarjeta td1 = new TarjetaDebito(1234, 123, LocalDate.of(2023, 12, 31), 5000.00);

        tc1.realizarPago(10000.00);
        td1.realizarPago(4000.00);
    }
}
