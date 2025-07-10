package services;

import java.util.List;
import java.util.function.Predicate;
import java.util.Comparator;

public interface Gestionable<T> {
    void agregar(T elemento);
    void eliminar(int indice);
    T obtener(int indice);
    void limpiar();
    void ordenar();
    void ordenar(Comparator<T> comparador);
    List<T> filtrar(Predicate<T> criterio);
    void guardarEnBinario(String ruta);
    void cargarDesdeBinario(String ruta);
    void guardarEnCSV(String ruta);
    void cargarDesdeCSV(String ruta, java.util.function.Function<String, T> parser);
    void mostrarTodos();
}
