package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;


/**
 * Engloba los datos necesarios para crear una partida.
 * @author nicolas
 *
 */
public class POJOCreacionPartida {
    /**
     * No puede haber, estrictamente, menos rondas que este n�mero.
     */
    public static final int CANTIDAD_MINIMA_RONDAS = 1;
    
    /**
     * No puede haber, estrictamente, m�s rondas que este n�mero.
     */
    public static final int CANTIDAD_MAXIMA_RONDAS = 10;
    
    /**
     * No puede haber, estrictamente, menos jugadores que este n�mero.
     */
    public static final int CANTIDAD_MINIMA_JUGADORES = 4;
    
    /**
     * No puede haber, estrictamente, m�s jugadores que este n�mero.
     */
    public static final int CANTIDAD_MAXIMA_JUGADORES = 20;
    
    // Par�metros de la partida.
    private int cantidadRondas;
    private int cantidadMaximaJugadores;

    /**
     * Crea un POJO de creaci�n de partida.
     * @param cantidadRondas - la cantidad de rondas a la que se juega la partida.
     * @param cantidadMaximaJugadores - la cantidad m�xima de jugadores para la partida.
     * @throws ParametrosNoValidosException si
     * <ul>
     *  <li>La cantidad de rondas no est� entre 
     *      CANTIDAD_MINIMA_RONDAS y CANTIDAD_MAXIMA_RONDAS </li>
     *  <li>La cantidad de jugadores no est� entre 
     *      CANTIDAD_MINIMA_JUGADORES y CANTIDAD_MAXIMA_JUGADORES</li>
     *  <li>La cantidad m�xima de jugadores no es un m�ltiplo de la cantidad de rondas</li>
     * </ul>
     */
    public POJOCreacionPartida(int cantidadRondas, int cantidadMaximaJugadores) throws ParametrosNoValidosException {
        List<String> errores = new ArrayList<String>();
        if (cantidadRondas < CANTIDAD_MINIMA_RONDAS) {
            errores.add("La cantidad de rondas es inferior a la m�nima "
                      + "(" + CANTIDAD_MINIMA_RONDAS + ")");
        }
        if (cantidadRondas > CANTIDAD_MAXIMA_RONDAS) {
            errores.add("La cantidad de rondas es superior a la m�xima "
                      + "(" + CANTIDAD_MAXIMA_RONDAS + ")");
        }
        if (cantidadMaximaJugadores < CANTIDAD_MINIMA_JUGADORES) {
            errores.add("La cantidad de jugadores es inferior a la m�nima "
                      + "(" + CANTIDAD_MINIMA_JUGADORES + ")");
        }
        if (cantidadMaximaJugadores > CANTIDAD_MAXIMA_JUGADORES) {
            errores.add("La cantidad de jugadores es superior a la m�xima "
                      + "(" + CANTIDAD_MAXIMA_JUGADORES + ")");
        }
        if (cantidadRondas == 0 || cantidadMaximaJugadores % cantidadRondas != 0)
            errores.add("La cantidad de jugadores no es m�ltiplo de la cantidad de rondas");
        if (errores.size() > 0)
            throw new ParametrosNoValidosException("Creaci�n de partida", errores);
        this.cantidadRondas = cantidadRondas;
        this.cantidadMaximaJugadores = cantidadMaximaJugadores;
    }

    /**
     * @return la cantidad de rondas para los que la partida se juega.
     */
    public int getCantidadRondas() {
        return cantidadRondas;
    }

    /**
     * @return la cantidad m�xima de jugadores definida por el usuario.
     */
    public int getCantidadMaximaJugadores() {
        return cantidadMaximaJugadores;
    }

}
