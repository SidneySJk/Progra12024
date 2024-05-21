package crearTrivia;

public class Jugador {
  private String nombre;
  private String correo;
  private int tiempo;


  public Jugador() {
    this.nombre = "";
    this.correo = "";
    this.tiempo = 30;
  }

  public Jugador(String nombre, String correo, int tiempo) {
    super();
    this.nombre = nombre;
    this.correo = correo;
    this.tiempo = tiempo;
  }


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

  @Override
  public String toString() {
    return "Jugador [nombre=" + nombre + ", correo=" + correo + ", tiempo=" + tiempo + "]";
  }


}
