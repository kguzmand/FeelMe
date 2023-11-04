package logic;

public class Cancion {
  private final String nombre, artista, emocion, ruta;
  int popularidad;

  public Cancion(String nombre, String artista, String emocion, String ruta, int popularidad) {
    this.nombre = nombre;
    this.artista = artista;
    this.emocion = emocion;
    this.ruta = ruta;
    this.popularidad = popularidad;
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

  public int getPopularidad() {
    return popularidad;
  }
}
