package com.rzg.zombieland.server.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
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
     * Devuelve un controlador de acuerdo a la l�nea le�da. 
     * @param linea
     * @return
     * @throws ComandoDesconocidoException 
     */
    public static Controlador crear(int codigo) throws ComandoDesconocidoException {
        switch (codigo) {
        case Enviable.TEST:
            return new ControladorTest();
        case Enviable.REGISTRAR_JUGADOR:
            return new ControladorRegistro();
        default:
            throw new ComandoDesconocidoException(
                    String.format("El c�digo 0x%X no corresponde con "
                                + "ninguno de los comandos conocidos", codigo));
        }
    }
    
    /**
     * Procesa la petici�n definida por la l�nea enviada.
     * @param linea
     * @return
     */
    public abstract String procesar(String linea);
}
