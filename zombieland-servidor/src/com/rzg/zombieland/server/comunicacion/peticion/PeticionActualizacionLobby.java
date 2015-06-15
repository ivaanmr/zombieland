package com.rzg.zombieland.server.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Env�a una petici�n de actualizaci�n de lobby a los clientes.
 * @author nicolas
 *
 */
public class PeticionActualizacionLobby extends Peticion<POJOPartida, RespuestaGenerica> {

    public PeticionActualizacionLobby(POJOPartida enviable) {
        super(enviable, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.ACTUALIZACION_LOBBY;
    }

}
