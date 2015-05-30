package com.rzg.zombieland.server.meta;

import java.util.List;

/**
 * Modela al usuario del cliente de Zombieland.
 * 
 * @author nicolas
 *
 */
public class Jugador {
    /**
     * Verifica nombre y clave del jugador, y devuelve un objeto jugador si logr�
     * iniciar sesi�n. 
     * @param nombre
     * @param clave
     * @return
     */
    public static Jugador inicarSesion(String nombre, String clave) {
        // TODO implementar.
        return null;
    }

    // Nombre de usuario.
    private String nombre;

    // Clave de acceso al sistema.
    private String clave;

    // Pregunta de seguridad que se env�a al usuario en caso de errores al
    // iniciar sesi�n.
    private String preguntaSecreta;

    // Respuesta a la pregunta secreta de inicio de sesi�n.
    private String respuestaSecreta;

    // Indica el ranking del jugador en la tabla general.
    private int ranking;

    // Resultados de partidas hist�ricos.
    private List<ResultadoPartida> historicoPartidas;
    
    /**
     * Crea un nuevo jugador.
     * 
     * @param nombre
     * @param clave
     * @param validacionClave
     * @param preguntaSecreta
     */
    public Jugador(String nombre, String clave, String validacionClave,
                   String preguntaSecreta, String respuestaSecreta) {
        // TODO implementar.
    }

    /**
     * @return las partidas que lleva jugadas hist�ricamente.
     */
    public int getPartidasJugadas() {
        // TODO implementar.
        return 0;
    }

    /**
     * @return las partidas que lleva ganadas hist�ricamente.
     */
    public int getPartidasGanadas() {
        // TODO implementar.
        return 0;
    }
}
