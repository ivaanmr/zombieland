package com.rzg.zombieland.cliente.comunicacion;

import java.io.IOException;
import java.net.Socket;

import com.rzg.zombieland.cliente.comunicacion.controlador.ControladorClienteFactory;
import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.HiloListener;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Singleton que mantiene una conexi�n constante con el servidor.
 * @author nicolas
 *
 */
public class ServicioCliente implements HiloListener {
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
            hiloEscucha = new HiloEscucha(new Socket(host, puerto), new ControladorClienteFactory(), this);
            hiloEscucha.start();
        } catch(IOException e) {
            throw new ZombielandException("No se pudo realizar la conexi�n con el servidor: " +
                                          e.getLocalizedMessage());
        }
    }
    
    /**
     * @return el hilo de escucha.
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
     * @return la instancia de ServicioCliente cargada �ltima, o null si no se carg� ninguna.
     */
    public static ServicioCliente getInstancia() {
        return instancia;
    }
    
    /**
     * M�todo de convenienvia para enviar una petici�n al hilo de escucha que subyace al 
     * ServicioCliente.
     * @param peticion
     * @throws ZombielandException
     */
    public static void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException {
        getInstancia().getHiloEscucha().enviarPeticion(peticion);
    }

    /**
     * Cierra el servicio cliente, limpiando todos los recursos que inicializ�.
     */
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

    @Override
    public void hiloCerrado(HiloEscucha hilo) {
        Log.error("Se cerr� la conexi�n con el servidor");
    }
}
