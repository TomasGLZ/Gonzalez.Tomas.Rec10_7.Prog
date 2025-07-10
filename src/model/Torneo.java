package model;
import java.time.LocalDate;
import java.io.Serializable;

public abstract class Torneo implements Serializable {
    protected int id;
    protected String nombre;
    protected LocalDate fecha;

    public Torneo(int id, String nombre, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + fecha;
    }
}
