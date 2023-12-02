package logic;

public class Cancion {
  private final String nombre, artista, emocion, ruta, imagen;
  int popularidad;

  public Cancion(String nombre, String artista, String emocion, String ruta, int popularidad, String imagen) {
    this.nombre = nombre;
    this.artista = artista;
    this.emocion = emocion;
    this.ruta = ruta;
    this.popularidad = popularidad;
    this.imagen = imagen;
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
  public String getRuta() {return ruta;}
  public String getImagen() {return imagen;}

  public int getPopularidad() {
    return popularidad;
  }
}
