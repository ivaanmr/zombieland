package com.rzg.zombieland.comunes.misc;

/**
 * Estado de la partida actual.
 * 
 * @author nicolas
 *
 */
public enum EstadoPartida {
    /**
     * Todav�a no ha arrancado, est� en la fase de espera de jugadores.
     */
    EN_ESPERA("En espera"),

    /**
     * La partida est� en progreso.
     */
    ACTIVA("Activa"),

    /**
     * La partida ha finalizado.
     */
    FINALIZADA("Finalizada"), 
    
    /**
     * Una ronda termin� y estamos esperando a la siguiente.
     */
    ENTRE_RONDAS("Entre rondas");

    // Una descripci�n amigable para el usuario del estado.
    private String descripcion;

    private EstadoPartida(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return una descripci�n amigable para el usuario.
     */
    public String getDescripcion() {
        return descripcion;
    }
}