package com.rzg.zombieland.server.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Tests para el modelo de jugador.
 * @author nicolas
 *
 */
public class JugadorTest {

    /**
     * Intenta crear un jugador con todos los datos v�lidos.
     * @throws ZombielandException no deber�a!
     */
    @Test
    public void testCrearBien() throws ZombielandException {
        new Jugador("Juan", "1234", "1234", "Nombre de mi madre", "Mar�a");
    }
    
    /**
     * 
     * @throws ZombielandException
     */
    @Test(expected=ZombielandException.class)
    public void testCrearConClavesDistintas() throws ZombielandException {
        new Jugador("Juan", "1234", "12345", "Nombre de mi madre", "Mar�a");
    }

    /**
     * Intenta crear un jugador con sin nombre. Tiene que explotar.
     * @throws ZombielandException
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinNombre() throws ZombielandException {
        new Jugador("", "1234", "1234", "Nombre de mi madre", "Mar�a");
    }
    
    /**
     * Intenta crear un jugador sin clave. Tiene que explotar.
     * @throws ZombielandException
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinClave() throws ZombielandException {
        new Jugador("Juan", "", "", "Nombre de mi madre", "Mar�a");
    }
    
    /**
     * Intenta crear un jugador sin pregunta. Tiene que explotar.
     * @throws ZombielandException
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinPregunta() throws ZombielandException {
        new Jugador("Juan", "1234", "1234", "", "Mar�a");
    }
    
    /**
     * Intenta crear un jugador sin respuesta. Tiene que explotar.
     * @throws ZombielandException
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinRespuesta() throws ZombielandException {
        new Jugador("Juan", "1234", "1234", "Nombre de mi madre", "");
    }
    
    /**
     * Intenta crear un jugador sin nada. Tiene que explotar en todos los par�metros.
     */
    @Test
    public void testCrearSinNada() {
        try {
            new Jugador("", "", "validacionDistinta", "", "");
            fail("Deber�a haber lanzado una excepci�n");
        } catch (ZombielandException e) {
            assertEquals(5, e.getErrores().size());
        }
    }
}
