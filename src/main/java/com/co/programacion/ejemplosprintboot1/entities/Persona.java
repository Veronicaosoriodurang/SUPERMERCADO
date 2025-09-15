package com.co.programacion.ejemplosprintboot1.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona implements Comparable<Persona> {
    private Integer idPersona;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String genero;
    private String cedula;
    private String celular;
    private String email;
    private int edad;
    private String direccion;

    @Override
    public int compareTo(Persona o) {
        return Integer.compare(this.edad, o.edad);
    }

}
