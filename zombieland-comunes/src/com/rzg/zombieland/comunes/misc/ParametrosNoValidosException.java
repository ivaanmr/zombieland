package com.rzg.zombieland.comunes.misc;

import java.util.List;

/**
 * Excepci�n con un mensaje amigable para el usuario referida a la no validez de par�metros.
 * Deber�a ser lanzada desde cualquier constructor que reciba par�metros dados por el usuario.
 * @author nicolas
 *
 */
public class ParametrosNoValidosException extends ZombielandException {
    private static final long serialVersionUID = 2748734264163995657L;

    // Listado de par�metros no v�lidos.
    private List<String> parametros;
    
    // Nombre amigable para el usuario del objeto que se valida. 
    private String nombreObjeto;
    
    /**
     * Construye una excepci�n de par�metros no v�lidos.
     * @param nombreObjeto - nombre amigable para el usuario del objeto cuyos par�metros no son
     *                       v�lidos.
     * @param parametros - listado de errores de par�metros no v�lidos del objeto. Por ejemplo,
     *                     "El nombre no puede ser vac�o."
     */
    public ParametrosNoValidosException(String nombreObjeto, List<String> parametros) {
        super("Par�metros no v�lidos para " + nombreObjeto);
        this.nombreObjeto = nombreObjeto;
        this.parametros = parametros;
        Log.info(getMensaje());
    }
    
    /**
     * @return un mensaje amigable para el usuario del error.
     */
    public String getMensaje() {
        StringBuilder mensaje = new StringBuilder("Par�metros no v�lidos para ");
        mensaje.append(nombreObjeto);
        mensaje.append("\n");
        for (String parametro : parametros) {
            mensaje.append(parametro);
            mensaje.append("\n");
        }
        return mensaje.toString();
    }
    
    /**
     * @return la cantidad de par�metros no v�lidos. 
     */
    public int getCantidadParametros() {
        return parametros.size();
    }
}
