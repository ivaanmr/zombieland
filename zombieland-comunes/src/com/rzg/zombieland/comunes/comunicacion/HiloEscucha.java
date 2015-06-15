package com.rzg.zombieland.comunes.comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorFactory;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Clase que se ocupa de la comunicaci�n con un cliente en particular.
 * Una vez que se arranca el hilo, se escucha constantemente. Enviar una petici�n desde otro hilo
 * es thread-safe (bueno, supuestamente). Ver comentarios en implementaci�n para conocer detalles
 * del protocolo.
 * @author nicolas
 *
 */
public class HiloEscucha extends Thread implements EnviaPeticiones {

    // El socket sobre el que se escucha al cliente.
    private Socket socket;
    
    // Indica si el hilo se est� ejecutando.
    private boolean corriendo;
    
    // F�brica de controladores.
    private ControladorFactory controladorFactory;
    
    // Mapea las peticiones para su respuesta.
    private Map<UUID, Peticion<?, ?>> mapaPeticiones;

    // Objeto al que se le avisa cuando ocurren distintos eventos en el hilo de escucha.
    private HiloListener listener;
    
    /**
     * Comienza a escuchar en el socket dado, delegando las peticiones a la f�brica de
     * controladores. El socket ya debe estar abierto.
     * @param socket - el socket con el que se escuchar� al cliente.
     * @param controladorFactory - f�brica de controladores inyectada.
     */
    public HiloEscucha(Socket socket, ControladorFactory controladorFactory, HiloListener listener) {
        super("HiloEscucha: " + socket.getInetAddress());
        corriendo = true;
        Log.debug("Estableciendo nueva conexi�n con " + socket.getInetAddress());
        this.socket = socket;
        this.controladorFactory = controladorFactory;
        mapaPeticiones = new HashMap<UUID, Peticion<?, ?>>();
        this.listener = listener;
    }
    
    @Override
    public void run() {
        // Los dos warnings siguiente NO son de verdad, son un bug de Eclipse:
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=371614
        try (@SuppressWarnings("resource")
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             @SuppressWarnings("resource")
             // Obtengo un reader de entrada.
             BufferedReader in = new BufferedReader(new InputStreamReader(
                 socket.getInputStream()))) {
            while (corriendo) {
                // El hilo se detendr� ac� hasta que el otro extremo env�e un c�digo de petici�n. 
                int codigo = in.read();
                Log.debug("Recibiendo datos en HiloEscucha " + this + ". C�digo:");
                Log.debug(codigo);
                
                // -1 indica que el otro extremo cerr� la conexi�n.
                if (codigo == -1) {
                    Log.debug("Cerrando hilo escucha: lleg� el -1");
                    cerrar(true);
                    return;
                }
                // Este bloque es sincronizado para que si otro hilo intenta enviar una
                // respuesta sobre esta instancia de escucha mientras nosotros estamos
                // escribiendo en el socket, los mensajes no se intercalen y rompan el
                // protocolo.                
                try {
                    synchronized (this) {
                        // Si el servidor env�a una respuesta, resolvemos la petici�n que ten�amos
                        // relacionada. Primero buscamos la buscamos en el mapa de peticiones de
                        // acuerdo al ID que nos envi� el otro extremo, luego la quitamos y le enviamos
                        // la respuesta para que la procese.
                        if (codigo == Enviable.RESPUESTA) {
                            mapaPeticiones.remove(UUID.fromString(in.readLine())).
                                procesarRespuesta(in.readLine());
                            continue;
                        }
                        // Si llegamos ac�, la petici�n no viene de una respuesta. Obtenemos el ID
                        // generado por el cliente de la petici�n para que la pueda identificar.
                        String uuid = in.readLine();
                        
                        // En esta secci�n se puede ver el protocolo de env�o de respuestas.
                        // Escribimos el c�digo de las respuestas para que el cliente pueda
                        // identificar el mensaje apropiadamente.
                        out.write(Enviable.RESPUESTA);
                        // Escribimos el mismo UUID que nos dio el otro extremo.
                        out.println(uuid);
                        // Obtengo un controlador que es quien tomar� las acciones apropiadas para
                        // la petici�n, como mover a un personaje o registrar un jugador.
                        Controlador controlador = controladorFactory.crear(codigo);
                        Log.debug("Contenido:");
                        // La pr�xima l�nea es el cuerpo de la petici�n. Su formato var�a de 
                        // acuerdo al tipo de petici�n y lo sabe manejar el controlador.
                        String contenido = in.readLine();
                        Log.debug(contenido);
                        // Obtenemos la respuesta a partir del controlador.
                        String respuesta = controlador.procesar(contenido);
                        Log.debug("Respuesta:");
                        Log.debug(respuesta);
                        // Escribimos la respuesta en el b�fer de salida...
                        out.println(respuesta);
                        // ...y nos aseguramos de que se limpie de una vez para evitar deadlocks
                        // en los tests.
                        out.flush();
                    }
                } catch (ComandoDesconocidoException e) {
                    // Esto no deber�a suceder jam�s de los jamases, a menos que se corte la
                    // conexi�n en el medio del env�o del c�digo de comando... en cualquier caso,
                    // debemos poder manejar el error.
                    synchronized (this) {
                        Log.error("Se envi� un comando desconocido: " + e.getMessage());
                        e.printStackTrace();
                        out.println(Enviable.LINEA_ERROR);
                        Log.error("Matando hilo de escucha :(");
                        cerrar(true);
                    }
                }
            }
            // Ya no estamos m�s corriendo. Cerramos el socket y nos vamos a dormir.
            socket.close();
        } catch (SocketException e) {
            Log.info("Cerrando hilo de escucha " + getName() + ":");
            Log.info("Motivo: " + e.getMessage());
            // Esperada, se usa para cerrar el Thread.
            return;
        } catch (IOException e) {
            Log.error("Error en hilo de escucha " + getName() + ":");
            Log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Env�a la petici�n al otro lado de la conexi�n.
     * @param peticion
     * @throws ZombielandException 
     */
    @Override
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException {
        try {
            // Antes de enviar la petici�n, la almacenamos en un mapa identificada por un ID
            // generado aleatoriamente para poder identificar su respuesta. Es importante notar que
            // la vuelta de la petici�n desde el otro extremo se maneja en otro hilo, por lo que la
            // respuesta debe resolverse as�ncronamete mediante una promesa.
            mapaPeticiones.put(peticion.getID(), peticion);
            PrintWriter salida = new PrintWriter(socket.getOutputStream());
            Log.debug("Enviando petici�n");
            // Ver comentarios en run() sobre el synchronized. 
            synchronized (this) {
                // El protocolo de env�o de peticiones es sencillo: c�digo, ID, contenido.
                salida.write(peticion.getCodigoPeticion());
                salida.println(peticion.getID().toString());
                salida.println(peticion.getMensajePeticion());
                salida.flush();
            }
        } catch (IOException e) {
            Log.error("Error de conexi�n:");
            Log.error(e.getMessage());
            e.printStackTrace();
            throw new ZombielandException("No se pudo comunicar con el otro extremo al enviar una petici�n");
        }
    }
    /**
     * Cierra el hilo de escucha.
     * @param notificar - true si debe notificar a los escuchadores, false de lo contrario.
     */
    public void cerrar(boolean notificar) {
        corriendo = false;
        try {
            socket.close();
        } catch (IOException e) {
        }
        if (notificar && listener != null)
            listener.hiloCerrado(this);
    }

    /**
     * @return la f�brica de controladores.
     */
    public ControladorFactory getFactory() {
        return controladorFactory;
    }
}
