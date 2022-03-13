package C2_A;

import java.time.LocalDate;

public abstract class Tarjeta {
    private int numFrente;
    private int codSeg;
    private LocalDate fechaExpiracion;

    public Tarjeta(int numFrente, int codSeg, LocalDate fechaExpiracion) {
        this.numFrente = numFrente;
        this.codSeg = codSeg;
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getNumFrente() {
        return numFrente;
    }

    public void setNumFrente(int numFrente) {
        this.numFrente = numFrente;
    }

    public int getCodSeg() {
        return codSeg;
    }

    public void setCodSeg(int codSeg) {
        this.codSeg = codSeg;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public void realizarPago(double monto) {
        if (fechaExpiracionOk() && procesarPago(monto)){
            System.out.println("Pago realizado");
        } else {
            System.out.println("Pago no realizado");
        }
    }

    protected abstract boolean procesarPago(double monto);

    public boolean fechaExpiracionOk(){
        return this.fechaExpiracion.isAfter(LocalDate.now());
    }

}
