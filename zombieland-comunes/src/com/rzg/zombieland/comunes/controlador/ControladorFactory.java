package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.misc.ZombielandException;


/**
 * Crea controladores para las distintas peticiones.
 * @author nicolas
 *
 */
public interface ControladorFactory {
    /**
     * @param codigo - el c�digo de petici�n.
     * @return un controlador que maneja la petici�n.
     * @throws ComandoDesconocidoException si la petici�n es desconocida.
     * @throws ZombielandException 
     */
    public Controlador crear(int codigo) throws ComandoDesconocidoException;
}
