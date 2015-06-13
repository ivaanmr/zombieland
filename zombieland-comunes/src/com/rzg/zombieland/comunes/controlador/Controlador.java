package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Responde a una acci�n del cliente.
 * @author nicolas
 *
 */
public abstract class Controlador {
    /**
     * Indica que el comando solicitado no est� en el sistema.
     * @author nicolas
     */
    public static class ComandoDesconocidoException extends ZombielandException {
        private static final long serialVersionUID = -8914691837771387774L;

        /**
         * Constructor con mensaje de error.
         * @param error
         */
        public ComandoDesconocidoException(String error) {
            super(error);
        }
    }
    
    /**
     * Procesa la petici�n definida por la l�nea enviada.
     * @param linea
     * @return la respuesta que se debe imprimir en el b�fer de salida.
     * @throws ZombielandException 
     */
    public abstract String procesar(String linea);
}
