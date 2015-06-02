package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Responde a una acci�n del cliente.
 * @author nicolas
 *
 */
public abstract class Controlador {
    public static class ComandoDesconocidoException extends ZombielandException {
        private static final long serialVersionUID = -8914691837771387774L;

        public ComandoDesconocidoException(String error) {
            super(error);
        }
    }
    
    /**
     * Procesa la petici�n definida por la l�nea enviada.
     * @param linea
     * @return
     */
    public abstract String procesar(String linea);
}
