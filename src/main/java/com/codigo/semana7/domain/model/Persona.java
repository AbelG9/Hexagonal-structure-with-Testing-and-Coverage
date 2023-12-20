package com.codigo.semana7.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;

    public Persona(Long id, String nombre, String apellido, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
}
