package Interfaz;

import crearTrivia.GenerarTrivia;
import java.io.File;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Trivia extends javax.swing.JFrame {

  private String nombre;
  private String correo;
  private int tiempo;

  public Trivia() {
    initComponents();
    setResizable(false); // Hace que la ventana no sea redimensionable
    setLocationRelativeTo(null);
    setSize(570, 489);
  }


  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jTextField2 = new javax.swing.JTextField();
    jTextField3 = new javax.swing.JTextField();
    jComboBox2 = new javax.swing.JComboBox<>();
    jComboBox3 = new javax.swing.JComboBox<>();
    jComboBox4 = new javax.swing.JComboBox<>();
    jTextField5 = new javax.swing.JTextField();
    jTextField6 = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);

    jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton1.setText("Cancelar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    getContentPane().add(jButton1);
    jButton1.setBounds(400, 370, 120, 70);

    jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jButton2.setText("Generar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });
    getContentPane().add(jButton2);
    jButton2.setBounds(40, 370, 120, 70);

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel2.setText("Ingrese su nombre");
    getContentPane().add(jLabel2);
    jLabel2.setBounds(100, 20, 160, 30);

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel3.setText("Tema deseado");
    getContentPane().add(jLabel3);
    jLabel3.setBounds(100, 60, 270, 30);

    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel7.setText("Detalle sobre el tema");
    getContentPane().add(jLabel7);
    jLabel7.setBounds(100, 100, 270, 30);

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel4.setText("Correo Electrónico");
    getContentPane().add(jLabel4);
    jLabel4.setBounds(100, 140, 160, 30);

    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel5.setText("Cantidad de Preguntas");
    getContentPane().add(jLabel5);
    jLabel5.setBounds(100, 180, 260, 40);

    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel6.setText("Tiempo por pregunta");
    getContentPane().add(jLabel6);
    jLabel6.setBounds(100, 260, 190, 30);

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel1.setText("Idioma de la trivia");
    getContentPane().add(jLabel1);
    jLabel1.setBounds(100, 320, 190, 30);

    jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // String NOMBRE
    getContentPane().add(jTextField2);
    jTextField2.setBounds(320, 20, 200, 30);

    jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // String Tema
    getContentPane().add(jTextField3);
    jTextField3.setBounds(320, 60, 200, 30);

    jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // String Detalle
    getContentPane().add(jTextField6);
    jTextField6.setBounds(320, 100, 200, 30);

    jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // Idioma de la trivia
    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] {"Español", "Inglés", "Mandarín", "Francés"}));
    jComboBox2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox2ActionPerformed(evt);
      }
    });
    getContentPane().add(jComboBox2);
    jComboBox2.setBounds(310, 320, 120, 40);

    jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // Tiempo por pregunta
    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] {"1 min", "2 mins", "3 mins", "4 mins"}));
    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox3ActionPerformed(evt);
      }
    });
    getContentPane().add(jComboBox3);
    jComboBox3.setBounds(350, 260, 80, 40);

    jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // Tiempo por pregunta
    jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] {"6", "7", "8", "9", "10", "11", "12"}));
    jComboBox4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox3ActionPerformed(evt);
      }
    });

    getContentPane().add(jComboBox4);
    jComboBox4.setBounds(350, 180, 80, 40);

    jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    getContentPane().add(jTextField5);
    jTextField5.setBounds(320, 140, 200, 30);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
    setNombre("");
    setCorreo("");
    setTiempo(30);
    this.dispose();
  }// GEN-LAST:event_jButton1ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
    // Validar que todos los campos estén completos
    if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty()
        || jTextField6.getText().isEmpty() || jTextField5.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Error",
          JOptionPane.ERROR_MESSAGE);
    } else {
      setNombre(jTextField2.getText());
      String tema = jTextField3.getText();
      String detalle = jTextField6.getText();
      setCorreo(jTextField5.getText());
      int cantidadPreguntas = jComboBox3.getSelectedIndex() + 6;
      setTiempo((jComboBox3.getSelectedIndex() + 1) * 60); // Asumiendo que los índices corresponden
                                                           // a 1 min, 2 mins, etc.
      String idioma = (String) jComboBox2.getSelectedItem();
      GenerarTrivia nuevaTrivia =
          new GenerarTrivia(tema, tema, correo, String.valueOf(cantidadPreguntas), idioma, detalle);
      nuevaTrivia.LectorTriviaJSON();
      int confirm = JOptionPane.showConfirmDialog(this, "Trivia generada, ¿desea guardarla?",
          "Éxito", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
      if (confirm == JOptionPane.YES_OPTION) {
        // Lógica para guardar la trivia
        JOptionPane.showMessageDialog(this, "Trivia guardada exitosamente", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
      }
      if (confirm == JOptionPane.NO_OPTION) {
        File archivoGenerado = new File(tema + ".json");
        if (archivoGenerado.delete()) {
          JOptionPane.showMessageDialog(this, "La trivia no fue guardada.", "Información",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(this, "No se pudo borrar la trivia", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }

      this.dispose();
    }
  }// GEN-LAST:event_jButton2ActionPerformed

  private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox2ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jComboBox2ActionPerformed

  private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox3ActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_jComboBox3ActionPerformed

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
      java.util.logging.Logger.getLogger(Trivia.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Trivia.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Trivia.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Trivia.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    }
    // </editor-fold>
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Trivia().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JComboBox<String> jComboBox2;
  private javax.swing.JComboBox<String> jComboBox3;
  private javax.swing.JComboBox<String> jComboBox4;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JTextField jTextField2;
  private javax.swing.JTextField jTextField3;
  private javax.swing.JTextField jTextField5;
  private javax.swing.JTextField jTextField6;
  // End of variables declaration//GEN-END:variables

  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return the correo
   */
  public String getCorreo() {
    return correo;
  }

  /**
   * @param correo the correo to set
   */
  public void setCorreo(String correo) {
    this.correo = correo;
  }

  /**
   * @return the tiempo
   */
  public int getTiempo() {
    return tiempo;
  }

  /**
   * @param tiempo the tiempo to set
   */
  public void setTiempo(int tiempo) {
    this.tiempo = tiempo;
  }
}
