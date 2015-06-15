package com.rzg.zombieland.comunes.comunicacion;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz para algo que env�a peticiones.
 * @author nicolas
 *
 */
public interface EnviaPeticiones {
    /**
     * Env�a una petici�n.
     * @param peticion
     * @throws ZombielandException
     */
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException;
}
