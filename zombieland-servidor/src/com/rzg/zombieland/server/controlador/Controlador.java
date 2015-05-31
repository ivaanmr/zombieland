package com.rzg.zombieland.server.controlador;

import java.security.InvalidParameterException;

import com.rzg.zombieland.comunes.comunicacion.Enviable;

/**
 * Responde a una acci�n del cliente.
 * @author nicolas
 *
 */
public abstract class Controlador {
    /**
     * Devuelve un controlador de acuerdo a la l�nea le�da. 
     * @param linea
     * @return
     */
    public static Controlador crear(int codigo) {
        switch (codigo) {
        case Enviable.TEST:
            return new ControladorVacio();
        case Enviable.REGISTRAR_JUGADOR:
            return new ControladorRegistrarCliente();
        default:
            throw new InvalidParameterException(
                    String.format("El c�digo %h no corresponde con "
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
