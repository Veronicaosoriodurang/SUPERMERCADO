package com.co.programacion.ejemplosprintboot1.controllers;

import com.co.programacion.ejemplosprintboot1.entities.Persona;
import com.co.programacion.ejemplosprintboot1.services.PersonaService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    //CRUDL Create, Read, Update, Delete, List. tambien agregamos Search by apellidos

    //inyección de dependencias
    @Autowired
    private PersonaService service;

    @GetMapping("/ping")
    public String ping(){
        return "pong, desde personas";
    }

    @GetMapping("/todos")
    public List<Persona> getPersonas(){
        return service.getPersonas();
    }

    @GetMapping("/buscar/{apellidos}")
    public ResponseEntity<List<Persona>> getPersonasByApellidos(@PathVariable String apellidos){
       return  new ResponseEntity<List<Persona>>(service.getPersonasByApellidos(apellidos), HttpStatus.OK);
      //  return ResponseEntity.ok(service.getPersonasByApellidos(apellidos));
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<Object> getPersonasByApellidos(@PathVariable int personaId){
        Persona persona = service.getPersonaById(personaId);
        if(Objects.isNull(persona)){
            return new ResponseEntity<>("No se encontró la persona con id: " + personaId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

  @PutMapping("/actualizar")
  public ResponseEntity<String> actualizarPersona(@RequestBody Persona persona) {

    if (service.actualizarPersona(persona)) {
      return new ResponseEntity<>("Persona actualizada correctamente", HttpStatus.OK);
    }

    return new ResponseEntity<>(
          "No se pudo actualizar la persona", HttpStatus.NO_CONTENT);

  }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> actualizarPersona(@RequestParam int personaId) {

        if (service.eliminarPersona(personaId)) {
            return new ResponseEntity<>("Persona eliminada correctamente", HttpStatus.OK);
        }

        return new ResponseEntity<>(
                "No se pudo eliminar la persona", HttpStatus.NO_CONTENT);

    }

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarPersona(@RequestBody Persona persona) {

        if (service.insertarPersona(persona)) {
            return new ResponseEntity<>("Persona agregada correctamente", HttpStatus.OK);
        }

        return new ResponseEntity<>(
                "No se pudo agregar a la persona", HttpStatus.NO_CONTENT);

    }

}
