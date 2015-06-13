package com.rzg.zombieland.server.controlador;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Verifica el correcto funcionamiento del controlador de creaci�n de partida.
 * @author nicolas
 *
 */
public class ControladorCrearPartidaTest {

    /**
     * Test b�sico de creaci�n de partida.
     * @throws ZombielandException
     */
    @Test
    public void test() throws ZombielandException {
        // Inicializaci�n.
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ControladorCrearPartida controlador = new ControladorCrearPartida(manejador);
        Gson gson = new Gson();
        POJOCreacionPartida pojo = new POJOCreacionPartida(5, 10, "comer cerebros");
        
        Assert.assertNull(manejador.getSesion().getPartida());
        
        String respuesta = controlador.procesar(gson.toJson(pojo));
        RespuestaGenerica objetoRespuesta = gson.fromJson(respuesta, RespuestaGenerica.class);
        
        Assert.assertTrue(objetoRespuesta.fuePeticionExitosa());
        Assert.assertNotNull(manejador.getSesion().getPartida());
    }

}
