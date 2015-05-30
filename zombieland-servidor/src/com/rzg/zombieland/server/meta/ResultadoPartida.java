package com.rzg.zombieland.server.meta;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Almacena el resultado de una partida para un solo jugador con fin de registrar estad�sticas.
 * @author nicolas
 *
 */
@Entity
public class ResultadoPartida {
    // Identificaci�n del resultado.
    @Id
    private UUID id;
    
    // Puntos totales ganados en la partida.
    @Column
    private int puntos;
    
    // Indica el lugar en el que sali� el jugador (1ro, 2do, etc).
    @Column
    private int ranking;
    
    // El jugador para el que se registra la estad�stica.
    @Transient
    private Jugador jugador;
    
    /**
     * Constructor vac�o para Hibernate.
     */
    public ResultadoPartida() {
        
    }
    
    /**
     * Crea un nuevo resultado de partida.
     * @param puntos
     * @param ranking
     */
    public ResultadoPartida(int puntos, int ranking) {
        id = UUID.randomUUID();
        this.puntos = puntos;
        this.ranking = ranking;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public int getRanking() {
        return ranking;
    }
    
    @Override
    public String toString() {
        return "ResultadoPartida [id=" + id + ", puntos=" + puntos + ", ranking=" + ranking
                + ", jugador=" + jugador + "]";
    }
}
