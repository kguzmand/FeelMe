package logic;

public class Cancion {
  private final String nombre;
  private final String artista;
  private final String emocion;

  public Cancion(String nombre, String artista, String emocion) {
    this.nombre = nombre;
    this.artista = artista;
    this.emocion = emocion;
  }

  //Getters y Setters

  public String getNombre() {
    return nombre;
  }

  public String getArtista() {
    return artista;
  }

  public String getEmocion() {
    return emocion;
  }
}
