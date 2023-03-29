package com.prueba.authuseradminds.controllers;

/**
 * Clase base de Controlador que implementa la interface de servicio
 * @author David M.
 * @version 2023-03-27
 */
public class DataServiceController implements DataServiceInterface {
    // Definición del objeto de respuesta
    DataServiceResponse dataServiceResponse = null;
    // Definición del nombre del servicio
    static final String serviceNameV1 = serviceBase + serviceVersion1;
}