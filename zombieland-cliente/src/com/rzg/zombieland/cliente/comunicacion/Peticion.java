package com.rzg.zombieland.cliente.comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Modela una petici�n realizada al servidor.
 * @author nicolas
 *
 */
public abstract class Peticion<Respuesta> {
    // TODO parametrizar.
    private final String host = "localhost";
    private final int puerto = 2048;
    
    public Respuesta enviar() throws ZombielandException {
        try (Socket socket = new Socket(host, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream());
             InputStreamReader reader = new InputStreamReader(socket.getInputStream());
             BufferedReader entrada = new BufferedReader(reader)) {
            salida.write(getCodigoPeticion());
            salida.println(getMensajePeticion());
            salida.flush();
            return procesarRespuesta(entrada.readLine());
        } catch (IOException e) {
            Log.error("No se puede realizar la petici�n: ");
            Log.error(e.getMessage());
            e.printStackTrace();
            throw new ZombielandException("Error de conexi�n, por favor revise su conexi�n a internet");
        }
    }

    /**
     * Devuelve el mensaje de una petici�n.
     * @return
     */
    protected abstract String getMensajePeticion();

    /**
     * Devuelve el c�digo de la petici�n.
     * @return
     */
    protected abstract int getCodigoPeticion();
    
    /**
     * Procesa una respuesta y devuelve un producto para su consumo.
     * @param respuesta
     * @return
     */
    protected abstract Respuesta procesarRespuesta(String respuesta);
}
