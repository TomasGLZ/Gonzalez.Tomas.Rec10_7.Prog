package model;

import java.time.LocalDate;

public class TorneoVideojuego extends Torneo implements Comparable<TorneoVideojuego>, CSVSerializable {
    private final String equipoPrincipal;
    private final CategoriaVideojuego categoria;

    public TorneoVideojuego(int id, String nombre, LocalDate fecha, String equipoPrincipal, CategoriaVideojuego categoria) {
        super(id, nombre, fecha);
        this.equipoPrincipal = equipoPrincipal;
        this.categoria = categoria;
    }

    public String getEquipoPrincipal() { return equipoPrincipal; }
    public CategoriaVideojuego getCategoria() { return categoria; }

    @Override
    public int compareTo(TorneoVideojuego otro) {
        return this.fecha.compareTo(otro.fecha);
    }

    @Override
    public String toCSV() {
        return id + "," + nombre + "," + fecha + "," + equipoPrincipal + "," + categoria;
    }

    public static String toHeaderCSV() {
        return "ID,Nombre,Fecha,Equipo Principal,Categoria";
    }

    public static TorneoVideojuego fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new TorneoVideojuego(
            Integer.parseInt(partes[0]),
            partes[1],
            LocalDate.parse(partes[2]),
            partes[3],
            CategoriaVideojuego.valueOf(partes[4])
        );
    }

    @Override
    public String toString() {
        return super.toString() + " - " + equipoPrincipal + " - " + categoria;
    }
}
