package logic;

import java.util.Random;

public class ListaCanciones {
  private NodoCancion primero, ptr;

  // Constructor
  public ListaCanciones() {
    primero = null;
  }
  
  public NodoCancion getPrimero() {
    return primero;
}

  // Método para agregar una canción a la lista
  public void agregarCancion(Cancion cancion) {
    NodoCancion nuevoNodo = new NodoCancion(cancion);
    if (primero == null) {
      primero = nuevoNodo;
    } else {
      nuevoNodo.setSiguiente(primero);
      primero = nuevoNodo;
    }
  }

  // Método para eliminar una canción de la lista
  public void eliminarCancion(String nombre) {
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
  }

  public Cancion buscarCancion(String nombre) {
    NodoCancion actual = primero;
    while (actual != null) {
      if (actual.getCancion().getNombre().equals(nombre)) {
        return actual.getCancion(); // Canción encontrada
      }
      actual = actual.getSiguiente();
    }
    return null; // Canción no encontrada
  }

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

    ListaCanciones listaEmocion = filtrarEmocion(emocion);

    // Genera un número aleatorio entre 0 y el número de canciones en la lista
    Random rand = new Random();
    int numCanciones = listaEmocion.contarCanciones();
    int indiceAleatorio = rand.nextInt(numCanciones);

    NodoCancion actual = listaEmocion.primero;
    for (int i = 0; i < indiceAleatorio; i++) {
      actual = actual.getSiguiente();
    }

    return actual.getCancion();
  }

  public ListaCanciones filtrarEmocion(String emocion){
    ListaCanciones listaEmocion = new ListaCanciones();
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
    NodoCancion actual = primero;
    while (actual != null) {
      contador++;
      actual = actual.getSiguiente();
    }
    return contador;
  }

  // Método para imprimir la lista de canciones
  public void imprimirLista() {
    NodoCancion actual = primero;
    while (actual != null) {
      System.out.println("Canción: " + actual.getCancion().getNombre());
      System.out.println("Artista: " + actual.getCancion().getArtista());
      System.out.println("Emoción: " + actual.getCancion().getEmocion());
      System.out.println("//////////////////////////");
      actual = actual.getSiguiente();
    }
  }
}
