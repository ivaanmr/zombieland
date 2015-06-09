package com.rzg.zombieland.server.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaLogin;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;

/**
 * Gestiona una petici�n de inicio de sesi�n, devolviendo el ID de sesi�n o un mensaje de error. 
 * @author nicolas
 */
public class ControladorInicioSesion extends Controlador {

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJOInicioSesion datos = gson.fromJson(linea, POJOInicioSesion.class);
        Jugador jugador = Jugador.iniciarSesion(datos.getNombre(), datos.getClave());
        if (jugador == null) {
            return gson.toJson(new RespuestaLogin(null, "No se pudo iniciar sesi�n. Verifique "
                                                      + "el usuario y contrase�a"));
        }
        String token = ServicioSesion.getInstancia().alta(jugador);
        return gson.toJson(new RespuestaLogin(token, null));
    }

}
