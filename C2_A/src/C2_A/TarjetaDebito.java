package C2_A;

import java.time.LocalDate;

public class TarjetaDebito extends Tarjeta{
    private double saldoDisponible;


    public TarjetaDebito(int numFrente, int codSeg, LocalDate fechaExpiracion, double saldoDisponible) {
        super(numFrente, codSeg, fechaExpiracion);
        this.saldoDisponible = saldoDisponible;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    @Override
    protected boolean procesarPago(double monto) {
        return saldoDisponible > monto;
    }
}
