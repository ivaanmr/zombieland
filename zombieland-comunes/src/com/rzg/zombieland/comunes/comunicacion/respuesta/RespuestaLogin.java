package com.rzg.zombieland.comunes.comunicacion.respuesta;

import java.security.InvalidParameterException;

/**
 * Representa la respuesta de una petici�n de inicio de sesi�n.
 * @author nicolas
 *
 */
public class RespuestaLogin {
    // Token de inicio de sesi�n. null si la petici�n no fue exitosa.
    private String token;
    
    // Mensaje de error. null si la petici�n fue exitosa.
    private String mensajeError;
    
    /**
     * Construye una respuesta exitosa.
     * @param token - el token de autenticaci�n.
     * @param mensajeError - el mensaje de error.
     * @throws InvalidParameterException si token y el mensaje de error son los dos distintos de
     *         null.
     */
    public RespuestaLogin(String token, String mensajeError) {
        if (token == null && mensajeError == null) {
            throw new InvalidParameterException("El token o el mensaje de error deben incluirse en "
                                              + "una respuesta de login");
        }
        if (token != null && mensajeError != null) {
            throw new InvalidParameterException("El token y el mensaje de error no pueden estar "
                                              + "juntos en la misma respuesta de login");
        }
        this.token = token;
        this.mensajeError = mensajeError;
    }
    
    /**
     * @return true si la petici�n fue exitosa, false de lo contrario.
     */
    public boolean fuePeticionExitosa() {
        return mensajeError == null;
    }
    
    /**
     * @return el mensaje de error, o null si no existe.
     */
    public String getMensajeError() {
        return mensajeError;
    }
    
    /**
     * @return el token de autenticaci�n, o null si no existe.
     */
    public String getToken() {
        return token;
    }
}
