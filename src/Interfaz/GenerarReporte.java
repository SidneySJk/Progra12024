package Interfaz;

import crearTrivia.Pregunta;
import java.util.List;

public class GenerarReporte {
  private StringBuilder resumen;
  private List<Pregunta> triviaPreguntas;
  private int respuestasCorrectas;
  private int respuestasIncorrectas;
  private int respuestasSinResponder;
  private List<String> respuestasUsuario;
  private List<Pregunta> preguntasFallidas;
  private boolean comodin5050Usado;
  private boolean comodinTiempoExtraUsado;
  private List<Long> tiemposRespuesta;

  public GenerarReporte(List<Pregunta> pTriviaPreguntas, boolean pComodin5050,
      boolean pComodinTiempo, List<String> pRespuestasUsuario, List<Pregunta> pPreguntasFallidas,
      List<Long> pTiemposRespuesta) {
    resumen = new StringBuilder();
    triviaPreguntas = pTriviaPreguntas;
    comodin5050Usado = pComodin5050;
    comodinTiempoExtraUsado = pComodinTiempo;
    respuestasUsuario = pRespuestasUsuario;
    preguntasFallidas = pPreguntasFallidas;
    tiemposRespuesta = pTiemposRespuesta;

    // Initialize counts
    respuestasCorrectas = 0;
    respuestasIncorrectas = 0;
    respuestasSinResponder = 0;

    resumen.append("Resumen de la trivia:\n\n");

    resumen.append("Trivia Completa:\n");
    for (int i = 0; i < triviaPreguntas.size(); i++) {
      Pregunta pregunta = triviaPreguntas.get(i);
      String respuestaUsuario = respuestasUsuario.get(i);

      resumen.append("Pregunta ").append(i + 1).append(": ").append(pregunta.getPregunta())
          .append("\n");
      resumen.append("Opciones:\n");
      List<String> opciones = pregunta.getOpciones();
      for (int j = 0; j < opciones.size(); j++) {
        resumen.append((char) ('A' + j)).append(") ").append(opciones.get(j)).append("\n");
      }
      resumen.append("Respuesta seleccionada: ")
          .append(respuestaUsuario != null ? respuestaUsuario : "Sin responder").append("\n");
      resumen.append("Respuesta correcta: ").append(pregunta.getRespuestaCorrecta()).append("\n\n");

      if (respuestaUsuario == null) {
        respuestasSinResponder++;
      } else if (respuestaUsuario.equals(pregunta.getRespuestaCorrecta())) {
        respuestasCorrectas++;
      } else {
        respuestasIncorrectas++;
      }
    }

    resumen.append("Preguntas fallidas:\n");
    for (int i = 0; i < preguntasFallidas.size(); i++) {
      Pregunta pregunta = preguntasFallidas.get(i);
      resumen.append("Pregunta: ").append(pregunta.getPregunta()).append("\n");
      resumen.append("Respuesta correcta: ").append(pregunta.getRespuestaCorrecta()).append("\n\n");
    }

    resumen.append("Comodines usados:\n");
    resumen.append("50/50: ").append(comodin5050Usado ? "Sí" : "No").append("\n");
    resumen.append("Tiempo extra: ").append(comodinTiempoExtraUsado ? "Sí" : "No").append("\n\n");

    if (!tiemposRespuesta.isEmpty()) {
      long tiempoTotal = tiemposRespuesta.stream().mapToLong(Long::longValue).sum();
      long tiempoPromedio = tiempoTotal / tiemposRespuesta.size();
      resumen.append("Tiempo promedio para responder las preguntas: ").append(tiempoPromedio / 1000)
          .append(" segundos\n");
    }

    resumen.append("\nPuntaje obtenido: ").append(respuestasCorrectas).append("/")
        .append(triviaPreguntas.size()).append(" puntos");
  }

  public String getResumen() {
    return resumen.toString();
  }
}
