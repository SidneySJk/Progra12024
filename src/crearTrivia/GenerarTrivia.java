package crearTrivia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * Esta clase se encarga de generar una trivia basada en un tema y parámetros específicos. La trivia
 * se genera mediante una solicitud a la API de OpenAI, y se procesa el JSON recibido para extraer
 * las preguntas y respuestas.
 */
public class GenerarTrivia {
  private String nombre;
  private String tema;
  private String email;
  private String cantPreguntas;
  private String idioma;
  private String detalle;
  String prompt;


  /**
   * Constructor para inicializar una instancia de GenerarTrivia con parámetros específicos.
   * 
   * @param pNombre El nombre de la trivia.
   * @param pTema El tema de la trivia.
   * @param pEmail El email del usuario que solicita la trivia.
   * @param pCantPreguntas La cantidad de preguntas deseadas.
   * @param pTiempoMax El tiempo máximo para responder cada pregunta.
   * @param pIdioma El idioma de las preguntas.
   * @param pDetalle Detalles adicionales sobre la trivia.
   * @param pGuardarTriviaLocalmente Indica si la trivia debe guardarse localmente.
   */
  public GenerarTrivia(String pNombre, String pTema, String pEmail, String pCantPreguntas,
      String pIdioma, String pDetalle) {
    nombre = pNombre;
    tema = pTema;
    email = pEmail;
    cantPreguntas = pCantPreguntas;
    idioma = pIdioma;
    detalle = pDetalle;
    prompt = String.format(
        "Estoy construyendo un juego de trivia sobre %s. Puedes redactar %s preguntas relacionadas con %s. Necesito para cada pregunta 4 opciones, donde una es la respuesta correcta. Además, el idioma de redacción de las preguntas lo requiero en %s. Los datos los requiero en formato JSON, Debes indicar mediante el atributo correcta='si' para la respuesta correcta a la pregunta y las respuestas incorrectas a la pregunta el atributo correcta='no'. Utilizar las siguientes etiquetas dentro de content: 'preguntas', 'pregunta', 'opciones', 'respuesta'",
        tema, cantPreguntas, detalle, idioma, email);

  }

  /**
   * Método para interactuar con la API de ChatGPT y obtener una respuesta generada.
   * 
   * @param prompt El texto que se enviará como solicitud a la API.
   * @return La respuesta de la API como un string.
   */
  public String chatGPT(String prompt) {
    String url = "https://api.openai.com/v1/chat/completions";
    String apiKey = ""; // API KEY GENERADO
    String model = "gpt-3.5-turbo";

    try {
      URL obj = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", "Bearer " + apiKey);
      connection.setRequestProperty("Content-Type", "application/json");

      // The request body
      String body = "{\"model\": \"" + model
          + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
      connection.setDoOutput(true);
      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(body);
      writer.flush();
      writer.close();

      // Response from ChatGPT
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      StringBuffer response = new StringBuffer();
      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();

      // Save response to a JSON file
      saveResponseToFile(response.toString());

      // calls the method to extract the message.
      return extractMessageFromJSONResponse(response.toString());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Método privado para guardar la respuesta JSON obtenida en un archivo local.
   * 
   * @param jsonResponse El string de respuesta JSON para ser guardado.
   * @throws IOException Si ocurre un error al escribir el archivo.
   */
  private void saveResponseToFile(String jsonResponse) throws IOException {
    // Crear una instancia de Gson con pretty printing
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Convertir la cadena JSON en un objeto Java genérico
    Object json = gson.fromJson(jsonResponse, Object.class);

    // Convertir el objeto Java nuevamente en una cadena JSON con formato
    String prettyJsonString = gson.toJson(json);

    // Guardar el JSON formateado en un archivo
    File file = new File(nombre + ".json");
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(prettyJsonString);
    }
  }


  /**
   * Método para extraer el mensaje de una respuesta JSON.
   * 
   * @param response La respuesta JSON de la cual extraer el mensaje.
   * @return El mensaje extraído.
   */
  public String extractMessageFromJSONResponse(String response) {
    int start = response.indexOf("content") + 11;
    int end = response.indexOf("\"", start);
    return response.substring(start, end);
  }

  /**
   * Método para leer y procesar un archivo JSON que contiene las preguntas de la trivia.
   */
  public void LectorTriviaJSON() {
    String pathToFile = nombre + ".json"; // Nombre del archivo JSON

    try {
      String jsonString = new String(Files.readAllBytes(Paths.get(pathToFile)));
      JSONObject jsonObject = new JSONObject(jsonString);

      // Navega a través del objeto JSON para llegar al contenido deseado
      JSONArray choices = jsonObject.getJSONArray("choices");
      if (choices.length() > 0) {
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject message = firstChoice.getJSONObject("message");
        String triviaContent = message.getString("content");

        // Parsea el contenido JSON interno de trivia
        JSONObject triviaJson = new JSONObject(triviaContent);
        JSONArray preguntas = triviaJson.getJSONArray("preguntas");
    //    System.out.println("Preguntas de Trivia:");
        for (int i = 0; i < preguntas.length(); i++) {
          JSONObject pregunta = preguntas.getJSONObject(i);
    //      System.out.println((i + 1) + ". " + pregunta.getString("pregunta"));

          JSONArray respuestas = pregunta.getJSONArray("opciones");
          for (int j = 0; j < respuestas.length(); j++) {
            JSONObject respuesta = respuestas.getJSONObject(j);
    //        System.out.print("   - " + respuesta.getString("respuesta"));

    /*        if (respuesta.getString("correcta").equals("si")) { // System.out.println();
              System.out.println(" (correcta)"); // Muestra cual es la respuesta correcta. Cuando la
                                                 // progra este lista 'comentar' el if/else excepto
                                                 // un println
            } else {
              System.out.println();
            }*/
          }
          System.out.println();
        }
      }
    } catch (IOException e) {
      System.out.println("Error al leer archivo: " + e.getMessage());
      System.out.println("NUEVA TRIVIA GENERANDOSE!!!");
      //System.out.println(chatGPT(prompt));
      chatGPT(prompt);
      LectorTriviaJSON();
    } catch (Exception e) {
      System.out.println("Error al procesar JSON: " + e.getMessage());
      System.out.println("NUEVA TRIVIA GENERANDOSE!!!");
      //System.out.println(chatGPT(prompt));
      chatGPT(prompt);
      LectorTriviaJSON();
    }
  }
  /*
   * public static void main(String[] args) { GenerarTrivia test = new GenerarTrivia("Trivia3",
   * "Redes cisco", "guvargas@gmail.com", "7", "120", "Espanol", "Spanning Tree", true);
   * 
   * System.out.println(test.prompt); System.out.println(test.chatGPT(test.prompt));
   * test.LectorTriviaJSON(); }
   */
}
