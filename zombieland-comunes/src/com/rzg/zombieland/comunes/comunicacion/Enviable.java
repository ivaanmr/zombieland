package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define un componente que puede enviarse a trav�s de un socket. 
 * @author nicolas
 *
 */
public abstract class Enviable {
    // Constantes de tipos de peticiones.
    public static final int TEST = 0x0;
    public static final int REGISTRAR_JUGADOR = 0x1;

    /**
     * Crea un objeto enviable a partir de los bytes dados.
     * @return
     */
    protected Enviable(String bytes) { }
    
    /**
     * Constructor por defecto.
     * @param object
     */
    protected Enviable() { }
    
    /**
     * Convierte el objeto en bytes para su env�o.
     * @return una cadena de bytes para su env�o.
     */
    public abstract String serializar();
}
