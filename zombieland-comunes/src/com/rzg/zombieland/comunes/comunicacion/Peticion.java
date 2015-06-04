package com.rzg.zombieland.comunes.comunicacion;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
    private CompletableFuture<Respuesta> promesa;
    
    public Peticion() {
        this.id = UUID.randomUUID();
        promesa = new CompletableFuture<Respuesta>();
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
    public CompletableFuture<Respuesta> getRespuesta() {
        return promesa;
    }
    
    /**
     * Completa la promesa con la respuesta dada.
     * @param respuesta
     */
    public void procesarRespuesta(String respuesta) {
        promesa.complete(generarRespuesta(respuesta));
    }
}
