package logic;

public class Cancion {
  private final String nombre, artista, emocion, ruta;

  public Cancion(String nombre, String artista, String emocion, String ruta) {
    this.nombre = nombre;
    this.artista = artista;
    this.emocion = emocion;
    this.ruta = ruta;
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
  public String getRuta() {
    return ruta;
  }
}
