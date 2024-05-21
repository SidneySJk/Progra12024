/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
 * license Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

// import Interfaz.Trivia;
import crearTrivia.Jugador;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
@SuppressWarnings("serial")
public class VPrinci extends javax.swing.JFrame {

  Jugador player = new Jugador();

  /**
   * Creates new form VPrinci
   */
  public VPrinci() {
    initComponents();
  }



  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    JLabel jLabelTitulo = new javax.swing.JLabel();
    JPanel jPanelTitulo = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelTitulo.setText("JUEGO DE TRIVIAS");

    jPanelTitulo.setBackground(new java.awt.Color(204, 204, 255)); // Color de fondo opcional
    jPanelTitulo.setLayout(new java.awt.BorderLayout());
    jPanelTitulo.add(jLabelTitulo, java.awt.BorderLayout.CENTER);

    jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton1.setText("Jugar Trivias");
    jButton1.setEnabled(false);
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton2.setText("Generar Trivia");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton3.setText("SALIR");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
      }
    });



    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup().addGap(132, 132, 132)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(116, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(28, 28, 28)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(26, 26, 26).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(47, Short.MAX_VALUE)));
    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    // Crear una instancia de EjecutarTriviaGrafico
    EjecutarTriviaGrafico JugarT = new EjecutarTriviaGrafico(player);

    // Agregar un WindowListener a JugarT para detectar cuando se cierre
    JugarT.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        // Hacer que la ventana actual vuelva a ser visible cuando se cierre JugarT
        setVisible(true);
      }
    });

    // Ocultar la ventana actual
    this.setVisible(false);

    // Mostrar la ventana EjecutarTriviaGrafico
    JugarT.setVisible(true);
  }

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
    Trivia GenerarT = new Trivia();

    // Agregar un WindowListener a JugarT para detectar cuando se cierre
    GenerarT.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        // Hacer que la ventana actual vuelva a ser visible cuando se cierre JugarT
        setVisible(true);
        jButton1.setEnabled(true);
        player.setNombre(GenerarT.getNombre());
        player.setCorreo(GenerarT.getCorreo());
        player.setTiempo(GenerarT.getTiempo());
        /*
         * System.out.println(GenerarT.getCorreo()); System.out.println(GenerarT.getNombre());
         * System.out.println(GenerarT.getTiempo()); System.out.println(player.toString());
         */
      }

    });

    // Ocultar la ventana actual
    this.setVisible(false);

    // Mostrar la ventana EjecutarTriviaGrafico
    GenerarT.setVisible(true);



  }// GEN-LAST:event_jButton2ActionPerformed


  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    System.out.println("Hasta luego!");
    System.exit(0);
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(VPrinci.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(VPrinci.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(VPrinci.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(VPrinci.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new VPrinci().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  // End of variables declaration//GEN-END:variables
}
