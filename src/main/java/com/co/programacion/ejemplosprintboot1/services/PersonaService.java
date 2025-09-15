package com.co.programacion.ejemplosprintboot1.services;

import com.co.programacion.ejemplosprintboot1.entities.Persona;
import com.co.programacion.ejemplosprintboot1.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    //inyección de dependencias
    private final PersonaRepository repository;

    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    public List<Persona> getPersonas(){
        return repository.getPersonas();
    }

    public List<Persona> getPersonasByApellidos( String apellidos){
        return repository.getPersonasByApellidos(apellidos);
    }

    public Persona getPersonaById(Integer idPersona){
        return repository.getPersonaById(idPersona);
    }

    public boolean actualizarPersona(Persona persona){
        return repository.actualizarPersona(persona);
    }

    public boolean eliminarPersona(int personaId){
        return repository.eliminarPersona(personaId);
    }

    public boolean insertarPersona(Persona persona){
        return repository.insertarPersona(persona);
    }

}
