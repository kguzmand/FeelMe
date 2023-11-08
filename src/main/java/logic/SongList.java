package logic;

import java.util.Random;

public class SongList {
  private static class Heaps {
    private final int n = 1000010;
    private Cancion[] array;
    public int size;
    public Heaps() {
      array=new Cancion[n];
      size = 0;
    }

    public void insertItem(Cancion cancion) {
      array[size] = cancion;
      moveUp();
      size++;
    }

    private void moveUp() {
      int child = size;
      int parent = (child-1)/2;
      Cancion temp = array[child];
      while(child > 0 && temp.getPopularidad() < array[parent].getPopularidad()) {
        array[child] = array[parent];
        child = parent;
        parent = (child-1)/2;
      }
      array[child]=temp;
    }

    public Cancion removeMin() {
      Cancion min=array[0];
      array[0]=array[--size];
      moveDown();
      return min;
    }

    public Cancion minimo(){
      return array[0];
    }

    private void moveDown() {
      boolean flag = false;
      Cancion smallest = null;
      int parent = 0;
      int child = (2*parent)+1;
      Cancion temp = array[parent];
      while(child < size && !flag) {
        smallest = array[child];
        if(child+1 < size && array[child+1].getPopularidad() < array[child].getPopularidad())
          smallest = array[++child];
        if(smallest.getPopularidad() < temp.getPopularidad()) {
          array[parent] = smallest;
          parent = child;
        }
        else
          flag = true;
        child = 2*parent+1;
      }
      array[parent] = temp;
    }
  }

  private static class SongNode {
    private final Cancion cancion;
    private SongNode siguiente;

    public SongNode(Cancion cancion) {
      this.cancion = cancion;
      this.siguiente = null;
    }

    // Getters y setters

    public SongNode getSiguiente() {
      return siguiente;
    }

    public void setSiguiente(SongNode siguiente) {
      this.siguiente = siguiente;
    }

    public Cancion getCancion() {
      return cancion;
    }
  }
  
  private SongNode primero, ptr;
  private String songChoice, artistChoice, imageChoice;

  // Constructor
  public SongList() {
    primero = null;
  }
  
  public SongNode getPrimero() {
    return primero;
}

  // Método para agregar una canción a la lista
  public void agregarCancion(Cancion cancion) {
    SongNode nuevoNodo = new SongNode(cancion);
    if (primero == null) {
      primero = nuevoNodo;
    } else {
      nuevoNodo.setSiguiente(primero);
      primero = nuevoNodo;
    }
  }

  //No tienen uso en el programa

  // Método para eliminar una canción de la lista
  /*public void eliminarCancion(String nombre) {
    if (primero != null) {
      if (primero.getCancion().getNombre().equals(nombre)) {
        primero = primero.getSiguiente();
      } else {
        NodoCancion actual = primero;
        while (actual.getSiguiente() != null) {
          if (actual.getSiguiente().getCancion().getNombre().equals(nombre)) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            return;
          }
          actual = actual.getSiguiente();
        }
      }
    }
  }*/

  /*public Cancion buscarCancion(String nombre) {
    NodoCancion actual = primero;
    while (actual != null) {
      if (actual.getCancion().getNombre().equals(nombre)) {
        return actual.getCancion(); // Canción encontrada
      }
      actual = actual.getSiguiente();
    }
    return null; // Canción no encontrada
  }*/

  public Cancion seleccionarCancionAleatoria(int feel) {
    String emocion = null;
    if (primero == null) {
      return null; // Retorna null si la lista está vacía
    }else{
      switch (feel){
        case 1:
          emocion = "Felicidad";
          break;
        case 2:
          emocion = "Tristeza";
          break;
        case 3:
          emocion = "Melancolia";
          break;
        case 4:
          emocion = "Enojo";
          break;
        case 5:
          emocion = "Euforia";
          break;
        case 6:
          emocion = "Amor";
          break;
        default:
          System.out.println("Opción no válida.");
          break;
      }
    }

    SongList listaEmocion = filtrarEmocion(emocion);

    // Genera un número aleatorio entre 0 y el número de canciones en la lista
    Random rand = new Random();
    int numCanciones = listaEmocion.contarCanciones();
    int indiceAleatorio = rand.nextInt(numCanciones);

    SongNode actual = listaEmocion.primero;
    for (int i = 0; i < indiceAleatorio; i++) {
      actual = actual.getSiguiente();
    }

    setSongChoice(actual.getCancion().getNombre());
    setArtistChoice(actual.getCancion().getArtista());
    setImageChoice(actual.getCancion().getImagen());

    return actual.getCancion();
  }

  public SongList filtrarEmocion(String emocion){
    SongList listaEmocion = new SongList();
    ptr = primero;
    while(ptr != null){
      if(ptr.getCancion().getEmocion().equals(emocion)){
        listaEmocion.agregarCancion(ptr.getCancion());
      }
      ptr = ptr.getSiguiente();
    }
    return listaEmocion;
  }

  // Método auxiliar para contar el número de canciones en la lista
  private int contarCanciones() {
    int contador = 0;
    SongNode actual = primero;
    while (actual != null) {
      contador++;
      actual = actual.getSiguiente();
    }
    return contador;
  }

  //Metodo para ordenar las canciones por popularidad
  public void ordenarPorPopularidad(){
    Heaps heapPopularidad = new Heaps();
    // Agregar todas las canciones al montículo
    SongNode actual = primero;
    while (actual != null) {
      heapPopularidad.insertItem(actual.getCancion());
      actual = actual.getSiguiente();
    }
    // Limpiar la lista actual
    primero = null;
    // Agregar las canciones ordenadas de nuevo a la lista
    while (heapPopularidad.size > 0) {
      agregarCancion(heapPopularidad.removeMin());
    }
  }

  public String getSongChoice() {
    return songChoice;
  }

  public void setSongChoice(String songChoice) {
    this.songChoice = songChoice;
  }

  public String getArtistChoice() {
    return artistChoice;
  }

  public void setArtistChoice(String artistChoice) {
    this.artistChoice = artistChoice;
  }

  public String getImageChoice() {
    return imageChoice;
  }

  public void setImageChoice(String imageChoice){
    this.imageChoice = imageChoice;
  }
}