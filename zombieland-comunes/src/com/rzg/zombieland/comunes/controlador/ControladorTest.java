package com.rzg.zombieland.comunes.controlador;

import java.util.HashSet;
import java.util.Set;

/**
 * Un controlador que devuelve lo mismo que se envi� para testing.
 * @author nicolas
 *
 */
public class ControladorTest extends Controlador {
    
    // La �ltima l�nea que se proces�.
    private static Set<String> lineasProcesadas = new HashSet<String>();
    
    public ControladorTest() { }

    @Override
    public String procesar(String linea) {
        lineasProcesadas.add(linea);
        return linea;
    }
    
    /**
     * Devuelve true si este controlador ha procesado la l�nea de env�o dada.
     * @param lineaEnvio
     * @return
     */
    public static boolean proceso(String lineaEnvio) {
        return lineasProcesadas.contains(lineaEnvio);
    }
}
