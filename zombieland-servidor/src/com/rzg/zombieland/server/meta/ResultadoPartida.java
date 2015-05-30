package com.rzg.zombieland.server.meta;

/**
 * Almacena el resultado de una partida para un solo jugador con fin de registrar estad�sticas.
 * @author nicolas
 *
 */
public class ResultadoPartida {
    // Puntos totales ganados en la partida.
    private int puntos;
    
    // Indica el lugar en el que sali� el jugador (1ro, 2do, etc).
    private int ranking;
    
    /**
     * Crea un nuevo resultado de partida.
     * @param puntos
     * @param ranking
     */
    public ResultadoPartida(int puntos, int ranking) {
        this.puntos = puntos;
        this.ranking = ranking;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public int getRanking() {
        return ranking;
    }
}
