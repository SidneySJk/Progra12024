package crearTrivia;



import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;
import java.util.List;

/**
 *
 * @author andog
 */


public class Comentario {

  public static void main(String[] args) {
    // Configurar las propiedades del pipeline
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

    // Crear el pipeline
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Texto de ejemplo
    String text = "I love to study";

    // Ejecutar el análisis de sentimientos
    Annotation annotation = new Annotation(text);
    pipeline.annotate(annotation);

    // Extraer las anotaciones de sentimientos
    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
      System.out.println("Sentimiento: " + sentiment + " - Texto: " + sentence);
    }
  }
}
