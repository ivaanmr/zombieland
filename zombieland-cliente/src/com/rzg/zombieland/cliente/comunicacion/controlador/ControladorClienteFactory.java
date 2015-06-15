package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorFactory;

/**
 * F�brica de controladores para el cliente.
 * @author nicolas
 *
 */
public class ControladorClienteFactory implements ControladorFactory {

    @Override
    public Controlador crear(int codigo) throws ComandoDesconocidoException {
        switch (codigo) {
        case Enviable.ACTUALIZACION_LOBBY:
            return new ControladorActualizacionLobby();
        default:
            throw new ComandoDesconocidoException(
                    "El comando " + codigo + " no es conocido por el cliente");
        }
    }

}