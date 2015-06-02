package com.rzg.zombieland.cliente.comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Modela una petici�n realizada al servidor.
 * 
 * @author nicolas
 *
 */
public abstract class Peticion<Respuesta> {
    // TODO parametrizar.
    
    // Host y puerto del servidor
    private final String host = "localhost";
    private final int puerto = 2048;

    // Elementos de entrada / salida que se persisten.
    // TODO extraer en otra clase para poder escuchar.
    private static Socket socket;
    private static InputStreamReader reader;
    private static PrintWriter salida;
    private static BufferedReader entrada;
    
    // Indica si se est� reintentando la conexi�n.
    private boolean reintentando;

    /**
     * Env�a la petici�n al servidor y devuelve la respuesta.
     * @return respuesta �envasada� de acuerdo a la petici�n.
     * @throws ZombielandException si ocurre un error de conexi�n.
     */
    public Respuesta enviar() throws ZombielandException {
        try {
            // Si el la primera vez que entramos, o el socket est� cerrado, lo abrimos.
            if (socket == null || socket.isClosed()) {
                Log.debug("Abriendo conexi�n con el servidor");
                socket = new Socket(host, puerto);
                salida = new PrintWriter(socket.getOutputStream());
                reader = new InputStreamReader(socket.getInputStream());
                entrada = new BufferedReader(reader);
            }
            Log.debug("Enviando datos al servidor");
            salida.write(getCodigoPeticion());
            salida.println(getMensajePeticion());
            salida.flush();
            Log.debug("Esperando respuesta");
            try {
                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    if (reintentando) {
                        Log.error("El servidor devolvi� null");
                        throw new ZombielandException("No se puede conectar con el servidor.");
                    }
                    Log.debug("Parece que el servidor cerr� la conexi�n, reintentando");
                    return reintentar();
                }
                return procesarRespuesta(respuesta);
            } catch (SocketException e) {
                if (reintentando) {
                    Log.error("Error al intentar conectar: " + e.getMessage());
                    e.printStackTrace();
                    throw new ZombielandException("No se puede conectar con el servidor.");
                }
                return reintentar();
            }
        } catch (IOException e) {
            Log.error("No se puede conectar con el servidor: ");
            Log.error(e.getMessage());
            e.printStackTrace();
            throw new ZombielandException(
                    "Error de conexi�n, por favor revise su conexi�n a internet");
        }

    }
    
    /**
     * Reintenta el env�o una vez.
     * @return
     * @throws IOException
     * @throws ZombielandException
     */
    private Respuesta reintentar() throws IOException, ZombielandException {
        socket.close();
        salida.close();
        reader.close();
        entrada.close();
        reintentando = true;
        return enviar();
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
    protected abstract Respuesta procesarRespuesta(String respuesta);
}
