package com.rzg.zombieland.server.sesion;


/**
 * Define el comportamiento de una clase que maneja la sesi�n de un jugador para un hilo de escucha
 * particular.
 * @author nicolas
 *
 */
public interface ManejadorSesion {
    /**
     * Establece la sesi�n que el manejador administra.
     * @param sesion
     */
    public void setSesion(Sesion sesion);
    
    /**
     * @return la sesi�n administrada, o null si el jugador no inici� sesi�n.
     */
    public Sesion getSesion();
}
