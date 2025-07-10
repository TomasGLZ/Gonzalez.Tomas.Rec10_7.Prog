package model;

public interface CSVSerializable {
    String toCSV();
    static String toHeaderCSV() { return ""; } // Sobrescribir en implementación
}
