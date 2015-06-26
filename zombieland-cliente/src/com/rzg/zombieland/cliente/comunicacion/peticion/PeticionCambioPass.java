package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCambioPass;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Peticion para realizar un cambio en la contrase�a de un usuario que la olvid�.
 * @author Nicolas L
 *
 */
public class PeticionCambioPass extends Peticion<POJOCambioPass, RespuestaGenerica>{

	  /**
     * Construye una petici�n para modificar la pass de un 
     * Usuario.
     * @param pojo
     */
    public PeticionCambioPass(POJOCambioPass pojo) {
        super(pojo, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.CAMBIOS_CONTRASE�A;
    }


}
