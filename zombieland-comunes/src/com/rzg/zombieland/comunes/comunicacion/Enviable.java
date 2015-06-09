package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define un componente que puede enviarse a trav�s de un socket, y contiene las constantes de 
 * tipos de peticiones. 
 * @author nicolas
 *
 */
public abstract class Enviable {
    /**
     * Prueba para los tests. 
     */
    public static final int TEST = 0x0;
    
    /**
     * Registrar un jugador nuevo.
     */
    public static final int REGISTRAR_JUGADOR = 0x1;
    
    /**
     * Respuesta a una petici�n anterior. 
     */
    public static final int RESPUESTA = 0x2;
    
    /**
     * Iniciar sesi�n de un jugador existente. 
     */
    public static final int INICIAR_SESION = 0x3;
    
    /**
     * Indica que ha habido un error en el proceso.
     */
    public static final String LINEA_ERROR = "__!!__ERROR__!!__";
    
}
