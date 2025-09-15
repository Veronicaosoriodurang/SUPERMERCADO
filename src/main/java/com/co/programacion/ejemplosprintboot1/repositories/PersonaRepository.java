package com.co.programacion.ejemplosprintboot1.repositories;

import com.co.programacion.ejemplosprintboot1.entities.Persona;
import com.co.programacion.ejemplosprintboot1.utilities.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository {
  @Autowired private PersonaRepositoryHelper personaRepositoryHelper;

  // seguimos el patrón DAO data access object
  public List<Persona> getPersonas() {
    List<Persona> result = new ArrayList<>();
    Conexion conexion = new Conexion();
    Connection connection = conexion.obtenerConexion();
    try {

      PreparedStatement preparedStatement =
          connection.prepareStatement(personaRepositoryHelper.listarPersonas());

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {

        Persona persona = new Persona();
        persona.setIdPersona(resultSet.getInt("idPersona"));
        persona.setNombres(resultSet.getString("nombres"));
        persona.setApellidos(resultSet.getString(3));
        persona.setCedula(resultSet.getString(4));
        persona.setDireccion(resultSet.getString(5));

        result.add(persona);
      }
    } catch (SQLException exp) {
      exp.printStackTrace();
    } finally {
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
        e.printStackTrace();
      }
    }

    return result;
  }

  public List<Persona> getPersonasByApellidos(String apellidos) {
    List<Persona> result = new ArrayList<>();
    Conexion conexion = new Conexion();
    Connection connection = conexion.obtenerConexion();
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(personaRepositoryHelper.getPersonaByApellidos(apellidos));

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Persona persona =
            Persona.builder()
                .idPersona(resultSet.getInt(1))
                .nombres(resultSet.getString(2))
                .apellidos(resultSet.getString(3))
                .cedula(resultSet.getString(4))
                .direccion(resultSet.getString(5))
                .build();
        result.add(persona);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
        e.printStackTrace();
      }
    }
    return result;
  }

  public Persona getPersonaById(int idPersona) {
    Persona persona = null;
    Conexion conexion = new Conexion();
    Connection connection = conexion.obtenerConexion();
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(personaRepositoryHelper.getPersonaById());
      preparedStatement.setInt(1, idPersona);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        persona =
            Persona.builder()
                .idPersona(resultSet.getInt(1))
                .nombres(resultSet.getString(2))
                .apellidos(resultSet.getString(3))
                .cedula(resultSet.getString(4))
                .direccion(resultSet.getString(5))
                .build();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
        e.printStackTrace();
      }
    }
    return persona;
  }

  public boolean actualizarPersona(Persona persona) {
    boolean result = false;
    Conexion conexion = new Conexion();
    Connection connection = conexion.obtenerConexion();
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(personaRepositoryHelper.actualizarPersona());
      preparedStatement.setString(1, persona.getNombres());
      preparedStatement.setString(2, persona.getApellidos());
      preparedStatement.setString(3, persona.getCedula());
      preparedStatement.setString(4, persona.getDireccion());
      preparedStatement.setInt(5, persona.getIdPersona());

      int rowsAffected = preparedStatement.executeUpdate();
      result = rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
        e.printStackTrace();
      }
    }
    return result;
  }

  public boolean eliminarPersona(int personaId) {
    boolean result = false;
    Conexion conexion = new Conexion();
    Connection connection = conexion.obtenerConexion();
    try {
      PreparedStatement preparedStatement =
          connection.prepareStatement(personaRepositoryHelper.eliminarPersona());
      preparedStatement.setInt(1, personaId);

      int rowsAffected = preparedStatement.executeUpdate();
      result = rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
        e.printStackTrace();
      }
    }
    return result;
  }

    public boolean insertarPersona(Persona persona) {
        boolean result = false;
        Conexion conexion = new Conexion();
        Connection connection = conexion.obtenerConexion();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(personaRepositoryHelper.insertarPersona());
            preparedStatement.setString(1, persona.getNombres());
            preparedStatement.setString(2, persona.getApellidos());
            preparedStatement.setString(3, persona.getCedula());
            preparedStatement.setString(4, persona.getDireccion());

            int rowsAffected = preparedStatement.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

}
