package crearTrivia;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EjecutarTrivia {

  public static List<Pregunta> cargarPreguntas(String filePath) {
    StringBuilder content = new StringBuilder();
    List<Pregunta> triviaPreguntas = new ArrayList<>();

    // Leer el archivo JSON
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        content.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    // Parsear el contenido del JSON
    JSONObject jsonObject = new JSONObject(content.toString());
    JSONObject messageObject =
        jsonObject.getJSONArray("choices").getJSONObject(0).getJSONObject("message");
    JSONObject preguntasData = new JSONObject(messageObject.getString("content"));

    // Crear la lista de preguntas para el juego de trivia
    JSONArray preguntasArray = preguntasData.getJSONArray("preguntas");

    for (int i = 0; i < preguntasArray.length(); i++) {
      JSONObject preguntaObj = preguntasArray.getJSONObject(i);
      String textoPregunta = preguntaObj.getString("pregunta");
      JSONArray opcionesArray = preguntaObj.getJSONArray("opciones");

      List<String> opciones = new ArrayList<>();
      String respuestaCorrecta = null;

      for (int j = 0; j < opcionesArray.length(); j++) {
        JSONObject opcionObj = opcionesArray.getJSONObject(j);
        String respuesta = opcionObj.getString("respuesta");
        opciones.add(respuesta);
        if (opcionObj.getString("correcta").equals("si")) {
          respuestaCorrecta = respuesta;
        }
      }

      triviaPreguntas.add(new Pregunta(textoPregunta, opciones, respuestaCorrecta));
    }

    return triviaPreguntas;
  }
}
