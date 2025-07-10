package services;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import model.CSVSerializable;
import model.TorneoVideojuego;

public class GestorEventos<T extends CSVSerializable & Comparable<T>> implements Gestionable<T> {
    private List<T> elementos = new ArrayList<>();

    @Override
    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    
    @Override
    public void eliminar(int indice) {
        elementos.remove(indice);
    }

    @Override
    public T obtener(int indice) {
        return elementos.get(indice);
    }

    @Override
    public void limpiar() {
        elementos.clear();
    }

    @Override
    public void ordenar() {
        Collections.sort(elementos);
    }

    @Override
    public void ordenar(Comparator<T> comparador) {
        elementos.sort(comparador);
    }

    @Override
    public List<T> filtrar(Predicate<T> criterio) {
        return elementos.stream().filter(criterio).toList();
    }

    @Override
    public void guardarEnBinario(String ruta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(elementos);
        } catch (IOException e) {
        }
    }

    @Override
    public void cargarDesdeBinario(String ruta) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            elementos = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    @Override
    public void guardarEnCSV(String ruta) {
        try (PrintWriter pw = new PrintWriter(ruta)) {
            if (!elementos.isEmpty()) {
                pw.println(TorneoVideojuego.toHeaderCSV());
                for (T e : elementos) {
                    pw.println(e.toCSV());
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void cargarDesdeCSV(String ruta, Function<String, T> parser) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // saltar encabezado
            while ((linea = br.readLine()) != null) {
                agregar(parser.apply(linea));
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void mostrarTodos() {
        elementos.forEach(System.out::println);
    }
}
