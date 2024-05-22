package crearTrivia;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Comentario extends JFrame {

    private static final long serialVersionUID = 1L;
    private final List<String> comentarios;
    private final List<String> sentimientos;
    private int contadorPositivo;
    private int contadorNegativo;
    private int contadorNeutro;

    public Comentario() {
        this.comentarios = new ArrayList<>();
        this.sentimientos = new ArrayList<>();
        this.contadorPositivo = 0;
        this.contadorNegativo = 0;
        this.contadorNeutro = 0;
        setTitle("Análisis de Sentimientos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Comentario comentario = new Comentario();
        comentario.cargarComentariosGuardados();
        comentario.ejecutarAnalisis();
    }

    private void ejecutarAnalisis() {
        ingresarComentarios();
        analizarSentimientos();
        guardarComentarios();
        mostrarGraficoPastel();
    }

    private void ingresarComentarios() {
        JTextArea textArea = new JTextArea();
        JOptionPane.showMessageDialog(null, textArea, "Ingrese los comentarios", JOptionPane.PLAIN_MESSAGE);
        String texto = textArea.getText();
        String[] lineas = texto.split("\\r?\\n");
        for (String linea : lineas) {
            if (!linea.isEmpty()) {
                comentarios.add(linea);
            }
        }
    }

    private void analizarSentimientos() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for (String comentario : comentarios) {
            Annotation annotation = new Annotation(comentario);
            pipeline.annotate(annotation);
            List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                String sentimiento = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                sentimientos.add(sentimiento);
                switch (sentimiento) {
                    case "Positive" -> contadorPositivo++;
                    case "Negative" -> contadorNegativo++;
                    default -> contadorNeutro++;
                }
            }
        }
    }

    private void mostrarGraficoPastel() {
        PieDataset dataset = createDataset(contadorPositivo, contadorNegativo, contadorNeutro);
        JFreeChart chart = createChart(dataset, "Análisis de Sentimientos");
        ApplicationFrame frame = new ApplicationFrame("Gráfico de Pastel");
        frame.setContentPane(new ChartPanel(chart));
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    private PieDataset createDataset(int positivos, int negativos, int neutros) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Positivos", positivos);
        dataset.setValue("Negativos", negativos);
        dataset.setValue("Neutros", neutros);
        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        plot.setNoDataMessage("No hay datos");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
    }

    private void cargarComentariosGuardados() {
        try {
            File archivo = new File("comentarios.txt");
            if (archivo.exists()) {
                Scanner scanner = new Scanner(archivo);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        comentarios.add(partes[0]);
                        sentimientos.add(partes[1]);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarComentarios() {
        try {
            FileWriter writer = new FileWriter("comentarios.txt", true);
            for (int i = 0; i < comentarios.size(); i++) {
                writer.write(comentarios.get(i) + "," + sentimientos.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
