package com.rzg.zombieland.server.sesion;

import com.rzg.zombieland.server.sesion.Sesion.SesionListener;


/**
 * Define el comportamiento de una clase que maneja la sesi�n de un jugador para un hilo de escucha
 * particular.
 * @author nicolas
 *
 */
public interface ManejadorSesion extends SesionListener {
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
