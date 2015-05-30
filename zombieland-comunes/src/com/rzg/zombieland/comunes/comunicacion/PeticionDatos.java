package com.rzg.zombieland.comunes.comunicacion;


/**
 * Petici�n de datos enviada por el cliente.
 * @author nicolas
 *
 */
public class PeticionDatos extends PeticionCliente {
    /**
     * Los tipos de petici�n que el cliente puede realizar.
     * @author nicolas
     *
     */
    public enum TipoPeticion {
        LISTADO_PARTIDAS, INICIAR_SESION, REGISTRAR_USUARIO, ESTADISTICAS_JUGADOR, RANKING_GENERAL;
    }
    
    // El tipo de petici�n solicitado.
    private TipoPeticion tipo;

    public PeticionDatos(Byte[] bytes) {
        super(bytes);
        // TODO deserealizar.
    }

    @Override
    public Byte[] serializar() {
        // TODO implementar.
        return null;
    }
}
