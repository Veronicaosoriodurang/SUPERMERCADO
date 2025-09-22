package com.co.programacion.ejemplosprintboot1.repositories;

import org.springframework.stereotype.Service;

@Service
public class PersonaRepositoryHelper {

    public String listarPersonas() {
        return "Select idPersona, nombres, apellidos, cedula, direccion from persona ";
    }

    public String getPersonaById() {
        return "Select idPersona, nombres, apellidos, cedula, direccion from persona where idPersona = ? ";
    }

    public String getPersonaByApellidos(String apellidos) {
        String stringBuilder = "Select idPersona, nombres, apellidos, cedula, direccion from persona where apellidos like '%" +
                apellidos +
                "%'";
        return stringBuilder;
    }

    public String actualizarPersona() {
        return "Update persona set nombres = ? , apellidos = ?, cedula = ? , direccion = ? where idPersona = ? ";
    }

    public String insertarPersona() {
        return "insert into persona ( nombres, apellidos, cedula, direccion) values (?, ?, ?,?) ";
    }

    public String eliminarPersona() {
        return "Delete from persona where idPersona = ?  ";
    }
}
