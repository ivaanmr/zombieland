package com.rzg.zombieland.server.juego;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * @author Manuel
 */

public class TableroTest {

	/**
	 * Verifica que al crearse el tablero la posici�n inicial del zombi sea el
	 * centro de �ste.
	 */
	@Test
	public void posicionZombieTest() {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("Humano1"));
		jugadores.add(new Jugador("Humano2"));
		Jugador zombi = new Jugador("Zombi1");
		Personaje zombie = new Zombie(zombi.getNombre());
		Tablero tablero = new Tablero(10, jugadores, zombie);
		Assert.assertEquals(tablero.getEntidadEn(new Coordenada(5, 5)), zombie);
	}

	/**
	 * Verifica en n iteraciones si el zombi qued� encerrado por obst�culos
	 * debido a la distribuci�n aleatoria de los mismos.
	 */
	public int jugadoresEncerrados() {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador zombi = new Jugador("Zombi1");
		Personaje zombie = new Zombie(zombi.getNombre());
		for (int i = 0; i < 100000; i++) {
			Tablero tablero = new Tablero(10, jugadores, zombie);
			// El truco est� en verificar las cuatro puntas, ya que los
			// movimientos
			// no pueden ser diagonales. Pruebo con el zombi porque est� en el
			// medio
			// y la probabilidad es la misma. Si pasa este test, asumo que pasan
			// todos.
			Coordenada posicion = zombie.getPosicion();
			if (tablero.getEntidadEn(new Coordenada(posicion.getX() + 1,
					posicion.getY())) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX(),
							posicion.getY() + 1)) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX() - 1,
							posicion.getY())) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX(),
							posicion.getY() + 1)) != null) {
				return 1;
			}
		}
		return 0;
	}

	@Test
	public void jugadoresEncerradosTest() {
		Assert.assertEquals(0, jugadoresEncerrados());
	}
	// TODO testear que la cantidad de personajes sea igual a la cantidad de
	// jugadores.
	// TODO testear que las coordenadas de los personajes coincidan con la
	// posicion de la matriz.
}
