package com.rzg.zombieland.server.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero.POJOEntidad;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * El tablero de juego, que contiene obst�culos, humanos y zombies.
 * 
 * @author Nicolas, Manuel
 *
 */
public class Tablero {
	// El tablero en s�, con todos sus actores. Incluidos obst�culos.
	private EntidadTablero[][] matriz;

	// Personajes que participan del juego - Humanos solamente.
	private List<Personaje> personajes;

	// Posici�n en el listado de personajes del que se mover� primero.
	private int primerPersonaje;

	// Personaje de la ronda que arranca como zombi.
	private Personaje zombi;

	/**
	 * Constructor por defecto. Aqu� se generar�n los obst�culos en forma
	 * 'aleatoria'
	 */
	public Tablero(int casilleros, List<Personaje> personajes, Personaje zombi) {
		Random rnd = new Random(); // Que quede claro que va a ser una cuesti�n
									// de suerte
		boolean resuelto = false; // Flag que me indica si ya posicione o no a
									// la entidad.

		Coordenada c;

		matriz = new EntidadTablero[casilleros][casilleros];
		// Ponemos al zombi
		zombi.setPosicion(new Coordenada(casilleros / 2, casilleros / 2)); 
		// Siempre arranca en el medio.
		matriz[casilleros / 2][casilleros / 2] = zombi; // Lo ponemos en la
														// matriz.

		// Ponemos los obstaculos. Si la matriz es de 10x10, son 100 casilleros.
		// Con 30 obst�culos estariamos bien -- Ser�a el 30%
		// NOTA - Es muy aleatorio, si vemos que no sale bien, probamos otras
		// cosas.
		for (int i = 0; i < Math.pow(casilleros, 2) * 0.3; i++) {
			resuelto = false;
			while (!resuelto) {
				c = new Coordenada(Math.abs(rnd.nextInt()) % casilleros, Math.abs(rnd.nextInt())
						% casilleros);
				if (matriz[c.getX()][c.getY()] == null
					/*&& matriz[c.getX() + 1][c.getY()] == null
					&& matriz[c.getX() - 1][c.getY()] == null
					&& matriz[c.getX()][c.getY() + 1] == null
					&& matriz[c.getX()][c.getY() - 1] == null
					&& matriz[c.getX() + 1][c.getY() - 1] == null
					&& matriz[c.getX() + 1][c.getY() + 1] == null
					&& matriz[c.getX() - 1][c.getY() - 1] == null
					&& matriz[c.getX() - 1][c.getY() + 1] == null*/) {
					// Basicamente, inserta un obstaculo si a la redonda no hay nada. 
					// Esto evita tener obstaculos adyacentes y evita encerrar al zombi.
					matriz[c.getX()][c.getY()] = new Obstaculo(c);
					resuelto = true;
				}
			}
		}

		// Ponemos a los humanos
		for (Personaje personaje : personajes) {
			resuelto = false;
			while (!resuelto) {
				c = new Coordenada(Math.abs(rnd.nextInt()) % casilleros, Math.abs(rnd.nextInt())
						% casilleros);
				if (matriz[c.getX()][c.getY()] == null) {
					matriz[c.getX()][c.getY()] = personaje;
					personaje.setPosicion(c);
					resuelto = true;
				}
			}

		}

	}

	/**
	 * @param superiorIzquierda
	 * @param inferiorDerecha
	 * @return la proyecci�n del tablero entre las dos esquinas dadas.
	 */
	public ProyeccionTablero getProyeccion(Coordenada superiorIzquierda,
			Coordenada inferiorDerecha) {
		List<POJOEntidad> entidades = new ArrayList<POJOEntidad>();
		// Recorro mi matriz de entidades en los limites indicados por el metodo.
		for(int i = superiorIzquierda.getX(); i < inferiorDerecha.getX(); i++) {
			for(int j = superiorIzquierda.getY(); j < inferiorDerecha.getY(); i++) {
				if(matriz[i][j] != null) {
					// Agrego las entidades que encuentre a la lista de la proyeccion
					entidades.add
						(new POJOEntidad("Elemento" + i + j,
										 new Coordenada(i,j),  //Cada entidad ya tiene su posicion
										 Avatar.HOMBRE)); // Ac� iria el avatar correspondiente.
				}
			}
		}
		// Devuelvo la proyecci�n. Chiche bomb�n.
		return new ProyeccionTablero(matriz.length,superiorIzquierda,inferiorDerecha,entidades);
	}

	/**
	 * Obtiene la entidad por coordenada.
	 * 
	 * @param coordenada
	 * @return la entidad en la coordenada dada, o null si no hay ninguna.
	 */
	public EntidadTablero getEntidadEn(Coordenada coordenada) {
		return matriz[coordenada.getX()][coordenada.getY()];
	}

	/**
	 * Mueve una entidad.
	 * 
	 * @param desde
	 *            - coordenada donde la entidad original se encuentra.
	 * @param hasta
	 *            - coordenada de destino. Debe estar vac�a.
	 */
	public void moverEntidad(Coordenada desde, Coordenada hasta) {
		// TODO implementar.
	}
}
