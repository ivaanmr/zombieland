package com.rzg.zombieland.server.sesion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.sesion.Sesion.SesionListener;

/**
 * Verifica el correcto funcionamiento de la sesi�n.
 * @author nicolas
 *
 */
public class SesionTest {

    private final Jugador jugadorValido;
    
    private class SesionListenerImpl implements SesionListener {
     
        private boolean sesionCerrada = false;
        
        @Override
        public void notificarSesionCerrada(Sesion sesion) {
            sesionCerrada = true;
        }
        
        public boolean getSesionCerrada() {
            return sesionCerrada;
        }
    }
    
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

    /**
     * Prueba crear y cerrar una sesi�n.
     */
    @Test
    public void testCerrar() {
        Sesion sesion = new Sesion(jugadorValido, new EnviaPeticionesImpl());
        SesionListenerImpl listener = new SesionListenerImpl();
        sesion.addListener(listener);
        assertFalse(listener.getSesionCerrada());
        sesion.cerrar();
        assertTrue(listener.getSesionCerrada());
    }
    

    /**
     * Prueba crear y cerrar una sesi�n que ten�a una partida.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testAbandonarPartida() throws ParametrosNoValidosException, ZombielandException {
        Sesion sesion = new Sesion(jugadorValido, new EnviaPeticionesImpl());
        Partida partida = new Partida(jugadorValido, new POJOCreacionPartida(5, 5, "test"));
        assertEquals(1, partida.getJugadores().size());
        assertNull(sesion.getPartida());
        sesion.setPartida(partida);
        assertEquals(partida, sesion.getPartida());
        sesion.cerrar();
        assertEquals(0, partida.getJugadores().size());
        assertNull(sesion.getPartida());
    }
}
