package com.rzg.zombieland.server.controlador;

import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Define un controlador que requiere del inicio de sesi�n para operar.
 * @author nicolas
 *
 */
public abstract class ControladorConSesion extends Controlador {

    private ManejadorSesion manejadorSesion;
    
    /**
     * Construye un controlador con sesi�n.
     * @param manejadorSesion - el manejador de la sesi�n.
     * @throws ZombielandException si la sesi�n es null (es decir, el jugador no ha iniciado 
     *         sesi�n).
     */
    public ControladorConSesion(ManejadorSesion manejadorSesion) throws ZombielandException {
        if (manejadorSesion.getSesion() == null)
            throw new ZombielandException("La acci�n requiere del inicio de sesi�n");
        this.manejadorSesion = manejadorSesion; 
    }
    
    /**
     * @return la sesi�n del jugador.
     */
    protected Sesion getSesion() {
        return manejadorSesion.getSesion();
    }
}
