package com.rzg.zombieland.server.sesion;

import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.server.meta.Partida;

/**
 * Rastrea el estado de un jugador: si ha iniciado sesi�n, en qu� partida est� jugando y a qu� 
 * personaje est� controlando.
 * @author nicolas
 *
 */
public class Sesion {
    // El jugador relacionado a la sesi�n.
    private Jugador jugador;
    
    // El ID �nico de la sesi�n.
    private UUID id;

    // La partida a la que est� asociada el jugador. Puede ser null.
    private Partida partida;
    
    // El hilo que est� atendiendo la sesi�n.
    private HiloEscucha hilo;
    
    /**
     * Construye una sesi�n a partir del jugador.
     * @param jugador
     * @param hilo - el hilo de escucha que atiende esta sesi�n.
     * @throws NullPointerException si el jugador es nulo.
     */
    public Sesion(Jugador jugador, HiloEscucha hilo) {
        if (jugador == null)
            throw new NullPointerException("El jugador no puede ser null");
        this.jugador = jugador;
        id = UUID.randomUUID();
    }

    /**
     * @return el ID de la sesi�n.
     */
    public UUID getId() {
        return id;
    }
    
    /**
     * @return el jugador asociado a la sesi�n.
     */
    public Jugador getJugador() {
        return jugador;
    }


    /**
     * @return la partida jugada en esta sesi�n. 
     */
    public Partida getPartida() {
        return partida;
    }
    
    /**
     * Establece la partida que el jugador comienza.
     * @param partida
     */
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    /**
     * @return el hilo de escucha.
     */
    public HiloEscucha getHilo() {
    	return hilo;
    }
}
