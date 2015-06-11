package com.rzg.zombieland.cliente.comunicacion;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaLogin;

/**
 * Define una petici�n del cliente para autenticarse.
 * @author nicolas
 *
 */
public class PeticionInicioSesion extends Peticion<RespuestaLogin> {
    
    // Los datos de inicio de sesi�n que se env�an.
    private POJOInicioSesion pojo;
    
    /**
     * Construye una petici�n de login para el POJO indicado.
     * @param pojo
     */
    public PeticionInicioSesion(POJOInicioSesion pojo) {
        this.pojo = pojo;
    }

    @Override
    protected String getMensajePeticion() {
        return new Gson().toJson(pojo);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.INICIAR_SESION;
    }

    @Override
    protected RespuestaLogin generarRespuesta(String respuesta) {
        return new Gson().fromJson(respuesta, RespuestaLogin.class);
    }
}
