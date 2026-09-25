package com.mycompany.tp_calidad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class TurnoManagerTest {

    // Validacion de datos del paciente

    @Test
    void datosPacienteValidos_nombreYEdadCorrectos_devuelveTrue() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String nombre = "Ana Perez";
        int edad = 30;

        // Act
        boolean resultado = manager.datosPacienteValidos(nombre, edad);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void datosPacienteValidos_nombreNulo_devuelveFalse() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String nombre = null;
        int edad = 30;

        // Act
        boolean resultado = manager.datosPacienteValidos(nombre, edad);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void datosPacienteValidos_nombreVacio_devuelveFalse() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String nombre = "";
        int edad = 30;

        // Act
        boolean resultado = manager.datosPacienteValidos(nombre, edad);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void datosPacienteValidos_edadCero_devuelveFalse() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String nombre = "Ana Perez";
        int edad = 0;

        // Act
        boolean resultado = manager.datosPacienteValidos(nombre, edad);

        // Assert
        assertFalse(resultado);
    }

    // Categoria segun obra social

    @Test
    void obtenerCategoria_obraSocialOSDE_devuelvePremium() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String obraSocial = "OSDE";

        // Act
        String categoria = manager.obtenerCategoria(obraSocial);

        // Assert
        assertEquals("PREMIUM", categoria);
    }

    @Test
    void obtenerCategoria_obraSocialSwiss_devuelvePremium() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String obraSocial = "SWISS";

        // Act
        String categoria = manager.obtenerCategoria(obraSocial);

        // Assert
        assertEquals("PREMIUM", categoria);
    }

    @Test
    void obtenerCategoria_obraSocialPublica_devuelvePublico() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String obraSocial = "PUBLICA";

        // Act
        String categoria = manager.obtenerCategoria(obraSocial);

        // Assert
        assertEquals("PUBLICO", categoria);
    }

    @Test
    void obtenerCategoria_obraSocialDesconocida_devuelveSinCategoria() {
        // Arrange
        TurnoManager manager = new TurnoManager();
        String obraSocial = "IOMA";

        // Act
        String categoria = manager.obtenerCategoria(obraSocial);

        // Assert
        assertEquals("SIN_CATEGORIA", categoria);
    }

    // Construccion del turno

    @Test
    void construirTurno_pacienteUrgente_agregaSufijoUrgente() {
        // Arrange
        TurnoManager manager = new TurnoManager();

        // Act
        String turno = manager.construirTurno("Ana", 30, "OSDE", true);

        // Assert
        assertEquals("Ana-30-OSDE-URGENTE", turno);
    }

    @Test
    void construirTurno_pacienteNoUrgente_noAgregaSufijo() {
        // Arrange
        TurnoManager manager = new TurnoManager();

        // Act
        String turno = manager.construirTurno("Ana", 30, "OSDE", false);

        // Assert
        assertEquals("Ana-30-OSDE", turno);
    }

    // Registro del turno

    @Test
    void registrarTurno_datosValidos_guardaElTurno() {
        // Arrange
        TurnoManager manager = new TurnoManager();

        // Act
        boolean registrado = manager.registrarTurno("Ana", 30, "PUBLICA", false);

        // Assert
        assertTrue(registrado);
        assertEquals(List.of("Ana-30-PUBLICA"), manager.getTurnos());
    }

    @Test
    void registrarTurno_datosInvalidos_noGuardaNada() {
        // Arrange
        TurnoManager manager = new TurnoManager();

        // Act
        boolean registrado = manager.registrarTurno("", 30, "PUBLICA", false);

        // Assert
        assertFalse(registrado);
        assertTrue(manager.getTurnos().isEmpty());
    }
}
