package com.rzg.zombieland.server.sesion;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;

/**
 * Verifica el correcto funcionamiento de la sesi�n.
 * @author nicolas
 *
 */
public class SesionTest {

    private final Jugador jugadorValido;
    
    /**
     * Inicializa al jugador v�lido.
     * @throws ParametrosNoValidosException
     */
    public SesionTest() throws ParametrosNoValidosException {
        jugadorValido = new Jugador("a", "b", "b", "c", "d");
    }
    
    /**
     * Prueba generar una sesi�n con datos v�lidos.
     */
    @Test
    public void testDatosValidos() {
        Sesion sesion = new Sesion(jugadorValido, new EnviaPeticionesImpl());
        Assert.assertEquals(jugadorValido, sesion.getJugador());
    }

    /**
     * Prueba generar una sesi�n con un jugador nulo y espera la excepci�n.
     */
    @Test(expected = NullPointerException.class)
    public void testJugadorNull() {
        new Sesion(null, new EnviaPeticionesImpl());
    }
    
    /**
     * Prueba generar una sesi�n con un hilo nulo y espera la excepci�n.
     */
    @Test(expected = NullPointerException.class)
    public void testEnviaPeticionesNull() {
        new Sesion(jugadorValido, null);
    }

}
