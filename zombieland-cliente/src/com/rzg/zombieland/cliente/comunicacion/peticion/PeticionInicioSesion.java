package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Define una petici�n del cliente para autenticarse.
 * @author nicolas
 *
 */
public class PeticionInicioSesion extends Peticion<POJOInicioSesion, RespuestaGenerica> {
    
    /**
     * Construye una petici�n de login para el POJO indicado.
     * @param pojo
     */
    public PeticionInicioSesion(POJOInicioSesion pojo) {
        super(pojo, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.INICIAR_SESION;
    }
}
