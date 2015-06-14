package com.rzg.zombieland.comunes.comunicacion;

import java.util.UUID;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import com.google.gson.Gson;

/**
 * Modela una petici�n realizada al servidor.
 * 
 * @author nicolas
 * @param <ClaseEnviable> el tipo de objeto que se env�a al otro extremo.
 * @param <Respuesta> el tipo de objeto respuesta de la petici�n.
 *
 */
public abstract class Peticion<ClaseEnviable, Respuesta> {
    // Un ID �nico para identificar a la petici�n.
    private UUID id;
    
    // Promesa que se llena con la respuesta.
    private Deferred<Respuesta, Object, Respuesta> promesa;
    
    // El objeto que se env�a al servidor.
    private ClaseEnviable enviable;
    
    // La clase que forma el objeto respuesta de esta petici�n.
    private Class<Respuesta> claseRespuesta;
     
    /**
     * Construye la petici�n asing�ndole un ID aleatorio y creando la promsa.
     * @param enviable - el objeto que se env�a al servidor.
     * @param claseRespuesta - la clase que se responde.
     */
    public Peticion(ClaseEnviable enviable, Class<Respuesta> claseRespuesta) {
        if (claseRespuesta == null)
            throw new NullPointerException("La clase de la respuesta no puede ser null");
        this.claseRespuesta = claseRespuesta;
        this.id = UUID.randomUUID();
        promesa = new DeferredObject<Respuesta, Object, Respuesta>();
        this.enviable = enviable;
    }
    /**
     * @return el mensaje de una petici�n.
     */
    public String getMensajePeticion() {
        return new Gson().toJson(enviable);
    }

    /**
     * @return el c�digo de la petici�n.
     */
    protected abstract int getCodigoPeticion();

    /**
     * Procesa una respuesta. 
     * @param respuesta
     * @return el resultado de procesar la respuesta.
     */
    private Respuesta generarRespuesta(String respuesta) {
        return new Gson().fromJson(respuesta, claseRespuesta);
    }
    
    /**
     * @return un ID �nico de petici�n.
     */
    public final UUID getID() {
        return id;
    }
    
    /**
     * @return la promesa de la respuesta.
     */
    public final Promise<Respuesta, Object, Respuesta> getRespuesta() {
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
