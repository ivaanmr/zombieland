package com.rzg.zombieland.comunes.misc;

/**
 * Define una interfaz que recibe notificaciones de log.
 * @author nicolas
 *
 */
public interface EscuchaLog {
	/**
	 * Notifica de un log nuevo.
	 * @param mensaje - el mensaje en s� de log.
	 * @param nivel - el nivel de log, seg�n constantes en clase Log.
	 */
	public void onLog(String mensaje, int nivel);
}
