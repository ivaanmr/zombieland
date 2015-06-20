package com.rzg.zombieland.server.juego;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.controlador.AbstractPartidasTest;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * @author Manuel
 */

public class TableroTest extends AbstractPartidasTest {

    @Override
    protected Jugador crearJugador() throws ParametrosNoValidosException {
        Jugador jugador = super.crearJugador();
        Sesion sesion = new Sesion(jugador, new EnviaPeticionesImpl());
        ServicioSesion.getInstancia().addSesion(sesion);
        return jugador;
    }
    
	/**
	 * Verifica que al crearse el tablero la posici�n inicial del zombi sea el
	 * centro de �ste.
	 * @throws ParametrosNoValidosException 
	 */
	@Test
    public void testPosicionZombie() throws ParametrosNoValidosException {
        for (int k = 0; k < 1000; k++) {
            java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
            jugadores.add(crearJugador());
            jugadores.add(crearJugador());
            Jugador zombi = crearJugador();
            Tablero tablero = new Tablero(10, jugadores, zombi);
            Assert.assertEquals(((Zombie) tablero.getEntidadEn(new Coordenada(5, 5))).getJugador(),
                                zombi);
        }
    }

	/**
	 * Verifica en n iteraciones si el zombi qued� encerrado por obst�culos
	 * debido a la distribuci�n aleatoria de los mismos. NOTA: S�lo testea que
	 * no pueda moverse hacia ningun lado desde su posici�n.
	 * @throws ParametrosNoValidosException 
	 */
	public boolean jugadoresEncerrados() throws ParametrosNoValidosException {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador zombi = crearJugador();
		for (int i = 0; i < 100000; i++) {
			Tablero tablero = new Tablero(10, jugadores, zombi);
			// El truco est� en verificar las cuatro puntas, ya que los
			// movimientos
			// no pueden ser diagonales. Pruebo con el zombi porque est� en el
			// medio
			// y la probabilidad es la misma. Si pasa este test, asumo que pasan
			// todos.
			Personaje personaje = ServicioSesion.getInstancia().getSesion(zombi).getPersonaje();
			Coordenada posicion = personaje.getPosicion();
			if (tablero.getEntidadEn(new Coordenada(posicion.getX() + 1,
					posicion.getY())) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX(),
							posicion.getY() + 1)) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX() - 1,
							posicion.getY())) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX(),
							posicion.getY() + 1)) != null) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testJugadoresEncerrados() throws ParametrosNoValidosException {
		Assert.assertEquals(false, jugadoresEncerrados());
	}

	/**
	 * Devuelve la cantidad de personajes de un tablero.
	 * 
	 * @param t
	 *            El tablero en cuesti�n
	 */
	public int cantidadPersonajes(Tablero t) {
		int acum = 0;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (t.getEntidadEn(new Coordenada(i, j)) != null
						&& t.getEntidadEn(new Coordenada(i, j)).esPersonaje())
					acum++;
		return acum;
	}

	/**
	 * Testea que la cantidad de jugadores del tablero sea la misma que la
	 * cantidad de jugadores en el juego.
	 * @throws ParametrosNoValidosException 
	 */
	@Test
	public void testCantidadDeJugadores() throws ParametrosNoValidosException {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(crearJugador());
		jugadores.add(crearJugador());
		jugadores.add(crearJugador());
		Jugador zombi = crearJugador();
		Tablero tablero = new Tablero(10, jugadores, zombi);
		Assert.assertEquals(4, cantidadPersonajes(tablero));
	}

	/**
	 * Testea que los movimientos se realicen de forma correcta.
	 * @throws ParametrosNoValidosException 
	 */
	@Test
	public void testMovimiento() throws ParametrosNoValidosException {
	    for (int k = 0; k < 1000; k++) {
    		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
    		Jugador zombi = crearJugador();
    		// Generamos el tablero de prueba para mover al zombie en �l
    		// Sabemos que el zombie arranca en la posici�n (5,5)
    		Tablero tablero = new Tablero(10, jugadores, zombi);
    		Coordenada desde = new Coordenada(5, 5);
    		Coordenada hasta;
    		// Elijo una de las posiciones de los costados. Si una de ellas est�
    		// ocupada
    		// uso la que est� en su diagonal, que debido al algoritmo de colocaci�n
    		// de obst�culos debe estar vac�a
    		if (tablero.getEntidadEn(new Coordenada(5, 6)) == null)
    			hasta = new Coordenada(5, 6);
    		else
    			hasta = new Coordenada(6, 5);
    		tablero.moverEntidad(desde, hasta);
    		// Verificamos que en la posici�n 'hasta' est� el zombie en cuesti�n.
    		Personaje personaje = ServicioSesion.getInstancia().getSesion(zombi).getPersonaje();
    		Assert.assertEquals(personaje, tablero.getEntidadEn(hasta));
	    }
	}

	/**
	 * Testea que no se produzcan movimientos que generen una superposici�n de
	 * elementos Si hay un obst�culo, que no se pueda mover ah�.
	 * @throws ParametrosNoValidosException 
	 */
	@Test
	public void testColision() throws ParametrosNoValidosException {
	    for (int k = 0; k < 1000; k++) {
    		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
    		Jugador zombi = crearJugador();
    		// Generamos el tablero de prueba para mover al zombie en �l
    		// Sabemos que el zombie arranca en la posici�n (5,5)
    		Tablero tablero = new Tablero(10, jugadores, zombi);
    		Coordenada desde = new Coordenada(5, 5);
    		Coordenada hasta = new Coordenada(0, 0);
    		// Voy a recorrer el tablero
    		// hasta encontrar un obst�culo.
    		int i = 0;
    		int j = 0;
    		while (tablero.getEntidadEn(hasta) == null) {
    			j++;
    			if (j == 10) {
    				j = 0;
    				i++;
    			}
    			hasta = new Coordenada(i, j);
    		}
    		// Salgo del while con una coordenada de un obstaculo.
    		// Ahora intento moverme ah�.
    		tablero.moverEntidad(desde, hasta);
    		// Comparo al personaje con la entidad que hay en la posici�n donde
    		// deber�a
    		// estar, que es la misma de antes ya que no debi� moverse.
    		Personaje personaje = ServicioSesion.getInstancia().getSesion(zombi).getPersonaje();
    		Assert.assertEquals(personaje, tablero.getEntidadEn(desde));
	    }
	}

	/**
	 * Verifica si al chocar un humano con un zombi, el humano se transforma y
	 * ambos quedan en la misma posici�n.
	 * @throws ParametrosNoValidosException 
	 */
	@Test
	public void testColisionZombieHumano() throws ParametrosNoValidosException {
	    for (int k = 0; k < 1000; k++) {
    		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
    		Jugador humano = crearJugador();
    		jugadores.add(humano);
    		Jugador zombi = crearJugador();
    		Tablero tablero = new Tablero(10, jugadores, zombi);
    		// Voy a intentar mover al zombi desde su posici�n a la posici�n del
    		// humano
    		// Como el tablero se genera al azar, necesito encontrar al humano
    		// primero.
    		Coordenada desde = new Coordenada(5, 5); // Posicion del zombie.
    		// Va a recorrer toda la matriz, cuando encuentre un personaje cuya
    		// posici�n
    		// No sea el 5,5, va a encontrar al humano.
    		Coordenada hasta = getCoordenadaHumano(tablero);
    		tablero.moverEntidad(desde, hasta);
    		Assert.assertEquals(Zombie.class, tablero.getEntidadEn(hasta)
    				.getClass());
	    }
	}

	/**
	 * Ahora testea que un humano se lleve puesto un zombie, que el humano se
	 * transforme y ambos mantengan la posici�n.
	 * @throws ParametrosNoValidosException 
	 */
	@Test
	public void testColisionHumanoZombie() throws ParametrosNoValidosException {
	    for (int k = 0; k < 1000; k++) {
    		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
    		Jugador humano = crearJugador();
    		jugadores.add(humano);
    		Jugador zombi = crearJugador();
            Tablero tablero = new Tablero(10, jugadores, zombi);
            // Voy a intentar mover al humano a donde est� el zombie.
            // Como el tablero se genera al azar, necesito encontrar al humano
            // primero.
            Coordenada hasta = new Coordenada(5, 5); // Posicion del zombie.
            // Va a recorrer toda la matriz, cuando encuentre un personaje cuya
            // posici�n
            // No sea el 5,5, va a encontrar al humano.
            Coordenada desde = getCoordenadaHumano(tablero);
            tablero.moverEntidad(desde, hasta);
            Assert.assertEquals(Zombie.class, tablero.getEntidadEn(desde).getClass());
	    }
	}

    private Coordenada getCoordenadaHumano(Tablero tablero) {
        Coordenada desde = null;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                EntidadTablero entidad = tablero.getEntidadEn(new Coordenada(i, j));
                if (!(i == 5 && j == 5) & entidad != null && entidad.esPersonaje()) {
                    desde = new Coordenada(i, j);
                }
            }
        }
        return desde;
    }
}
