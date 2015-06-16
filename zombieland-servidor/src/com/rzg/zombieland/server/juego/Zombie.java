package com.rzg.zombieland.server.juego;

/**
 * Personaje cuyo �nico deseo en el mundo es comer cerebros. RAWR!
 * 
 * @author nicolas
 *
 */
public class Zombie extends Personaje {

	// TODO definir sprite.
	private final String SPRITE = "zombie.jpg";

	// Usuario que identifica al zombie. Puede servir m�s adelante para colocar
	// el nombre por encima.
	private String usuario;

	// Permite construir un zombie a trav�s de un Jugador.
	public Zombie(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String getSprite() {
		return SPRITE;
	}

	@Override
	public void colisionar(EntidadTablero entidad) {
		// Si hay una colision llamada por un zombi que recibi� por par�metro un
		// humano
		// Hay cosas que hacer.
		if (entidad.esPersonaje()) {
			if (entidad.getClass() == Humano.class) {
				// Cambio al humano por un nuevo zombie.
				entidad = new Zombie(((Humano) entidad).getUsuario());
			}
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public boolean esPersonaje() {
		return true;
	}

}
