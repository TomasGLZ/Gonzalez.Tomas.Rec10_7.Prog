package parcialvideojuegos;

import config.config;
import model.CategoriaVideojuego;
import model.TorneoVideojuego;
import services.GestorEventos;

import java.time.LocalDate;
import java.util.List;

public class ParcialVideojuegos {

    public static void main(String[] args) {
        try {
            // Crear instancia del gestor
            GestorEventos<TorneoVideojuego> gestor = new GestorEventos<>();

            // Agregar torneos de videojuegos
            gestor.agregar(new TorneoVideojuego(1, "Campeonato de League of Legends",
                    LocalDate.of(2024, 12, 10), "Team Alpha", CategoriaVideojuego.ONLINE));
            gestor.agregar(new TorneoVideojuego(2, "Torneo de FIFA 24",
                    LocalDate.of(2024, 11, 20), "FIFA Legends", CategoriaVideojuego.PRESENCIAL));
            gestor.agregar(new TorneoVideojuego(3, "Competencia de Fortnite",
                    LocalDate.of(2024, 12, 5), "Battle Stars", CategoriaVideojuego.ONLINE));
            gestor.agregar(new TorneoVideojuego(4, "Campeonato de Valorant",
                    LocalDate.of(2025, 1, 15), "Valorant Pros", CategoriaVideojuego.PRESENCIAL));

            // Mostrar torneos agregados
            System.out.println("Torneos iniciales:");
            gestor.mostrarTodos();

            // Filtrar torneos por categoría ONLINE
            System.out.println("\nFiltrar por categoraa: ONLINE");
            List<TorneoVideojuego> torneosOnline = gestor.filtrar(t -> t.getCategoria() == CategoriaVideojuego.ONLINE);
            torneosOnline.forEach(System.out::println);

            // Filtrar torneos por rango de fechas
            System.out.println("\nFiltrar por rango de fechas (2024-12-01 a 2024-12-31):");
            List<TorneoVideojuego> rangoFechas = gestor.filtrar(t ->
                    !t.getFecha().isBefore(LocalDate.of(2024, 12, 1)) &&
                    !t.getFecha().isAfter(LocalDate.of(2024, 12, 31))
            );
            rangoFechas.forEach(System.out::println);

            // Ordenar torneos por nombre
            System.out.println("\nTorneos ordenados por nombre:");
            gestor.ordenar((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()));
            gestor.mostrarTodos();

            // Ordenar torneos por fecha (usa compareTo del modelo)
            System.out.println("\nTorneos ordenados por fecha:");
            gestor.ordenar(null); // Usa el criterio natural (compareTo)
            gestor.mostrarTodos();

            // Guardar y cargar en archivo binario
            gestor.guardarEnBinario(config.PATH_SER.toString());
            GestorEventos<TorneoVideojuego> gestorBinario = new GestorEventos<>();
            gestorBinario.cargarDesdeBinario(config.PATH_SER.toString());
            System.out.println("\nTorneos cargados desde archivo binario:");
            gestorBinario.mostrarTodos();

            // Guardar y cargar en archivo CSV
            gestor.guardarEnCSV(config.PATH_CSV.toString());
            GestorEventos<TorneoVideojuego> gestorCSV = new GestorEventos<>();
            gestorCSV.cargarDesdeCSV(config.PATH_CSV.toString(), TorneoVideojuego::fromCSV);
            System.out.println("\nTorneos cargados desde archivo CSV:");
            gestorCSV.mostrarTodos();

            // Eliminar torneo
            System.out.println("\nEliminar torneo con indice 1:");
            gestor.eliminar(1);
            gestor.mostrarTodos();

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
