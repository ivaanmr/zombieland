package com.rzg.zombieland.server.controlador;

/**
 * Un controlador que no hace nada para testing.
 * @author nicolas
 *
 */
public class ControladorVacio extends Controlador {
    
    // La �ltima l�nea que se proces�.
    private static String ultimaLineaProcesada;
    
    // La l�nea que se devolver� al procesar.
    private static String lineaDevolucion;
    
    public ControladorVacio() {
    }

    @Override
    public String procesar(String linea) {
        ultimaLineaProcesada = linea;
        return lineaDevolucion;
    }
    
    public static String getLineaDevolucion() {
        return lineaDevolucion;
    }

    public static void setLineaDevolucion(String lineaDevolucion) {
        ControladorVacio.lineaDevolucion = lineaDevolucion;
    }

    public static String getUltimaLineaProcesada() {
        return ultimaLineaProcesada;
    }
}
