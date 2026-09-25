package com.mycompany.tp_calidad;

import java.util.ArrayList;
import java.util.List;

public class TurnoManager {

    private final List<String> turnos = new ArrayList<>();

    public boolean registrarTurno(String nombrePaciente, int edad, String obraSocial, boolean urgente) {
        if (!datosPacienteValidos(nombrePaciente, edad)) {
            return false;
        }
        turnos.add(construirTurno(nombrePaciente, edad, obraSocial, urgente));
        return true;
    }

    public boolean datosPacienteValidos(String nombrePaciente, int edad) {
        return nombrePaciente != null && !nombrePaciente.isEmpty() && edad > 0;
    }

    public String obtenerCategoria(String obraSocial) {
        if ("OSDE".equals(obraSocial) || "SWISS".equals(obraSocial)) {
            return "PREMIUM";
        } else if ("PUBLICA".equals(obraSocial)) {
            return "PUBLICO";
        }
        return "SIN_CATEGORIA";
    }

    public String construirTurno(String nombrePaciente, int edad, String obraSocial, boolean urgente) {
        String turno = nombrePaciente + "-" + edad + "-" + obraSocial;
        if (urgente) {
            turno = turno + "-URGENTE";
        }
        return turno;
    }

    public List<String> getTurnos() {
        return new ArrayList<>(turnos);
    }
}
