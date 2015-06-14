package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Define un controlador que requiere del inicio de sesi�n para operar.
 * @author nicolas
 *
 */
public abstract class ControladorConSesion extends Controlador {

    /**
     * El mensaje de error que se env�a cuando el usuario no est� autenticado.
     */
    public static final String MENSAJE_NO_AUTENTICADO = "La acci�n requiere del inicio de sesi�n";
    private ManejadorSesion manejadorSesion;
    
    /**
     * Construye un controlador con sesi�n.
     * @param manejadorSesion - el manejador de la sesi�n.
     * @throws ZombielandException si la sesi�n es null (es decir, el jugador no ha iniciado 
     *         sesi�n).
     */
    public ControladorConSesion(ManejadorSesion manejadorSesion) {
        this.manejadorSesion = manejadorSesion; 
    }
    
    @Override
    public final String procesar(String linea) {
        // TODO manejar mejor.
        if (manejadorSesion.getSesion() == null)
            return mensajeErrorNoAutenticado();
        return procesarAutenticado(linea);
    }

    /**
     * @return el mensaje de error para un usuario no autenticado. Aquellos herederos que no usen
     * respuestas gen�ricas deber�n sobrecargar el m�todo.
     */
    protected String mensajeErrorNoAutenticado() {
        return new Gson().toJson(new RespuestaGenerica(MENSAJE_NO_AUTENTICADO));
    }

    /**
     * @return la sesi�n del jugador.
     */
    protected Sesion getSesion() {
        return manejadorSesion.getSesion();
    }
    
    /**
     * Procesa una respuesta una vez que ya sabemos que est� autenticado.
     * @param respuesta
     * @return la respuesta procesada.
     */
    protected abstract String procesarAutenticado(String respuesta);
}
