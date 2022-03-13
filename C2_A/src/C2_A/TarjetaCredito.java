package C2_A;

import java.time.LocalDate;

public class TarjetaCredito extends Tarjeta{
    private double limite;
    private double saldoUtilizado;


    public TarjetaCredito(int numFrente, int codSeg, LocalDate fechaExpiracion, double limite, double saldoUtilizado) {
        super(numFrente, codSeg, fechaExpiracion);
        this.limite = limite;
        this.saldoUtilizado = saldoUtilizado;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    public void setSaldoUtilizado(double saldoUtilizado) {
        this.saldoUtilizado = saldoUtilizado;
    }

    @Override
    protected boolean procesarPago(double monto) {
        return limite - saldoUtilizado > monto;
    }
}
