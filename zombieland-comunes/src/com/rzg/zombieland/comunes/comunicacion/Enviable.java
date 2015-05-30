package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define un componente que puede enviarse a trav�s de un socket. 
 * @author nicolas
 *
 */
public abstract class Enviable {
    /**
     * Crea un objeto enviable a partir de los bytes dados.
     * @return
     */
    public Enviable(Byte[] bytes) { }
    
    /**
     * Convierte el objeto en bytes para su env�o.
     * @return una cadena de bytes para su env�o.
     */
    public abstract Byte[] serializar();
}
