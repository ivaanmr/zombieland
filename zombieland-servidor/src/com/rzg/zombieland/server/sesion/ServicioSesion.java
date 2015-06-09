package com.rzg.zombieland.server.sesion;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Singleton que administra la sesi�n de los jugadores. Su responsabilidad es registrar y rastrear
 * qui�n ha iniciado sesi�n, pero no valida los datos del jugador.
 * @author nicolas
 *
 */
public class ServicioSesion {

    // Instancia del servicio de sesi�n.
    private static ServicioSesion instancia;
    
    // Rastrea a los jugadores que han iniciado sesi�n, identific�ndolos a trav�s de un token 
    // generado aleatoriamente.
    private Map<String, Jugador> jugadoresActivos;
    
    private ServicioSesion() {
        jugadoresActivos = new HashMap<String, Jugador>();
    }
    
    /**
     * @return la instancia activa de {@link ServicioSesion}.
     */
    public static ServicioSesion getInstancia() {
        if (instancia == null)
            instancia = new ServicioSesion();
        return instancia;
    }

    /**
     * Inicia la sesi�n de un jugador.
     * @param jugador - el jugador que inicia sesi�n.
     * @return el token de inicio de sesi�n con el que se identifica un�vocamente.
     */
    public String alta(Jugador jugador) {
        String token = UUID.randomUUID().toString();
        jugadoresActivos.put(token, jugador);
        return token;
    }
}
