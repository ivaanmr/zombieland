package com.rzg.zombieland.comunes.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Maneja un log de la aplicaci�n.
 * TODO implementar log4j.
 * @author nicolas
 *
 */
public abstract class Log {
	private static List<EscuchaLog> escuchadores = new ArrayList<EscuchaLog>();
	
	/**
	 * Muestra todas las entradas del log.
	 */
	public static final int DEBUG = 0;
	
	/**
	 * Muestra solo aquellas entradas que pueden aportar informaci�n �til a un usuario normal.
	 */
	public static final int INFO = 1;
	
	/**
	 * Muestra solo las entradas que detallan errores.
	 */
	public static final int ERROR = 2;
	
	/**
	 * Agrega un escuchador al log. El escuchador recibe todas las entradas de log.
	 * @param escuchador
	 */
	public static void agregarEscuchador(EscuchaLog escuchador) {
		escuchadores.add(escuchador);
	}
	
	/**
	 * Registra una entrada de log con la m�nima prioridad.
	 * @param mensaje
	 */
    public static void debug(Object mensaje) {
    	String imprimir = "[DEBUG] " + mensaje;
    	for (EscuchaLog escuchador : escuchadores)
    		escuchador.onLog(imprimir, DEBUG);
        System.out.println(imprimir);
    }
    
    /**
     * Registra una entrada de log con una prioridad intermedia.
     * @param mensaje
     */
    public static void info(Object mensaje) {
    	String imprimir = "[INFO] " + mensaje;
    	for (EscuchaLog escuchador : escuchadores)
    		escuchador.onLog(imprimir, INFO);
        System.out.println(imprimir);
    }

    /**
     * Registra una entrada de log con la m�xima prioridad.
     * @param mensaje
     */
    public static void error(Object mensaje) {
    	String imprimir = "[ERROR] " + mensaje;
    	for (EscuchaLog escuchador : escuchadores)
    		escuchador.onLog(imprimir, ERROR);
        System.err.println(imprimir);
    }
}

