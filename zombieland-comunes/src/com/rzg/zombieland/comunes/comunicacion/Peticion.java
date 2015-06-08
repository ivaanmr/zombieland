package com.rzg.zombieland.comunes.comunicacion;

import java.util.UUID;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

/**
 * Modela una petici�n realizada al servidor.
 * 
 * @author nicolas
 *
 */
public abstract class Peticion<Respuesta> {
    // Un ID �nico para identificar a la petici�n.
    private UUID id;
    
    // Promesa que se llena con la respuesta.
    private Deferred promesa;
     
    public Peticion() {
        this.id = UUID.randomUUID();
        promesa = new DeferredObject();
    }
    /**
     * Devuelve el mensaje de una petici�n.
     * 
     * @return
     */
    protected abstract String getMensajePeticion();

    /**
     * Devuelve el c�digo de la petici�n.
     * 
     * @return
     */
    protected abstract int getCodigoPeticion();

    /**
     * Procesa una respuesta y devuelve un producto para su consumo.
     * 
     * @param respuesta
     * @return
     */
    protected abstract Respuesta generarRespuesta(String respuesta);
    
    /**
     * Devuelve un ID �nico de petici�n.
     */
    public UUID getID() {
        return id;
    }
    
    /**
     * Devuelve la promesa de la respuesta.
     * @return
     */
    public Promise getRespuesta() {
        return promesa.promise();
    }
    
    /**
     * Completa la promesa con la respuesta dada.
     * @param respuesta
     */
    public void procesarRespuesta(String respuesta) {
        promesa.resolve(generarRespuesta(respuesta));
    }
}
