package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Estudiante extends Persona{
    private String carnet;
    private String carrera;
    private String correoIntitucional;
    private String programaSocial;
    private boolean bacado;

    public String getCorreoIntitucional() {
        return correoIntitucional;
    }

    public void setCorreoIntitucional(String correoIntitucional) {
        this.correoIntitucional = correoIntitucional;
    }

    public String getProgramaSocial() {
        return programaSocial;
    }

    public void setProgramaSocial(String programaSocial) {
        this.programaSocial = programaSocial;
    }

    public boolean isBacado() {
        return bacado;
    }

    public void setBacado(boolean bacado) {
        this.bacado = bacado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "carnet='" + getCedula() + '\'' +
                ", carrera='" +carrera + '\'' +
                "} " + super.toString();
    }
}
