package com.rzg.zombieland.server.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Gestiona una petici�n de inicio de sesi�n, devolviendo el ID de sesi�n o un mensaje de error. 
 * @author nicolas
 */
public class ControladorInicioSesion extends Controlador {
    
    // El manejador al que se le registra el jugador.
    private ManejadorSesion manejadorSesion;

    /**
     * Construye el controlador de inicio de sesi�n a partir del manejador.
     * @param manejadorSesion el manejador al que se le registrar� el jugador.
     */
    public ControladorInicioSesion(ManejadorSesion manejadorSesion) {
        this.manejadorSesion = manejadorSesion;
    }

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJOInicioSesion datos = gson.fromJson(linea, POJOInicioSesion.class);
        Jugador jugador = Jugador.iniciarSesion(datos.getNombre(), datos.getClave());
        if (jugador == null) {
            return gson.toJson(new RespuestaGenerica("No se pudo iniciar sesi�n. Verifique "
                                                      + "el usuario y contrase�a"));
        }
        Log.info("El jugador " + jugador.getNombre() + " ha iniciado sesi�n.");
        Sesion sesion = new Sesion(jugador);
        ServicioSesion.getInstancia().addSesion(sesion);
        manejadorSesion.setSesion(sesion);
        
        return gson.toJson(new RespuestaGenerica());
    }

}
