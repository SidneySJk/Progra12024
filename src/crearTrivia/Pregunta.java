
package crearTrivia;

import java.util.List;

public class Pregunta {
  private String pregunta;
  private List<String> opciones;
  private String correcta;

  public Pregunta(String pregunta, List<String> opciones, String correcta) {
    this.pregunta = pregunta;
    this.opciones = opciones;
    this.correcta = correcta;
  }

  public String getPregunta() {
    return pregunta;
  }

  public List<String> getOpciones() {
    return opciones;
  }

  @Override
  public String toString() {
    return "Pregunta: " + pregunta + "\nOpciones: " + opciones + "\nRespuesta correcta: "
        + correcta;
  }

  public String getRespuestaCorrecta() {
    return correcta;
  }
}

