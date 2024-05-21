package Interfaz;



import crearTrivia.Pregunta;
import crearTrivia.EjecutarTrivia;
import crearTrivia.Jugador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EjecutarTriviaGrafico extends JFrame {
  private Jugador jugador;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenu menu2;
  private JMenuItem generarPastel;
  private JMenuItem generarReporteMenu;
  private JMenuItem menu3;
  private JMenuItem seleccionarTrivia;
  private JTextArea preguntaTextArea;
  private ButtonGroup opcionesButtonGroup;
  private JButton siguienteButton;
  private JButton boton5050;
  private JButton botonTiempoExtra;
  private JLabel resultadosLabel;
  private JLabel countdownLabel;
  private List<Pregunta> triviaPreguntas;
  private int preguntaActualIndex;
  private int respuestasCorrectas;
  private int respuestasIncorrectas;
  private int respuestasSinResponder;
  private int countdownSeconds;
  private int tiempoComodin;
  private Timer countdownTimer;
  private List<JRadioButton> currentRadioButtons;


  // Variables para almacenar resultados y estadísticas
  private List<String> respuestasUsuario;
  private List<Pregunta> preguntasFallidas;
  private boolean comodin5050Usado;
  private boolean comodinTiempoExtraUsado;
  private List<Long> tiemposRespuesta;
  private long tiempoInicioPregunta;
  private String filePath;

  public EjecutarTriviaGrafico(Jugador pPlayer) {
    jugador = pPlayer;
    tiempoComodin = 60;
    // Configurar la ventana principal
    setTitle("Menu de Trivia");
    setSize(600, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Inicializar variables de resultados y estadísticas
    respuestasUsuario = new ArrayList<>();
    preguntasFallidas = new ArrayList<>();
    tiemposRespuesta = new ArrayList<>();
    comodin5050Usado = false;
    comodinTiempoExtraUsado = false;

    // Crear el panel principal
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    setContentPane(panel);

    // Crear la barra de menú
    menuBar = new JMenuBar();
    menu = new JMenu("Opciones");
    menu2 = new JMenu("Informacion");
    menu3 = new JMenuItem("INICIAR TRIVIA");

    generarPastel = new JMenuItem("Generar grafico pastel");
    generarPastel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        grafico pastel = new grafico();
        System.out.println("Imprimir Grafico Pastel");
        pastel.generarGrafico(respuestasCorrectas, respuestasIncorrectas, respuestasSinResponder);

      }
    });

    generarReporteMenu = new JMenuItem("Generar Reporte");
    generarReporteMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Generar Reporte");
        mostrarResumen();
      }
    });


    menu2.add(generarPastel);
    menu2.add(generarReporteMenu);
    menu2.setEnabled(false);
    menu3.setEnabled(false);
    menu3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cargarTrivia(filePath);
        System.out.println("Inicia el juego!!");
        menu3.setEnabled(false);
      }
    });


    seleccionarTrivia = new JMenuItem("Seleccionar Trivia");
    seleccionarTrivia.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mostrarFileChooser();

      }
    });
    menu.add(seleccionarTrivia);


    JMenuItem salir = new JMenuItem("Salir");


    salir.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?",
            "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
          dispose();
        }
      }
    });
    menu.add(salir);

    menuBar.add(menu);
    menuBar.add(menu2);
    menuBar.add(menu3);
    setJMenuBar(menuBar);

    // Crear el panel para los comodines
    JPanel comodinesPanel = new JPanel();
    comodinesPanel.setLayout(new GridLayout(2, 1));

    boton5050 = new JButton("50/50");
    boton5050.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        usarComodin5050();
        comodin5050Usado = true;
      }
    });

    botonTiempoExtra = new JButton("Tiempo Extra");
    botonTiempoExtra.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null,
            "¿Desea utilizar el comodín tiempo extra?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
          // Agregar tiempo extra
          usarComodinTiempoExtra(tiempoComodin); // Agrega x segundos
          countdownLabel.setText("Tiempo restante: " + countdownSeconds + " segundos");
          JOptionPane.showMessageDialog(null, "Comodín Tiempo Extra utilizado");
          botonTiempoExtra.setEnabled(false);
          comodinTiempoExtraUsado = true;
        }
      }
    });

    comodinesPanel.add(boton5050);
    comodinesPanel.add(botonTiempoExtra);

    // Muestra los botones deshabilitados, hasta que se inicia la trivia
    boton5050.setEnabled(false);
    botonTiempoExtra.setEnabled(false);

    // Crear el panel para mostrar la pregunta
    JPanel preguntaPanel = new JPanel();
    preguntaPanel.setLayout(new BorderLayout());

    preguntaTextArea = new JTextArea();
    preguntaTextArea.setLineWrap(true);
    preguntaTextArea.setWrapStyleWord(true);
    preguntaTextArea.setEditable(false);
    preguntaPanel.add(preguntaTextArea, BorderLayout.CENTER);

    // Crear el panel para las opciones
    JPanel opcionesPanel = new JPanel();
    opcionesPanel.setLayout(new GridLayout(0, 1));
    // Crear el panel para el Graficopastel
    JPanel graficoPastel = new JPanel();
    graficoPastel.setLayout(new GridLayout(0, 2));

    opcionesButtonGroup = new ButtonGroup();

    siguienteButton = new JButton("Siguiente");
    siguienteButton.setEnabled(false);
    siguienteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        registrarRespuesta();
        verificarRespuestaSeleccionada();
        mostrarSiguientePregunta();
      }
    });


    // Crear el panel para mostrar los resultados
    JPanel resultadosPanel = new JPanel();
    resultadosPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    resultadosLabel = new JLabel("Tiempo Restante: ");
    resultadosPanel.add(resultadosLabel);


    // Crear el JLabel para la cuenta regresiva
    countdownLabel = new JLabel("Tiempo restante: ");
    preguntaPanel.add(countdownLabel, BorderLayout.NORTH);


    // Agregar los componentes al panel principal
    panel.add(comodinesPanel, BorderLayout.WEST);
    panel.add(preguntaPanel, BorderLayout.CENTER);
    panel.add(opcionesPanel, BorderLayout.EAST);
    panel.add(siguienteButton, BorderLayout.SOUTH);
    // panel.add(resultadosPanel, BorderLayout.NORTH);
  }

  private void mostrarFileChooser() {
    // Obtener el directorio actual
    String currentDirectory = System.getProperty("user.dir");
    JFileChooser fileChooser = new JFileChooser(currentDirectory);
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    // Configurar el filtro de archivos para JSON
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      // Obtener el archivo seleccionado
      filePath = fileChooser.getSelectedFile().getAbsolutePath();
      // cargarTrivia(filePath);
      menu3.setEnabled(true);
    }
  }


  private void cargarTrivia(String filePath) {
    triviaPreguntas = EjecutarTrivia.cargarPreguntas(filePath);
    seleccionarTrivia.setEnabled(false);
    if (triviaPreguntas == null) {
      JOptionPane.showMessageDialog(this, "Error al cargar las preguntas.", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    preguntaActualIndex = 0;
    respuestasCorrectas = 0;
    respuestasIncorrectas = 0;
    respuestasSinResponder = triviaPreguntas.size(); // Todas las preguntas sin responder al inicio
    mostrarPregunta(triviaPreguntas.get(preguntaActualIndex));
    actualizarResultados();
    siguienteButton.setEnabled(true);
    boton5050.setEnabled(true);
    botonTiempoExtra.setEnabled(true);

  }


  private void mostrarPregunta(Pregunta pregunta) {
    preguntaTextArea.setText(pregunta.getPregunta());

    // Limpiar el panel de opciones
    opcionesButtonGroup.clearSelection();
    JPanel opcionesPanel = (JPanel) getContentPane().getComponent(2);
    opcionesPanel.removeAll();

    currentRadioButtons = new ArrayList<>();

    // Agregar las opciones al panel
    for (String opcion : pregunta.getOpciones()) {
      JRadioButton radioButton = new JRadioButton(opcion);
      radioButton.setActionCommand(opcion);
      opcionesButtonGroup.add(radioButton);
      opcionesPanel.add(radioButton);
      currentRadioButtons.add(radioButton);
    }

    // Iniciar la cuenta regresiva de 30 segundos (o el tiempo que desees)
    countdownSeconds = jugador.getTiempo();
    countdownLabel.setText("Tiempo restante: " + countdownSeconds + " segundos");
    if (countdownTimer != null) {
      countdownTimer.stop();
    }
    countdownTimer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (countdownSeconds > 0) {
          countdownSeconds--;
          countdownLabel.setText("Tiempo restante: " + countdownSeconds + " segundos");
        } else {
          countdownTimer.stop();
          JOptionPane.showMessageDialog(null, "¡Tiempo agotado!");
          registrarRespuesta();
          mostrarSiguientePregunta();
        }
      }
    });
    countdownTimer.start();

    // Registrar el tiempo de inicio de la pregunta
    tiempoInicioPregunta = System.currentTimeMillis();

    // Actualizar la ventana
    revalidate();
    repaint();
  }

  private void verificarRespuestaSeleccionada() {
    // Obtener el botón de opción seleccionado
    ButtonModel seleccionado = opcionesButtonGroup.getSelection();

    // Verificar si se ha seleccionado una respuesta
    if (seleccionado != null) {
      String respuestaSeleccionada = seleccionado.getActionCommand();
      String respuestaCorrecta = triviaPreguntas.get(preguntaActualIndex).getRespuestaCorrecta();

      // Verificar si la respuesta seleccionada es correcta
      if (respuestaSeleccionada.equals(respuestaCorrecta)) {
        respuestasCorrectas++;
      } else {
        respuestasIncorrectas++;
      }

      // Actualizar el contador de respuestas sin responder
      respuestasSinResponder--;

      // Actualizar los resultados mostrados
      actualizarResultados();
    }
  }

  private void mostrarSiguientePregunta() {
    if (preguntaActualIndex < triviaPreguntas.size() - 1) {
      preguntaActualIndex++;
      mostrarPregunta(triviaPreguntas.get(preguntaActualIndex));
    } else {
      registrarRespuesta();
      JOptionPane.showMessageDialog(this, "¡Fin de la trivia!");
      countdownTimer.stop();
      siguienteButton.setEnabled(false);
      boton5050.setEnabled(false);
      botonTiempoExtra.setEnabled(false);
      deshabilitarOpciones();
      menu2.setEnabled(true);
      mostrarResumen();
    }
  }

  private void registrarRespuesta() {
    String respuestaSeleccionada = opcionesButtonGroup.getSelection() == null ? "Sin responder"
        : opcionesButtonGroup.getSelection().getActionCommand();
    respuestasUsuario.add(respuestaSeleccionada);

    // Calcular y almacenar el tiempo de respuesta
    long tiempoRespuesta = System.currentTimeMillis() - tiempoInicioPregunta;
    tiemposRespuesta.add(tiempoRespuesta);

    // Verificar si la respuesta es incorrecta
    Pregunta pregunta = triviaPreguntas.get(preguntaActualIndex);
    if (!respuestaSeleccionada.equals(pregunta.getRespuestaCorrecta())) {
      preguntasFallidas.add(pregunta);
    }
  }

  private void usarComodin5050() {
    if (triviaPreguntas == null || triviaPreguntas.isEmpty() || currentRadioButtons == null
        || currentRadioButtons.isEmpty()) {
      return;
    }

    Pregunta pregunta = triviaPreguntas.get(preguntaActualIndex);
    String respuestaCorrecta = pregunta.getRespuestaCorrecta();
    List<JRadioButton> opcionesIncorrectas = new ArrayList<>();

    for (JRadioButton radioButton : currentRadioButtons) {
      if (!radioButton.getActionCommand().equals(respuestaCorrecta)) {
        opcionesIncorrectas.add(radioButton);
      }
    }

    Collections.shuffle(opcionesIncorrectas);

    // Deshabilitar dos opciones incorrectas
    for (int i = 0; i < 2 && i < opcionesIncorrectas.size(); i++) {
      opcionesIncorrectas.get(i).setEnabled(false);
    }

    // Deshabilitar el botón "50/50" después de usarlo
    boton5050.setEnabled(false);
  }

  private void usarComodinTiempoExtra(int pSegundos) {
    countdownSeconds += pSegundos;
  }

  private void actualizarResultados() {
    resultadosLabel
        .setText("Respuestas correctas: " + respuestasCorrectas + " | Respuestas incorrectas: "
            + respuestasIncorrectas + " | Respuestas sin responder: " + respuestasSinResponder);
  }

  private void deshabilitarOpciones() {
    if (currentRadioButtons != null) {
      for (JRadioButton radioButton : currentRadioButtons) {
        radioButton.setEnabled(false);
      }
    }
  }

  private void mostrarResumen() {
    GenerarReporte reporte;
    reporte = new GenerarReporte(triviaPreguntas, comodin5050Usado, comodinTiempoExtraUsado,
        respuestasUsuario, preguntasFallidas, tiemposRespuesta);


    // Mostrar el resumen en un JTextArea dentro de un JScrollPane
    JTextArea resumenTextArea = new JTextArea(reporte.getResumen());
    resumenTextArea.setEditable(false);
    resumenTextArea.setLineWrap(true);
    resumenTextArea.setWrapStyleWord(true);

    JScrollPane scrollPane = new JScrollPane(resumenTextArea);
    scrollPane.setPreferredSize(new Dimension(600, 400));

    // Crear botón "Enviar Reporte"
    JButton enviarReporteButton = new JButton("Enviar Reporte");
    enviarReporteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Lógica para enviar el reporte
        JOptionPane.showMessageDialog(null, "Reporte enviado!");
        enviarReporteButton.setEnabled(false);
      }
    });

    // Crear panel para contener JTextArea y botón
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(enviarReporteButton, BorderLayout.SOUTH);

    // Mostrar el panel en un JOptionPane
    JOptionPane.showMessageDialog(this, panel, "Resumen de la trivia",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /*
   * public static void main(String[] args) { SwingUtilities.invokeLater(new Runnable() {
   * 
   * @Override public void run() { new EjecutarTriviaGrafico().setVisible(true); } }); }
   */
}
