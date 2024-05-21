/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
 * license Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VentanaComentario {
  private JFrame frame;
  private JTextArea textArea;
  private JButton botonAceptar;
  private JButton botonCancelar;

  public VentanaComentario() {
    frame = new JFrame("Ventana de Comentario");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel label = new JLabel("Ingrese su comentario:");
    frame.add(label);

    textArea = new JTextArea(5, 30);
    frame.add(new JScrollPane(textArea));

    botonAceptar = new JButton("Aceptar");
    botonAceptar.setEnabled(false); // Inicialmente deshabilitado

    botonCancelar = new JButton("Cancelar");

    // Panel para contener los botones
    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Disposición horizontal, alineación
                                                              // derecha
    panelBotones.add(botonAceptar);
    panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio entre botones
    panelBotones.add(botonCancelar);

    frame.add(panelBotones);

    textArea.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        habilitarBotonAceptar();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        habilitarBotonAceptar();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        habilitarBotonAceptar();
      }
    });

    botonAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        aceptarComentario();
      }
    });

    botonCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelarComentario();
      }
    });

    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.pack();
    frame.setVisible(true);
  }

  private void habilitarBotonAceptar() {
    botonAceptar.setEnabled(!textArea.getText().trim().isEmpty());
  }

  private void aceptarComentario() {
    String comentario = textArea.getText();
    System.out.println("Comentario aceptado: " + comentario);
    // Aquí puedes agregar más lógica para lo que quieras hacer con el comentario
    frame.dispose(); // Cerrar la ventana después de aceptar el comentario
  }

  private void cancelarComentario() {
    System.out.println("Comentario cancelado");
    frame.dispose(); // Cerrar la ventana después de cancelar el comentario
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new VentanaComentario();
      }
    });
  }
}
