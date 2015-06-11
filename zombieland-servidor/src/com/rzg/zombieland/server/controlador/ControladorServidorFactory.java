package com.rzg.zombieland.server.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorFactory;
import com.rzg.zombieland.comunes.controlador.ControladorTest;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Crea controladores de acuerdo al c�digo de comando.
 * @author nicolas
 *
 */
public class ControladorServidorFactory implements ControladorFactory, ManejadorSesion {
    
    // La sesi�n del jugador.
    private Sesion sesion;
    
    /**
     * @param linea
     * @return un controlador de acuerdo a la l�nea le�da.
     * @throws ComandoDesconocidoException si el c�digo no se corresponde con ning�n controlador
     *         existente.
     */
    @Override
    public Controlador crear(int codigo) throws ComandoDesconocidoException {
        switch (codigo) {
        case Enviable.TEST:
            return new ControladorTest();
        case Enviable.REGISTRAR_JUGADOR:
            return new ControladorRegistro();
        case Enviable.INICIAR_SESION:
            return new ControladorInicioSesion(this);
        default:
            throw new ComandoDesconocidoException(
                    String.format("El c�digo 0x%X no corresponde con "
                                + "ninguno de los comandos conocidos", codigo));
        }
    }

    @Override
    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    @Override
    public Sesion getSesion() {
        return sesion;
    }
}
