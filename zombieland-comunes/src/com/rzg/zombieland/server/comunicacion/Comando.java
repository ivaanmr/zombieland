package com.rzg.zombieland.server.comunicacion;

import com.rzg.zombieland.server.misc.Movimiento;


/**
 * Orden de movimiento enviada por un cliente al servidor.
 * @author nicolas
 *
 */
public class Comando extends PeticionCliente {
    public Comando(Byte[] bytes) {
        super(bytes);
        // TODO deserealizar.
    }

    // Indica el movimiento que el jugador realiz�.
    private Movimiento movimiento;
    
    @Override
    public Byte[] serializar() {
        // TODO implementar.
        return null;
    }
}
