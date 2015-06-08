package com.rzg.zombieland.cliente.comunicacion;

import java.io.IOException;
import java.net.Socket;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Singleton que mantiene una conexi�n constante con el servidor.
 * @author nicolas
 *
 */
public class ServicioCliente {
    // El hilo de escucha en s�.
    private HiloEscucha hiloEscucha;
    
    // Esta clase es un singleton.
    private static ServicioCliente instancia;
    
    /**
     * Constructor privado para uso de crearInstancia.
     * @param puerto
     * @param host
     * @throws ZombielandException
     */
    private ServicioCliente(int puerto, String host) throws ZombielandException {
        try {
            hiloEscucha = new HiloEscucha(new Socket(host, puerto), new ControladorClienteFactory());
            hiloEscucha.start();
        } catch(IOException e) {
            throw new ZombielandException("No se pudo realizar la conexi�n con el servidor: " +
                                          e.getLocalizedMessage());
        }
    }
    
    /**
     * Devuelve el hilo de escucha.
     * @return
     */
    public HiloEscucha getHiloEscucha() {
        return hiloEscucha;
    }
    
    /**
     * Inicializa una instancia de ServicioCliente.
     * @param puerto - el puerto en el que se abre la conexi�n.
     * @param host - el FQDN del servidor.
     * @throws ZombielandException si ocurre alg�n error de conexi�n.
     */
    public static void crearInstancia(int puerto, String host) throws ZombielandException {
        instancia = new ServicioCliente(puerto, host);
    }
    
    /**
     * Devuelve la instancia de ServicioCliente cargada �ltima, o null si no se carg� ninguna.
     * @return
     */
    public static ServicioCliente getInstancia() {
        return instancia;
    }

    public static void cerrar() {
        instancia.hiloEscucha.cerrar();
        try {
            instancia.hiloEscucha.join();
        } catch (InterruptedException e) {
            Log.error("Interrumpido mientras se esperaba que el hilo escucha se cerrara.");
            Log.error(e.getLocalizedMessage());
        }
        instancia = null;
    }
}